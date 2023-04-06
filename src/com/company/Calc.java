package com.company;

   public class Calc {

    public double sum(int a, double b) {
        return a + b;
    }

    public static double mult(float a, long b) {
        return a * b;
    }

    private boolean and(boolean a, boolean b) {
        return a && b;
    }

    protected int max(int a, int b) {
        return a > b ? a : b;
    }
}
