package com.pgt.util;

/**
 * @program: pgtsdk
 * @description:
 * @author: LL
 * @create: 2018-05-21 16:23
 **/
public class test {
    public static void main(String[] args){
     /*   double a = 1.2789;
        float b = 1;
        System.out.println(a - b);

        int c = 'a';
        char d = 97;
        System.out.println(c == d);

        Integer e = 500;
        Integer f = 500;
        System.out.println( e == f);*/

        System.out.println(4&6);


    }
    public static String test(){
        int sum = 0;
        for (int i=0;i<10;i++) {
            if (i == 7)
                break;
            if (i > 5)
                continue;
            sum += i;
        }
        try {
            System.out.println("try");
             return "ss";
        }catch (Exception e){

        }finally {
            System.out.println(sum);
        }
        return "ss1";
    }

}
