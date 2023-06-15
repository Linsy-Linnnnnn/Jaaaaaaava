// 导入所需的库
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashMap;
import java.util.Map;

// 创建一个银行卡管理系统类
public class BankCardManagementSystem {
    // 定义界面组件
    private JFrame frame;
    private JTextField cardNumberField;
    private JTextField amountField;
    private JPasswordField passwordField; // 修改为JPasswordField以隐藏密码输入
    private JTextField targetCardNumberField;
    private JTextArea operationLogArea;
    private JComboBox<String> bankComboBox;
    private JButton createCardButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePasswordButton;
    private JButton transferButton;

    // 定义一个存储银行卡信息的Map
    private Map<String, BankCard> bankCards;

    // 主函数
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BankCardManagementSystem window = new BankCardManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 构造函数
    public BankCardManagementSystem() {
        initialize();
    }

    // 初始化界面
    private void initialize() {
        // 初始化组件
        frame = new JFrame("银行卡管理系统");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // 创建银行下拉框
        bankComboBox = new JComboBox<>();
        bankComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"中国工商银行", "中国建设银行", "中国农业银行"}));
        bankComboBox.setBounds(10, 10, 120, 25);
        frame.getContentPane().add(bankComboBox);

        // 创建银行卡号输入框
        cardNumberField = new JTextField();
        cardNumberField.setBounds(140, 10, 120, 25);
        cardNumberField.setText("请输入卡号");
        cardNumberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cardNumberField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cardNumberField.getText().isEmpty()) {
                    cardNumberField.setText("请输入卡号");
                }
            }
        });
        frame.getContentPane().add(cardNumberField);

        // 创建密码输入框
        passwordField = new JPasswordField();
        passwordField.setBounds(270, 10, 120, 25);
        passwordField.setEchoChar((char) 0);
        passwordField.setText("请输入密码");
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setText("");
                passwordField.setEchoChar('*');
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText("请输入密码");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        frame.getContentPane().add(passwordField);

        // 创建办理银行卡按钮
        createCardButton = new JButton("办理银行卡");
        createCardButton.setBounds(10, 45, 120, 25);
        frame.getContentPane().add(createCardButton);

        // 创建存款输入框
        amountField = new JTextField();
        amountField.setBounds(140, 45, 120, 25);
        amountField.setText("请输入金额");
        amountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                amountField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (amountField.getText().isEmpty()) {
                    amountField.setText("请输入金额");
                }
            }
        });
        frame.getContentPane().add(amountField);

        // 创建存款按钮
        depositButton = new JButton("存款");
        depositButton.setBounds(270, 45, 60, 25);
        frame.getContentPane().add(depositButton);

        // 创建取款按钮
        withdrawButton = new JButton("取款");
        withdrawButton.setBounds(330, 45, 60, 25);
        frame.getContentPane().add(withdrawButton);

        // 创建修改密码按钮
        changePasswordButton = new JButton("修改密码");
        changePasswordButton.setBounds(10, 80, 120, 25);
        frame.getContentPane().add(changePasswordButton);

        // 创建转账按钮
        transferButton = new JButton("转账");
        transferButton.setBounds(270, 80, 60, 25);
        frame.getContentPane().add(transferButton);

        // 创建目标银行卡号输入框
        targetCardNumberField = new JTextField();
        targetCardNumberField.setBounds(140, 80, 120, 25);
        targetCardNumberField.setText("请输入目标银行卡/新密码");
        targetCardNumberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                targetCardNumberField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (targetCardNumberField.getText().isEmpty()) {
                    targetCardNumberField.setText("请输入目标银行卡/新密码");
                }
            }
        });
        frame.getContentPane().add(targetCardNumberField);
        // 创建操作记录显示区域
        operationLogArea = new JTextArea();
        operationLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(operationLogArea);
        scrollPane.setBounds(10, 115, 414, 136);
        frame.getContentPane().add(scrollPane);

        // 初始化银行卡信息存储
        bankCards = new HashMap<>();

        // 添加事件监听器
        createCardButton.addActionListener(new CreateCardListener());
        depositButton.addActionListener(new DepositListener());
        withdrawButton.addActionListener(new WithdrawListener());
        changePasswordButton.addActionListener(new ChangePasswordListener());
        transferButton.addActionListener(new TransferListener());
    }

    // 办理银行卡事件监听器
    private class CreateCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取输入的银行卡号和密码
            String cardNumber = cardNumberField.getText();
            String password = new String(passwordField.getPassword()); // 使用getPassword()获取密码

            // 判断输入是否为空
            if (cardNumber.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "银行卡号和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断银行卡是否已存在
            if (bankCards.containsKey(cardNumber)) {
                JOptionPane.showMessageDialog(frame, "银行卡已存在！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 创建新的银行卡对象
            BankCard newCard = new BankCard(cardNumber, password, (String) bankComboBox.getSelectedItem());

            // 将新的银行卡添加到存储中
            bankCards.put(cardNumber, newCard);

            // 更新操作记录
            operationLogArea.append("成功办理银行卡：" + newCard + "\n");
        }
    }

    // 存款事件监听器
    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取输入的银行卡号、密码和存款金额
            String cardNumber = cardNumberField.getText();
            String password = new String(passwordField.getPassword()); // 使用getPassword()获取密码
            String amountStr = amountField.getText();

            // 判断输入是否为空
            if (cardNumber.isEmpty() || password.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "银行卡号、密码和存款金额不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为数字
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "存款金额必须为数字！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为正数
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "存款金额必须为正数！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断银行卡是否存在
            if (!bankCards.containsKey(cardNumber)) {
                JOptionPane.showMessageDialog(frame, "银行卡不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取银行卡对象
            BankCard card = bankCards.get(cardNumber);

            // 验证密码
            if (!card.verifyPassword(password)) {
                JOptionPane.showMessageDialog(frame, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 存款
            card.deposit(amount);

            // 更新操作记录
            operationLogArea.append("成功存款：" + card + " 存款金额：" + amount + "\n");
        }
    }

    // 取款事件监听器
    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取输入的银行卡号、密码和取款金额
            String cardNumber = cardNumberField.getText();
            String password = new String(passwordField.getPassword()); // 使用getPassword()获取密码
            String amountStr = amountField.getText();

            // 判断输入是否为空
            if (cardNumber.isEmpty() || password.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "银行卡号、密码和取款金额不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为数字
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "取款金额必须为数字！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为正数
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "取款金额必须为正数！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断银行卡是否存在
            if (!bankCards.containsKey(cardNumber)) {
                JOptionPane.showMessageDialog(frame, "银行卡不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取银行卡对象
            BankCard card = bankCards.get(cardNumber);

            // 验证密码
            if (!card.verifyPassword(password)) {
                JOptionPane.showMessageDialog(frame, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 取款
            if (!card.withdraw(amount)) {
                JOptionPane.showMessageDialog(frame, "余额不足！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 更新操作记录
            operationLogArea.append("成功取款：" + card + " 取款金额：" + amount + "\n");
        }
    }

    // 修改密码事件监听器
    private class ChangePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取输入的银行卡号和新密码
            String cardNumber = cardNumberField.getText();
            String newPassword = new String(passwordField.getPassword()); // 使用getPassword()获取新密码

            // 判断输入是否为空
            if (cardNumber.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "银行卡号和新密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断银行卡是否存在
            if (!bankCards.containsKey(cardNumber)) {
                JOptionPane.showMessageDialog(frame, "银行卡不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取银行卡对象
            BankCard card = bankCards.get(cardNumber);

            // 修改密码前需要验证旧密码
            String oldPassword = JOptionPane.showInputDialog(frame, "请输入旧密码：");
            if (oldPassword == null || !card.verifyPassword(oldPassword)) {
                JOptionPane.showMessageDialog(frame, "旧密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 修改密码
            card.changePassword(newPassword);

            // 更新操作记录
            operationLogArea.append("成功修改密码：" + card + "\n");
        }
    }

    // 转账事件监听器
    private class TransferListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取输入的银行卡号、密码、目标银行卡号和转账金额
            String cardNumber = cardNumberField.getText();
            String password = new String(passwordField.getPassword()); // 使用getPassword()获取密码
            String targetCardNumber = targetCardNumberField.getText();
            String amountStr = amountField.getText();

            // 判断输入是否为空
            if (cardNumber.isEmpty() || password.isEmpty() || targetCardNumber.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "银行卡号、密码、目标银行卡号和转账金额不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为数字
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "转账金额必须为数字！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断输入的金额是否为正数
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "转账金额必须为正数！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断银行卡是否存在
            if (!bankCards.containsKey(cardNumber) || !bankCards.containsKey(targetCardNumber)) {
                JOptionPane.showMessageDialog(frame, "银行卡不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取银行卡对象
            BankCard card = bankCards.get(cardNumber);
            BankCard targetCard = bankCards.get(targetCardNumber);

            // 验证密码
            if (!card.verifyPassword(password)) {
                JOptionPane.showMessageDialog(frame, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 转账
            if (!card.transfer(targetCard, amount)) {
                JOptionPane.showMessageDialog(frame, "余额不足！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 更新操作记录
            operationLogArea.append("成功转账：" + card + " 转账金额：" + amount + " 目标银行卡：" + targetCard + "\n");
        }
    }
}

// 银行卡类
class BankCard {
    private String cardNumber;
    private String password;
    private String bank;
    private double balance;

    public BankCard(String cardNumber, String password, String bank) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.bank = bank;
        this.balance = 0;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(BankCard targetCard, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            targetCard.deposit(amount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "银行卡号：" + cardNumber + " 银行：" + bank + " 余额：" + balance;
    }
}
