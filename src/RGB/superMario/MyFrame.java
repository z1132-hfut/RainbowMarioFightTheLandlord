package RGB.superMario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, Runnable {
    private List<BackGround> allBg = new ArrayList<>();//用于存储所有的背景
    private BackGround nowBg = new BackGround();
    //双缓存
    private Image offScreenImage = null;
    private Mario2 mario2 = new Mario2();
    private Thread thread = new Thread(this);

    public MyFrame() {
        this.setSize(800, 600);//设置大小800*600
        this.setLocationRelativeTo(null);//设置居中
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置点击窗口上的关闭键，结束程序
        this.setResizable(false);// 设置窗口大小不可变
        this.addKeyListener(this);
        this.setTitle("伙伴的考验");
        StaticValue.init();//初始化图片
        mario2 = new Mario2(10, 365);
        for (int i = 1; i <= 3; i++) {//创建全部的场景
            allBg.add(new BackGround(i, i == 3 ? true : false));
        }
        //为第一个场景设置为当前背景s
        nowBg = allBg.get(0);
        mario2.setBackGround(nowBg);
        //绘制图像
        repaint();
        thread.start();
        /*try {
            new Music();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }*/
    }


    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);
        //绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);
        //绘制障碍物
        for (Obtacle ob : nowBg.getObtacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }
        graphics.drawImage(mario2.getShow(), mario2.getX(), mario2.getY(), this);
        //将图像绘制到窗口中
        g.drawImage(offScreenImage, 0, 0, this);
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        MyFrame myFrame = new MyFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//向右移动
        if (e.getKeyCode() == 39) {
            mario2.rightMove();
        }
        //向左移动
        if (e.getKeyCode() == 37) {
            mario2.leftMove();
        }
//跳跃
        if(e.getKeyCode()==38){
            mario2.jump();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            mario2.leftStop();//左停
        }
        if (e.getKeyCode() == 39) {
            mario2.rightStop();//右停
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);
                if (mario2.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario2.setBackGround(nowBg);
                    mario2.setX(10);
                    mario2.setY(395);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

