package FightTheLandlord;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;


public class GameJFrame extends JFrame{
    public static Container container = null;
    ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();
    ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();

    //底牌
    ArrayList<Poker> lordList = new ArrayList<>();

    //牌盒，装所有的牌
    ArrayList<Poker> pokerList = new ArrayList();


    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private GameJFrame.Menu menuBar;



    public GameJFrame() {

        // 初始化当前JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("login/Face.jpg"));
        this.setVisible(true); // 通常在设置为可见后，不应立即进行重绘或重排等操作
        // 由于我们在LoginJFrame中已经通过EventQueue.invokeLater延迟了GameJFrame的创建和显示
        // 所以这里的initCard调用不需要额外的延迟处理
        new Thread(this::initCard).start(); // 在后台线程中初始化扑克牌

        this.setTitle("七彩马里奥斗地主！  ");
        this.setSize(1140, 570); // 宽高
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        //就是默认布局


        // 初始化container(免得你又净报错)
        container = this.getContentPane();  // 使用JFrame的getContentPane方法获取内容面板

        // 初始化并添加菜单
        menuBar = new GameJFrame.Menu("窗口2", 0, 0, 1140, 570); // 假设 菜单应该占据整个窗口
        this.setJMenuBar(menuBar.getJMenuBar()); // 添加菜单到当前JFrame
        initView();

    }
    //添加组件
    public void initView() {

        // 创建并添加按钮
        button1 = new JButton("抢地主");
        button1.setBounds(380,270,90,30);
        this.add(button1);

        button2 = new JButton("不   要");
        button2.setBounds(580,270,90,30);
        this.add(button2);

        button3 = new JButton("出牌");
        button3.setBounds(400,320,70,30);
        this.add(button3);

        button4 = new JButton("不出");
        button4.setBounds(580,320,70,30);
        this.add(button4);

        this.setVisible(true);
    }

    // 将Menu类的getMenuBar方法改为public，以便外部访问
    class Menu extends JFrame {
        // 创建菜单栏
        JMenuBar menubar;
        JMenu menu, subMenu;
        JMenuItem item1, item2;

        public Menu(String s, int x, int y, int w, int h) {
            init(s);
            this.setLocation(x, y);
            this.setSize(w, h);
            // 这里的setVisible和setDefaultCloseOperation对于Menu类来说是不必要的
            // 因为Menu是作为JMenuBar的一部分添加到Game窗口中的
            // 所以这些设置应该在Game窗口中进行
        }

        void init(String s) {
            setTitle(s); // 想设就设了
            menubar = new JMenuBar();
            menu = new JMenu("Tips");
            subMenu = new JMenu("Background");
            item1 = new JMenuItem("A Present", new ImageIcon("a.gif"));
            item2 = new JMenuItem("Skills", new ImageIcon("b.gif"));
            item1.setAccelerator(KeyStroke.getKeyStroke('A'));
            item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            menu.add(item1);
            menu.addSeparator();
            item1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 显示一个对话框来显示文字
                    JOptionPane.showMessageDialog(GameJFrame.this, "这个礼物就是：   这款小游戏全都是创新点！");
                }
            });

            menu.add(item2);
            menu.add(subMenu);
            item2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 显示一个对话框来显示文字
                    JOptionPane.showMessageDialog(GameJFrame.this, "斗地主的技巧还要看提示？好心的我帮你节省内存，都删掉啦！");
                }
            });

            JMenuItem item3 = subMenu.add(new JMenuItem("Characters", new ImageIcon("c.gif")));
            item3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 显示一个对话框来显示文字
                    JOptionPane.showMessageDialog(GameJFrame.this, "这里有马里奥和马里奥和马里奥。");
                }
            });
            subMenu.add(item3);
            JMenuItem item4 = subMenu.add(new JMenuItem("story", new ImageIcon("d.gif")));
            item4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 显示一个对话框来显示文字
                    JOptionPane.showMessageDialog(GameJFrame.this, "历经千辛万苦，马里奥终于救出了他的伙伴，逃出地宫。但在终点等待他们的却是......");
                }
            });
            subMenu.add(item4);
            menubar.add(menu);
            setJMenuBar(menubar);
        }
    }
    //初始化牌
    //准备牌，洗牌，发牌
    public void initCard() {
        //准备牌
        //把所有的牌，包括大小王都添加到牌盒cardList当中
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    Poker poker = new Poker(this, i + "-" + j, false);
                    poker.setLocation(500, 150);

                    pokerList.add(poker);
                    container.add(poker);
                }
            }
        }

        //洗牌
        Collections.shuffle(pokerList);

        //创建三个集合用来装三个玩家的牌，并把三个小集合放到大集合中方便管理
        ArrayList<Poker> player0 = new ArrayList<>();
        ArrayList<Poker> player1 = new ArrayList<>();
        ArrayList<Poker> player2 = new ArrayList<>();

        for (int i = 0; i < pokerList.size(); i++) {
            //获取当前遍历的牌
            Poker poker = pokerList.get(i);

            //发三张底牌
            if (i <= 2) {
                //移动牌
                Common.move(poker, poker.getLocation(), new Point(420 + (75 * i), 10));
                //把底牌添加到集合中
                lordList.add(poker);
                continue;
            }
            //给三个玩家发牌
            if (i % 3 == 0) {
                //给左边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(50, 60 + i * 5));
                player0.add(poker);
            } else if (i % 3 == 1) {
                //给中间的自己发牌
                Common.move(poker, poker.getLocation(), new Point(300 + i * 7, 400));
                player1.add(poker);
                //把自己的牌展示正面
                poker.turnFront();

            } else if (i % 3 == 2) {
                //给右边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(900, 60 + i * 5));
                player2.add(poker);
            }
            //把三个装着牌的小集合放到大集合中方便管理
            playerList.add(player0);
            playerList.add(player1);
            playerList.add(player2);

            //把当前的牌至于最顶端，这样就会有牌依次错开且叠起来的效果
            container.setComponentZOrder(poker, 0);

        }

        //给牌排序
        for (int i = 0; i < 3; i++) {
            //排序
            Common.order(playerList.get(i));
            //重新摆放顺序
            Common.rePosition(this, playerList.get(i), i);
        }
    }

}