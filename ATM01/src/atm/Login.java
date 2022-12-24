package atm;

//1.0.0
//此文件包含主函数，ATM程序从这里开始
class Login {
    public static void main(String[] args) {
        while (true) {
            main main = new main();//实例化登陆和主菜单
            menu menu = new menu();
            main.main();//进入登陆页面
            if (main.WhatAcc == 1 && main.tof == true || main.WhatAcc == 2 && main.tof == true) {//判断登录的账号密码是否有记录
                menu.main();//进入主菜单
            } else {
                System.out.println("?");//程序结束
                break;
            }
        }
    }
}