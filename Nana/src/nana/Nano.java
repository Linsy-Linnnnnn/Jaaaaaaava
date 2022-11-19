package nana;

import java.util.Scanner;
//design by linsy
public class Nano {
public static void main(String[] args) {
     double bb = 144.969;
     int cc=(int)bb;
     System.out.println("输进去一个数字");
     System.out.println(cc);
     int input1;//定义数据类型
     Scanner input=new Scanner(System.in);//输入数字
     input1 = input.nextInt();
     if(input1%2==0){//如果这个数字除以2的余数等于0
          System.out.println("奇数");
     }
     else {//如果不为0
          System.out.println("j偶数");
     }

     
}}
