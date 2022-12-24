package atm;

import java.util.Scanner;

class menu {//主菜单

    static void main() {
        menu me = new menu();
        String fc;
        while (true) {
            System.out.println("\n" + "********************************************");
            System.out.println("请选择自动柜员机服务");
            System.out.println("1.存款" + "\n" + "2.取款" + "\n" + "3.转账" + "\n" + "4.修改密码" + "\n" + "5.退出账号");
            System.out.println("********************************************");
            Scanner input1 = new Scanner(System.in);
            fc = input1.nextLine();
            if (fc.equals("1")) {
                System.out.println("请输入存入金额：");
                me.cunkuan();//存款方法 第42行
            } else if (fc.equals("2")) {
                System.out.println("请输入取出金额：");
                me.qukuan();//取款方法 第71-109行
            } else if (fc.equals("3")) {
                me.zhuanzhang();//转账方法 第110=177行
            } else if (fc.equals("4")) {
                me.xiugai();//改密码算法 第178-209行
                break;
            } else if (fc.equals("5")) {
                break;//退出循环 回到登陆页面
            } else {
                System.out.println("输入错误！请重新输入");//用户没输入正确的功能序号
                try {//1秒延迟
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void cunkuan() {//存款
        Data da = new Data();//调用数据类
        main ma = new main();//调用登录页类 用于判断登陆的是哪个账号
        while (true) {
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {//判断用户输入的是否是数字
                if (main.WhatAcc == 1) {//登录的是账号1
                    double money = input.nextDouble();//输入存款金额
                    da.setMoney1(da.getMoney1() + money);//将存款加进去余额里
                    System.out.println("存款成功！您的余额为：" + da.getMoney1());
                    da.Acc1();//输出账号信息
                    ma.back();//3秒倒数返回菜单
                    break;
                } else if (main.WhatAcc == 2) {//登录的是账号2
                    double money = input.nextDouble();//输入存款金额
                    da.setMoney2(da.getMoney2() + money);//将存款加进去余额里
                    System.out.println("存款成功！您的余额为：" + da.getMoney2());
                    da.Acc2();//输出账号信息
                    ma.back();//3秒倒数返回菜单
                    break;
                } else {
                    System.out.println("程序错误：未知的存款账户");//指针指向空位报错，一般不会出现
                }
            } else {
                System.out.println("请输入金额！");//用户没输入数字就会弹出这个
            }
        }
    }

    void qukuan() {//取款
        Data da = new Data();//如上
        main ma = new main();
        while (true) {
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {//判断用户输入的是否是数字
                if (main.WhatAcc == 1) {//用户1
                    double money = input.nextDouble();
                    if (money <= da.getLimit1() && money <= da.getMoney1()) {//判断取款金额是否超出账户余额和限额
                        da.setMoney1(da.getMoney1() - money);//从余额中取出
                        System.out.println("取款成功！您的余额为：" + da.getMoney1());
                        da.Acc1();//输出账号信息
                    } else {
                        System.out.println("超出限额/余额不足！");
                    }
                    ma.back();//3秒倒数回到菜单
                    break;
                } else if (main.WhatAcc == 2) {//用户2
                    double money = input.nextDouble();
                    if (money <= da.getLimit2() && money <= da.getMoney2()) {//判断取款金额是否超出账户余额和限额
                        da.setMoney2(da.getMoney2() - money);//从余额中取出
                        System.out.println("取款成功！您的余额为：" + da.getMoney2());
                        da.Acc2();//输出账号信息
                        ma.back();//3秒倒数回到菜单
                        break;
                    } else {
                        System.out.println("超出限额/余额不足!");
                        ma.back();
                        break;
                    }
                } else {
                    System.out.println("请输入金额！");//用于用户没正确输入数字
                }
            }
        }
    }

    void zhuanzhang() {//转账方法
        Data da = new Data();
        main ma = new main();
        while (true) {
            System.out.println("\n" + "请输入对方账户,或输入R回到菜单：");
            Scanner input = new Scanner(System.in);
            da.setAcc(input.nextLine());//存储对方账号
            if (da.getAcc().equals(da.getAcc1()) || da.getAcc().equals(da.getAcc2())) {//判断输入的账号是否为系统内已有的账号
                if (main.WhatAcc == 1 && da.getAcc().equals(da.getAcc1()) || main.WhatAcc == 2 && da.getAcc().equals(da.getAcc2())) {//判断用户输入的是否是自己的账户
                    System.out.println("不能转账给你自己！");
                } else {

                    System.out.println("请确认对方卡号：" + da.getAcc());
                    System.out.println("输入“Y”以继续,或输入任意修改卡号");
                    while (true) {
                        Scanner input1 = new Scanner(System.in);
                        String YoN = input1.nextLine();
                        if (YoN.equals("Y") || YoN.equals("y")) {
                            System.out.println("请输入转账金额：");
                            Scanner input2 = new Scanner(System.in);
                            double money = input2.nextDouble();
                            if (main.WhatAcc == 1) {//账户1为转账发起者
                                if (money <= da.getLimit1() && money <= da.getMoney1()) {
                                    da.setMoney1(da.getMoney1() - money);//从转账发起者的余额扣钱
                                    da.setMoney2(da.getMoney2() + money);//给被转账者的余额加钱
                                    System.out.println("转账成功！" + "\n" + "您的余额为：" + da.getMoney1() + "\n" + "对方的余额为：" + da.getMoney2());
                                    da.Acc1();
                                    ma.back();
                                    break;
                                } else {
                                    System.out.println("超出限额/余额不足！");
                                    ma.back();
                                    break;
                                }
                            }
                            if (main.WhatAcc == 2) {//账户2为转账发起者
                                if (money <= da.getLimit2() && money <= da.getMoney2()) {
                                    da.setMoney2(da.getMoney2() - money);//从转账发起者的余额扣钱
                                    da.setMoney1(da.getMoney1() + money);//给被转账者的余额加钱
                                    System.out.println("转账成功！" + "\n" + "您的余额为：" + da.getMoney2() + "\n" + "对方的余额为：" + da.getMoney1());
                                    da.Acc2();
                                    ma.back();
                                    break;
                                } else {
                                    System.out.println("超出限额/余额不足！");
                                    ma.back();
                                    break;
                                }
                            }
                        } else {
                            System.out.println("请正确输入金额！");
                            ma.back();
                            break;
                        }
                    }
                }

            } else if (da.getAcc().equals("R") || da.getAcc().equals("r")) {
                ma.back();
                break;
            } else {
                System.out.println("请正确输入卡号！");
                ma.back();
                break;
            }
        }
    }

    void xiugai() {//改密码方法
        Data da = new Data();
        main ma = new main();
        SHA1 sha = new SHA1();//加密算法
        while (true) {
            System.out.println("请输入原密码：");
            Scanner input = new Scanner(System.in);
            String opsw = input.nextLine();//opsw:old Password：旧密码
            sha.setPsw(opsw);//这三行与登陆时的原理一样，加密得到密钥之后对比旧密码是否正确
            sha.main();
            String oSpsw = sha.getSHAPsw();//oSpsw:old SHA1 password:旧的加密后密钥
            if (main.WhatAcc == 1 && oSpsw.equals(da.getPsw1())) {//如果输入的密码和旧密码是一样的，执行此段代码
                System.out.println("你正在修改账户：" + da.getAcc1() + "的密码");
                while (true) {
                    System.out.println("请输入新密码：");
                    Scanner input2 = new Scanner(System.in);
                    String npsw = input2.nextLine();//npsw:new password：新密码
                    System.out.println("请再次输入新密码：");
                    Scanner input3 = new Scanner(System.in);
                    if (npsw.equals(input3.nextLine())) {//判断两次输入的密码是否一致
                        sha.setPsw(npsw);//这三行都是将新密码转换为加密后密钥
                        sha.main();
                        String nSpsw = sha.getSHAPsw();//nSpsw:new SHA1 password：加密后的新密码
                        da.setPsw1(nSpsw);//新密码替换旧密码
                        System.out.println("修改成功！请牢记您的新密码.");
                        break;
                    } else {//如果输入的密码和旧密码不一样，执行此段代码
                        System.out.println("两次输入的密码不一致！请重试");
                    }
                }
                break;
            } else if (main.WhatAcc == 2 && oSpsw.equals(da.getPsw2())) {
                System.out.println("你正在修改账户: " + da.getAcc2() + "的密码");
                while (true) {
                    System.out.println("请输入新密码：");
                    Scanner input4 = new Scanner(System.in);
                    String npsw = input4.nextLine();//npsw:new password：新密码
                    System.out.println("请再次输入新密码：");
                    Scanner input5 = new Scanner(System.in);
                    if (npsw.equals(input5.nextLine())) {//判断两次输入的密码是否一致
                        sha.setPsw(npsw);//这三行都是将新密码转换为加密后密钥
                        sha.main();
                        String nSpsw = sha.getSHAPsw();//nSpsw:new SHA1 password：加密后的新密码
                        da.setPsw2(nSpsw);//新密码替换旧密码
                        System.out.println("修改成功！请牢记您的新密码.");
                        break;
                    } else {//如果输入的密码和旧密码不一样，执行此段代码
                        System.out.println("两次输入的密码不一致！请重试");
                    }
                }
                break;
            } else {
                System.out.println("原密码错误！");
            }
        }
    }
}


