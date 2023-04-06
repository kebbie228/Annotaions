package com.company;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class BookDemo {
    public static void main(String[] args) throws IllegalAccessException {
Book book=new Book(null,"author",2023,-13);
    BookProcessor bookProcessor=new BookProcessor(book);
    bookProcessor.print();
    bookProcessor.checkNotNull();
    bookProcessor.checkIsPositive();
    }
}
@BookAnnotation("short")
class Book{
@NotNull
public String title;
public String author;
public int year;
@IntPositive
public int pages;

public Book(String title, String author, int year, int pages) {

        this.title = title;

        this.author = author;
        this.year = year;
        this.pages = pages;
        }

public void printShort(){
        System.out.println("Title: "+title+", author:"+author);
        }

public void printLong(){
        System.out.println("Title: "+title+", author:"+author+ ", year"+year);
        }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface  BookAnnotation{
 String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface  NotNull{}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface  IntPositive{}

class BookProcessor{
    private Book book;


    public BookProcessor(Book book) {

        this.book = book;
        try {
            checkNotNull();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            checkIsPositive();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        Class obj = Book.class;
        Annotation annotation = obj.getAnnotation(BookAnnotation.class);
        if (annotation instanceof BookAnnotation){
            String value = ((BookAnnotation) annotation).value();
            if (value.equals("short"))
                book.printShort();
            if (value.equals("long"))
                book.printLong();
        }
    }

    public void checkNotNull() throws IllegalAccessException {
        Field[] fields = book.getClass().getDeclaredFields();
        for (Field field:fields){
            Annotation annotation = field.getAnnotation(NotNull.class);
            if (annotation!=null && field.get(book)==null)
                System.err.printf("Field %s cannot be null", field.getName());
        }
    }

    public void checkIsPositive() throws IllegalAccessException {
        Field[] fields = book.getClass().getDeclaredFields();
        for (Field field:fields){
            Annotation annotation = field.getAnnotation(IntPositive.class);
            if (annotation!=null && field.getInt(book)<=0)
                System.err.printf("Field %s must be positive", field.getName());
        }
    }
}