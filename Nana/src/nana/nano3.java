package nana;

import java.util.ArrayList;
import java.util.Scanner;


public class nano3 {
    public static void main(String[] args) {
        System.out.println("1.妈");
        System.out.println("2.死");
        System.out.println("3.你");
        System.out.println("4.逼");
        System.out.println("5.傻");
        System.out.println("6.低");
        System.out.println("7.了");
        System.out.println("8.能");

        int n1,c,d,e,f,g,h,j,k;
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("");
        sites.add("妈");
        sites.add("死");
        sites.add("你");
        sites.add("逼");
        sites.add("傻");
        sites.add("低");
        sites.add("了");
        sites.add("能");

        Scanner input1 = new Scanner(System.in);
        n1 = input1.nextInt();
        k=n1%10;
        j=n1/10%10;
        h=n1/100%10;
        g=n1/1000%10;
        f=n1/10000%10;
        e=n1/100000%10;
        d=n1/1000000%10;
        c=n1/10000000%10;




        System.out.println(sites.get(c)+sites.get(d)+sites.get(e)+sites.get(f)+sites.get(g)+sites.get(h)+sites.get(j)+sites.get(k));



         

    }
}
