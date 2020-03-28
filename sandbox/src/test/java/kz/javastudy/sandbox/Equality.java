package kz.javastudy.sandbox;

public class Equality {
    public static void main(String [] args) {
        String s1="firefox";
        String s2=new String(s1);
        String s3="firefox";
        String s4="fire"+ "fox";
        String s5="firefox 2.0";
        String s6="firefox"+Math.sqrt(4.0);

        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));

        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));

        System.out.println(s1==s4);
        System.out.println(s1.equals(s4));

        System.out.println(s5==s6);
        System.out.println(s5.equals(s6));
    }
}
