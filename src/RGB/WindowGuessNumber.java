package RGB;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowGuessNumber extends JFrame implements ActionListener {
    int number;
    JLabel hintLabel;
    JTextField inputGuess;
    JButton buttonGetNumber,buttonEnter;

    WindowGuessNumber() {
        //任务栏图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\JavaCode\\FightGroundOwner\\Image\\login\\face.jpg"));

        setLayout(new FlowLayout());//默认布局
        buttonGetNumber = new JButton("接下来我会随便说一个零到一百的数字");
        add(buttonGetNumber);

        hintLabel = new JLabel("来猜猜看吧！",JLabel.CENTER);
        hintLabel.setBackground(Color.cyan);
        add(hintLabel);

        inputGuess = new JTextField("0",10);
        add(inputGuess);

        buttonEnter = new JButton("你确定？");
        add(buttonEnter);

        buttonGetNumber.addActionListener((ActionEvent e)->{
            number = (int)(Math.random()*100)+1;
            hintLabel.setText("你觉得是多少呢？");
        });  //匿名类的实例做监控器，使用了Lambda表达式
        buttonEnter.addActionListener(this);  //窗口做监视器
        setBounds(400,400,560,110);
        this.toFront();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
    }

    public void actionPerformed(ActionEvent e) {
        int guess = 0;
        try {  guess=Integer.parseInt(inputGuess.getText());
            if(guess==number) {
                hintLabel.setText("不错嘛，居然答对了。");
                buttonEnter.setVisible(false);
            }
            else if(guess>number) {
                hintLabel.setText("太大了，再猜！");
                inputGuess.setText(null);
            }
            else if(guess<number) {
                hintLabel.setText("这么小？扣扣索索的，大大方方再蒙个数字。");
                inputGuess.setText(null);
            }
        } catch(NumberFormatException event) {
            hintLabel.setText("说吧，多少？");
        }
    }

    public void gussNumber(){
        WindowGuessNumber win = new WindowGuessNumber();
        win.setTitle("猜不到数字就带不走马里奥的小游戏");
    }

}


