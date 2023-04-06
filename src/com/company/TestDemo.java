package com.company;

import java.awt.*;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class TestDemo {
    public static void main(String[] args) {
TestProcessor testProcessor=new TestProcessor(new Test());
testProcessor.process();
    }
}
class Test{
    @Order(3)
    public void run1(){
        System.out.println("run 1");
    }

    @Order(2)
    public void run2(){
        System.out.println("run 2");
    }

    @Order(1)
    public void run3(){
        System.out.println("run 3");
    }
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface  Order{
    int value();
}

class TestProcessor {
    private Test test;


    public TestProcessor(Test test) {
        this.test = test;
    }
    //вызывать все методы помечанные  аннотацией order
    public void process(){
        Map<Integer,Method> map=new TreeMap<>();
        Method [] methods=test.getClass().getDeclaredMethods();
        for(Method method:methods){
            Annotation annotation = method.getAnnotation(Order.class);
map.put(((Order)annotation).value(), method);
        }
        for (Map.Entry<Integer,Method> entry:map.entrySet()){
            try {
                entry.getValue().invoke(test);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}