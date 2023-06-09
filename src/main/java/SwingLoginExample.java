import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingLoginExample {

    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("基金试算表路径:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField text1 = new JTextField(20);
        text1.setBounds(100, 20, 165, 25);
        panel.add(text1);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("通知书存放路径:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JTextField text2 = new JTextField();
        text2.setBounds(100, 50, 165, 25);
        panel.add(text2);

        // 创建登录按钮
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 120, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WriteWord.out(text1.getText(), text2.getText(), "E:\\system_files\\Desktop\\中江城投季度报表模板\\新建 Microsoft Word 文档.docx");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}