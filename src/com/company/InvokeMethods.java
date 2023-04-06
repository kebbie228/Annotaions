package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethods {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    System.setSecurityManager(new SecurityManager());

        Calc calc=new Calc();
        Method sum = Calc.class.getMethod("sum", int.class, double.class);
//public
       double resSum = (double) sum.invoke(calc,1,3);
        System.out.println(resSum);

        //public static
        Method sum2 = Calc.class.getMethod("mult", float.class, long.class);
        System.out.println(sum2.invoke(null,15,3));
    //private
        Method and = Calc.class.getDeclaredMethod("and", boolean.class, boolean.class);
      and.setAccessible(true);
        System.out.println(and.invoke(calc,true,false));

        //protected
        Method max = Calc.class.getDeclaredMethod("max", int.class, int.class);
        System.out.println(max.invoke(calc,12,3));
    }

}
