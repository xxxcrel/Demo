package com.cheeseocean.concurrency;

public class TryCatch {
    public static void main(String[] args) {
        doSth();
    }

    static void doSth(){
        boolean completedAbrutly = true;

        try {
            try {
                System.out.println("inner try");
                int zero = 0;
                int i = 10 / zero;
            } catch (Exception e) {
                System.out.println("inner exeception");
                throw e;
            }  finally{
                System.out.println("inner finally");
            }
            completedAbrutly = false;
        } finally {
            System.out.println("outer finally: " + completedAbrutly);
        }
    }
}
