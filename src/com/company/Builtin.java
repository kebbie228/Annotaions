package com.company;

import java.util.LinkedList;
import java.util.List;

public class Builtin {
    public static void main(String[] args) {
    Machine machine=new Machine();
    machine.addVersion("v1");

    DeprecatedDemo.test();

    }
}

   class Machine{
    private List versions=new LinkedList();

@SuppressWarnings({"unchecked"})
    public void addVersion(String version){
        versions.add(version);
    }
}

class DeprecatedDemo{

    @Deprecated(since="1.2", forRemoval=true)
    public static void test(){
        System.out.println("Legacy function");


    }
}
@FunctionalInterface
interface Print {
    @SuppressWarnings({"unused"})
    void print();

}




