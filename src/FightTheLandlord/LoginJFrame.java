package FightTheLandlord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton start = new JButton();

    public LoginJFrame() {
        // 初始化界面
        initJFrame();
        // 初始化组件
        initView();
        // 显示当前页面
        this.setVisible(true);

    }

    // 在这个界面中添加内容
    public void initView() {
        //任务栏图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("loginFTL\\face.jpg"));

        // 开始按钮
        start.setBounds(0, 0, 1140, 570);
        try {
            this.start.setIcon(new ImageIcon("loginFTL\\startBg.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 去除按钮边框
        start.setBorderPainted(false);
        // 去除按钮背景
        start.setContentAreaFilled(false);
        // 绑定按钮事件
        start.addMouseListener(this);
        this.getContentPane().add(start);

        //
        //JLabel background = new JLabel(new ImageIcon("Image/loginFTL/开始界面.jpg"));
        //background.setBounds(0, 0, 1140, 570);
        //this.getContentPane().add(background);

    }

    public void initJFrame() {
        this.setSize(1140, 570); // 宽高
        this.setTitle("七彩马里奥斗地主！");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component) null);
        this.setAlwaysOnTop(true); // 始终置顶
        this.setLayout(null); // 取消默认内部居中放置
    }



    // 点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == start) {
            this.setVisible(false);
            EventQueue.invokeLater(() -> {
                try {
                    GameJFrame gameJFrame = new GameJFrame();
                    gameJFrame.setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    //按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == start) {
            this.start.setIcon(new ImageIcon("loginFTL\\加载.gif"));
        }
    }

    //松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == start) {
            this.start.setIcon(new ImageIcon("loginFTL\\gameBg.jpg"));
        }
    }

    //鼠标划入划出
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(1140, 570);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        JLabel warning = new JLabel(content, SwingConstants.CENTER);
        warning.setBounds(0, 0, 1140, 570);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }
}