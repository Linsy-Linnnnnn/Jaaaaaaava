package atm;



//此处用来存放账号密码，以及用户个人信息
class Data {//储存输入的账号密码的类

    private String acc;
    private String psw;
    private String acc1 = "123456";
    private static String psw1 = "fh58q2ea6thauof5ikg98fe2ciafh50r";
    private String acc2 = "114514";
    private static String psw2 = "5i2gjnodupfpg9msgsl9ljvaacm1uku7";

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getAcc1() {
        return acc1;
    }

    public void setPsw1(String psw1) {
        this.psw1 = psw1;
    }

    public String getPsw1() {
        return psw1;
    }

    public String getAcc2() {
        return acc2;
    }

    public String getPsw2() {
        return psw2;
    }

    public static void setPsw2(String psw2) {
        Data.psw2 = psw2;
    }

    public void setAcc2(String acc2) {
        this.acc2 = acc2;
    }

    //储存用户账号的各个信息
    private String Id1 = "123456";
    private String name1 = "张浩二";
    private static double money1 = 100000;
    private double limit1 = 50000;

    //账户二
    private String Id2 = "114514";
    private String name2 = "李田所";
    private static double money2 = 70000;
    private double limit2 = 10000;

    //获取账户信息后的查询功能
    void Acc1() {
        System.out.println("您好！" + name1 + "先生");
        System.out.println("您的卡号是：" + Id1);
        System.out.println("您的余额为：" + money1);
        System.out.println("您每次的取款限额是" + limit1);
    }

    void Acc2() {
        System.out.println("您好！" + name2 + "先生");
        System.out.println("您的卡号是：" + Id2);
        System.out.println("您的余额为：" + money2);
        System.out.println("您每次的取款限额是" + limit2);
    }

    public double getMoney1() {
        return money1;
    }

    public double getMoney2() {
        return money2;
    }

    public void setMoney1(double money1) {
        this.money1 = money1;
    }

    public void setMoney2(double money2) {
        this.money2 = money2;
    }

    public double getLimit1() {
        return limit1;
    }

    public double getLimit2() {
        return limit2;
    }
}