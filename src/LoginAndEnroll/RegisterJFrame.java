package LoginAndEnroll;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {
    ArrayList<User> allUsers;

    //提升三个输入框的变量的作用范围，让这三个变量可以在本类中所有方法里面可以使用。
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    //提升两个按钮变量的作用范围，让这两个变量可以在本类中所有方法里面可以使用。
    JButton submit = new JButton();
    JButton reset = new JButton();

    public RegisterJFrame(ArrayList<User>allUsers) {
        this.allUsers = allUsers;
        initFrame();
        initView();
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==submit){
            if(username.getText().length()==0||password.getText().length()==0||rePassword.getText().length()==0) {
                showDialog("用户名和密码不能为空");
                return;
            }
            if (!password.getText().equals(rePassword.getText())){
                showDialog("两次密码输入不一致");
                return;
            }
            if (!username.getText().matches("[a-zA-Z0-9]{4,16}")){
                showDialog("用户名不符合规则。用户名应包含4-16位数字或字母");
                return;
            }
            if (!password.getText().matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")){
                showDialog("密码不符合规则，至少包含一个小写字母，一个数字，长度至少六位");
                return;
            }
            if (containUsername(username.getText())){
                showDialog("用户名已经存在，请重新输入");
                return;
            }

            allUsers.add(new User(username.getText(),password.getText()));

            //写入：
            FileOutputStream fos1;
            try {
                fos1 = new FileOutputStream("User/use.txt",true);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String r = "|username=";
            try {
                fos1.write(r.getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fos1.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            FileOutputStream fosName = null;
            try {
                fosName = new FileOutputStream("User/use.txt",true);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fosName.write(username.getText().getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String p = "&password=";
            try {
                fosName.write(p.getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fosName.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            FileOutputStream fosPassword = null;
            try {
                fosPassword = new FileOutputStream("User/use.txt",true);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fosPassword.write(password.getText().getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fosPassword.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showDialog("注册成功");

            this.setVisible(false);
            try {
                new LoginJFrame2();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==reset) {
            //点击了重置按钮
            //清空三个输入框
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }

    }

    public boolean containUsername(String username){
        for (User u : allUsers) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("register\\注册按下.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("register\\重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("register\\注册按钮.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("register\\重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void initView() {
        //添加注册用户名的文本
        JLabel usernameText = new JLabel(new ImageIcon("register\\注册用户名.png"));
        usernameText.setBounds(85, 135, 80, 20);

        //添加注册用户名的输入框
        username.setBounds(195, 134, 200, 30);

        //添加注册密码的文本
        JLabel passwordText = new JLabel(new ImageIcon("register\\注册密码.png"));
        passwordText.setBounds(97, 193, 70, 20);

        //添加密码输入框
        password.setBounds(195, 195, 200, 30);

        //添加再次输入密码的文本
        JLabel rePasswordText = new JLabel(new ImageIcon("register\\再次输入密码.png"));
        rePasswordText.setBounds(64, 255, 95, 20);

        //添加再次输入密码的输入框
        rePassword.setBounds(195, 255, 200, 30);

        //注册的按钮
        submit.setIcon(new ImageIcon("register\\注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);

        //重置的按钮
        reset.setIcon(new ImageIcon("register\\重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        //背景图片
        JLabel background = new JLabel(new ImageIcon("image/maliao.png"));
        background.setBounds(0, 0, 500, 600);

        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(rePassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    private void initFrame() {
        //对自己的界面做一些设置。
        //设置宽高
        setSize(488, 430);
        //设置标题
        setTitle("超级彩虹马里奥斗地主注册");
        //取消内部默认布局
        setLayout(null);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //设置居中
        setLocationRelativeTo(null);
        //设置置顶
        setAlwaysOnTop(true);
    }

    //创建一个弹框对象
    JDialog jDialog = new JDialog();

    public void showDialog(String content){
        if(!jDialog.isVisible()){
            //把弹框中原来的文字给清空掉。
            jDialog.getContentPane().removeAll();
            JLabel jLabel = new JLabel(content);
            jLabel.setBounds(0,0,400,150);
            jDialog.add(jLabel);
            //给弹框设置大小
            jDialog.setSize(400, 150);
            //要把弹框在设置为顶层 -- 置顶效果
            jDialog.setAlwaysOnTop(true);
            //要让jDialog居中
            jDialog.setLocationRelativeTo(null);
            //让弹框
            jDialog.setModal(true);
            //让jDialog显示出来
            jDialog.setVisible(true);
        }
    }
}
