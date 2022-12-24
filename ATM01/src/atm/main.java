package atm;

import java.util.Scanner;

//此类为登陆页面
class main {
    public static int WhatAcc = 0;//判断登陆的是哪个账号
    static boolean tof = true;//用于退出程序

    public void main() {
        Data paa = new Data();//数据库 Data类
        SHA1 sha = new SHA1();//加密算法 SHA1类
        clean();//清空屏幕的算法 第69行

        System.out.println("--------***欢迎登陆天地银行***--------");
        System.out.println("按enter进入登陆界面" + "\n" + "输入r退出系统");
        Scanner input0 = new Scanner(System.in);
        String LorR = input0.nextLine();
        while (tof) {//当用户输入R主动退出程序
            if (LorR.equals("R") || LorR.equals("r")) {
                System.out.println("欢迎下次光临！");
                tof = false;
                break;
            } else {

                System.out.println("\n" + "请输入卡号:");//输入卡号并重复一遍确认
                Scanner input1 = new Scanner(System.in);
                paa.setAcc(input1.nextLine());
                System.out.println("卡号：" + paa.getAcc());

                System.out.println("请输入密码:");//输入密码并重复
                Scanner input2 = new Scanner(System.in);
                paa.setPsw(input2.nextLine());//将输入的密码存储到数据类中的密码变量
                sha.setPsw(paa.getPsw());//将输入的密码发给加密算法SHA1类

                sha.main();//在SHA1内对密码进行处理
                String AccAndPas = paa.getAcc() + sha.getSHAPsw();//对密码进行加工，得出最终包含账号+密码的密钥以进行比对
                System.out.println(AccAndPas);//输出包含账号+密码的密钥（此行代码可有可无）
                if (AccAndPas.equals(paa.getAcc1() + paa.getPsw1())) {//将刚刚输入的密钥与数据类中存储的账号1密钥进行比对
                    System.out.println("密码正确！");
                    paa.Acc1();
                    WhatAcc = 1;
                    break;
                } else if (AccAndPas.equals(paa.getAcc2() + paa.getPsw2())) {//将刚刚输入的密钥与数据类中存储的账号2密钥进行比对
                    System.out.println("密码正确！");
                    paa.Acc2();
                    WhatAcc = 2;
                    break;
                } else {//输入密钥与两个账号密钥均不相同执行此行代码
                    System.out.println("密码错误！请重试");
                    back();
                }
            }
        }
    }

    static void back() { //此方法用于各种倒计时后返回上一页的情况
        int sec;
        for (sec = 3; sec >= 0; sec--) {
            System.out.print("\r" + sec + "秒后返回上一页面");
            try {//3秒倒计时延迟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clean() {//50个换行强制清屏
        for (int i = 0; i < 50; i++)
            System.out.println(" ");
    }
}


