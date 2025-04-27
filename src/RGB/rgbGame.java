package RGB;
//地牢游戏主类

import FightTheLandlord.LoginJFrame;
import LoginAndEnroll.LoginJFrame2;
import RGB.superMario.MyFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class rgbGame implements MouseListener, KeyListener {
    //新建需要的对象
    Mario mario = new Mario(1,10,30,0);
    NPC npc1 = new NPC(false);
    NPC npc2 = new NPC(false);
    Tools tool_iceCubeUpper = new Tools(false,false,330,140);
    Tools tool_flowerUpper = new Tools(false,false,240,230);
    Tools tool_iceCubeDown = new Tools(false,false,240,380);
    Tools tool_flowerDown = new Tools(false,false,240,500);
    Traps trap1a = new Traps(false,330,290);
    Traps trap1b = new Traps(false,330,320);
    Traps trap2a = new Traps(false,510,290);
    Traps trap2b = new Traps(false,540,290);

    int heartNum = 3; //马里奥剩余血格数

    //存储道具和陷阱
    Tools[] tools = {tool_flowerUpper,tool_flowerDown,tool_iceCubeUpper,tool_iceCubeDown};//8,9,10,11
    Traps[] traps = {trap1a,trap1b,trap2a,trap2b};//4,5,6,7

    int[][] maze= {
            //迷宫布局：1：砖块，0：可走的路，
            //2：npc1，3：npc2
            //4:trap1a,5:trap1b,6:trap2a,7:trap2b
            //8:tool_flowerUpper,9:tool_flowerDown,10:tool_iceCubeUpper,11:tool_iceCubeDown
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,10,1,0,0,0,1,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1},
            {1,0,0,0,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,8,0,0,0,1,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,1,1,1,1,1,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,0,0,0,0,4,0,0,0,0,1,6,7,1},
            {1,0,0,0,1,0,0,0,0,0,0,5,0,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,11,0,0,0,0,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1},
            {1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,1,0,0,1,9,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    //创建窗口对象
    JFrame gameJFrame = new JFrame("七彩马里奥");
    //创建菜单对象
    JMenu functionJMenu = new JMenu("选项");
    JMenu helpJMenu = new JMenu("帮助");
    //创建菜单选项对象
    JMenuItem ruleItem = new JMenu("游戏规则");
    JMenuItem againGameItem = new JMenu("重新开始");
    JMenuItem againLoginItem = new JMenu("重新登录");
    JMenuItem exitGameItem = new JMenu("退出游戏");

    //游戏运行主函数
    public void startGame(boolean visible,boolean helpVisible){
        initGameJFrame();
        initJMenu(gameJFrame);
        initPicture(gameJFrame);
        gameJFrame.addKeyListener(this);
        gameJFrame.setVisible(visible);
        initHelp(helpVisible);
    }

    //加载游戏规则对话框
    public void initHelp(boolean f){
        JDialog jDialog = new JDialog();
        ImageIcon icon = new ImageIcon("image\\help.png");
        JLabel jLabel = new JLabel(icon);
        jLabel.setBounds(0,0,322,264);
        jDialog.getContentPane().add(jLabel);
        jDialog.setSize(322,264);
        //置顶
        jDialog.setAlwaysOnTop(true);
        //居中
        jDialog.setLocationRelativeTo(null);
        //不关闭无法进行其他操作
        jDialog.setModal(true);
        jDialog.setVisible(f);

    }

    public void initGameJFrame(){ //初始化主窗口
        gameJFrame.setSize(614,708);
        gameJFrame.setLocationRelativeTo(null);
        gameJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameJFrame.setLayout(null);
    }

    public void initJMenu(JFrame gameFrame){ //初始化菜单
        //创建菜单栏对象
        JMenuBar jMenuBar = new JMenuBar();
        //添加鼠标监听事件
        ruleItem.addMouseListener(this);
        againGameItem.addMouseListener(this);
        againLoginItem.addMouseListener(this);
        exitGameItem.addMouseListener(this);
        //将选项加入到菜单
        functionJMenu.add(againGameItem);
        functionJMenu.add(againLoginItem);
        functionJMenu.add(exitGameItem);
        helpJMenu.add(ruleItem);
        //将菜单加入到菜单栏
        jMenuBar.add(functionJMenu);
        jMenuBar.add(helpJMenu);
        //将菜单栏加入到窗口
        gameJFrame.setJMenuBar(jMenuBar);

    }

    //加载所有图片
    public void initPicture(JFrame gameFrame){
        //将原先的画面清除。否则旧的画面会覆盖新的画面
        gameJFrame.getContentPane().removeAll();
        //加载马里奥，NPC，工具，陷阱，血格的图片
        initMario(gameJFrame);
        initNPCs(gameJFrame);
        initTools(gameJFrame);
        initTraps(gameJFrame);
        initHearts(gameJFrame);
        //加载右上角工具栏中的道具
        initGetTools(gameJFrame);
        //加载背景图片
        ImageIcon backImage = new ImageIcon("image\\back.jpg");
        JLabel backImageJLabel = new JLabel(backImage);
        backImageJLabel.setBounds(0, 0, 600, 650);
        //将背景图片添加到窗口
        gameJFrame.getContentPane().add(backImageJLabel);
        //刷新界面使新的画面显示
        gameJFrame.getContentPane().repaint();
    }

    public void initNPCs(JFrame gameFrame){
        if(!npc1.isIfMakeFriend()){ //先判断有没有被主角获取
            ImageIcon npc1 = new ImageIcon("image\\npc1.jpg");
            JLabel npc1JLabel = new JLabel(npc1);
            npc1JLabel.setBounds(30, 80, 25, 25);
            gameJFrame.getContentPane().add(npc1JLabel);
        }
        if(!npc2.isIfMakeFriend()){
            ImageIcon npc2 = new ImageIcon("image\\npc2.jpg");
            JLabel npc2JLabel = new JLabel(npc2);
            npc2JLabel.setBounds(240, 590, 25, 25);
            gameJFrame.getContentPane().add(npc2JLabel);
        }
    }

    public void initMario(JFrame gameFrame){
        //根据马里奥运动状态加载对应的图片。
        ImageIcon man = new ImageIcon("image\\ma"+mario.getMoveStatus()+".png");
        JLabel manJLabel = new JLabel(man);
        manJLabel.setBounds(mario.getX(), 590-mario.getY(), 30, 30);
        gameJFrame.getContentPane().add(manJLabel);
    }

    public void initTools(JFrame gameFrame){
        if(!tool_iceCubeUpper.isIfGet()){ //先判断有没有被主角获取
            ImageIcon tool1 = new ImageIcon("image\\tool3.png");
            JLabel tool1JLabel = new JLabel(tool1);
            tool1JLabel.setBounds(tool_iceCubeUpper.getX(), tool_iceCubeUpper.getY(), 30, 30);
            gameJFrame.getContentPane().add(tool1JLabel);
        }
        if(!tool_flowerUpper.isIfGet()){
            ImageIcon tool2 = new ImageIcon("image\\tool1.png");
            JLabel tool2JLabel = new JLabel(tool2);
            tool2JLabel.setBounds(tool_flowerUpper.getX(), tool_flowerUpper.getY(), 30, 30);
            gameJFrame.getContentPane().add(tool2JLabel);
        }
        if(!tool_iceCubeDown.isIfGet()){
            ImageIcon tool3 = new ImageIcon("image\\tool4.png");
            JLabel tool3JLabel = new JLabel(tool3);
            tool3JLabel.setBounds(tool_iceCubeDown.getX(), tool_iceCubeDown.getY(), 30, 30);
            gameJFrame.getContentPane().add(tool3JLabel);
        }
        if(!tool_flowerDown.isIfGet()){
            ImageIcon tool4 = new ImageIcon("image\\tool2.png");
            JLabel tool4JLabel = new JLabel(tool4);
            tool4JLabel.setBounds(tool_flowerDown.getX(), tool_flowerDown.getY(), 30, 30);
            gameJFrame.getContentPane().add(tool4JLabel);
        }
    }

    public void initTraps(JFrame gameFrame){
        if(!trap1a.isIfDisappear()){ //先判断有没有被主角清除
            ImageIcon trap1 = new ImageIcon("image\\trap.jpg");
            JLabel trap1JLabel = new JLabel(trap1);
            trap1JLabel.setBounds(330, 290, 30, 30);
            gameJFrame.getContentPane().add(trap1JLabel);
        }
        if(!trap1b.isIfDisappear()){
            ImageIcon trap2 = new ImageIcon("image\\trap.jpg");
            JLabel trap2JLabel = new JLabel(trap2);
            trap2JLabel.setBounds(330, 320, 30, 30);
            gameJFrame.getContentPane().add(trap2JLabel);
        }
        if(!trap2a.isIfDisappear()){
            ImageIcon trap3 = new ImageIcon("image\\trap.jpg");
            JLabel trap3JLabel = new JLabel(trap3);
            trap3JLabel.setBounds(510, 290, 30, 30);
            gameJFrame.getContentPane().add(trap3JLabel);
        }
        if(!trap2b.isIfDisappear()){
            ImageIcon trap4 = new ImageIcon("image\\trap.jpg");
            JLabel trap4JLabel = new JLabel(trap4);
            trap4JLabel.setBounds(540, 290, 30, 30);
            gameJFrame.getContentPane().add(trap4JLabel);
        }
    }

    public void initHearts(JFrame gameFrame){
        for(int i = 0;i<30*heartNum;i+=30){ //根据剩余血格数绘制
            ImageIcon fu = new ImageIcon("image\\heart.jpg");
            JLabel fuJLabel = new JLabel(fu);
            fuJLabel.setBounds(i, 20, 20, 20);
            gameJFrame.getContentPane().add(fuJLabel);
        }
    }

    public void initGetTools(JFrame gameFrame){
        int subjectX = 570;//新获取的道具的初始生成坐标
        for(int i = 0;i<tools.length;i++){
            //判断道具是否被收集且未被使用
            if(tools[i].isIfGet() && !tools[i].isIfUse()){
                //绘制相应的图片
                ImageIcon tool = new ImageIcon("image\\tool"+(i+1)+".png");
                JLabel toolJLabel = new JLabel(tool);
                toolJLabel.setBounds(subjectX, 10, 30, 30);
                gameJFrame.getContentPane().add(toolJLabel);
                //每绘制一个道具，新获取道具的生成坐标向左移动35个像素，避免重叠
                subjectX-=35;
            }
        }
    }



    public boolean ifWin(){ //游戏输赢判断
        //走到终点且获取两个NPC才获胜
        return heartNum > 0 && npc1.isIfMakeFriend() &&
                npc2.isIfMakeFriend() && mario.getX() == 570 && mario.getY() == 510;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(heartNum>0 && !ifWin()){ //没有剩余生命和游戏胜利时不能移动
            if (e.getKeyCode() == 38) { //左：37  上：38  右：39  下：40
                //判断是否未超出上边框且能向上碰不到障碍物
                if(mario.getY()<=530 && ifCanGoU()){
                    //人物上移
                    mario.setY(mario.getY()+mario.getSpeed());
                }else if (mario.getY() <= 530 && ifCanGoUTraps()) { //判断是否碰到陷阱或花道具
                    //碰到陷阱或花道具时会被弹开一段距离并扣血
                    heartNum--;
                    mario.setY(mario.getY() - mario.getSpeed() * 4);
                    //人物回复站立状态（正数面朝右，负数面朝左）
                    int n = mario.getMoveStatus() > 0 ? 2 : -2;
                    mario.setMoveStatus(n);
                    //更新图片
                    initPicture(gameJFrame);
                }
                //先判断人物面朝向，再更新相应动作
                if(mario.getMoveStatus()>0){//如果面朝右
                    //根据运动状态不断刷新和图片不断重新绘制实现跑动动画效果
                    if(mario.getMoveStatus()==2){//如果当前运动状态是2（向右跑动作1）
                        //将运动状态切换到23（向右跑动作2）
                        mario.setMoveStatus(23);
                        //更新图片
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==23){//如果当前运动状态是23（向右跑动作2）
                        //将运动状态切换到3（向右跑动作3）
                        mario.setMoveStatus(3);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==3){//如果当前运动状态是3（向右跑动作3）
                        //将运动状态切换到32（向右跑动作2）
                        mario.setMoveStatus(32);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==32){//如果当前运动状态是32（向右跑动作2）
                        //将运动状态切换到2（向右跑动作1）
                        mario.setMoveStatus(2);
                        initPicture(gameJFrame);
                    }else{
                        //其他运动状态：统一切换为2（向右跑动作1）
                        mario.setMoveStatus(2);
                        initPicture(gameJFrame);
                    }
                }else if(mario.getMoveStatus()<0){//如果面朝左。同理
                    if(mario.getMoveStatus()==-2){
                        mario.setMoveStatus(-23);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-23){
                        mario.setMoveStatus(-3);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-3){
                        mario.setMoveStatus(-32);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-32){
                        mario.setMoveStatus(-2);
                        initPicture(gameJFrame);
                    }else{
                        mario.setMoveStatus(-2);
                        initPicture(gameJFrame);
                    }
                }
            }
            if (e.getKeyCode() == 39) { //左：37  上：38  右：39  下：40
                //判断是否未超出右边框且能向右碰不到障碍物
                if(mario.getX()<570 && ifCanGoR()){
                    //人物右移
                    mario.setX(mario.getX()+mario.getSpeed());
                }else if (mario.getY() < 570 && ifCanGoRTraps()) {
                    heartNum--;
                    mario.setX(mario.getX() - mario.getSpeed() * 4);
                    int n = mario.getMoveStatus() > 0 ? 2 : -2;
                    mario.setMoveStatus(n);
                    initPicture(gameJFrame);
                }
                //运动动画切换
                if(mario.getMoveStatus()==2){
                    mario.setMoveStatus(23);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==23){
                    mario.setMoveStatus(3);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==3){
                    mario.setMoveStatus(32);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==32){
                    mario.setMoveStatus(2);
                    initPicture(gameJFrame);
                }else{
                    mario.setMoveStatus(2);
                    initPicture(gameJFrame);
                }
            }
            //向左和向右同理
            if(e.getKeyCode() == 37) { //左：37  上：38  右：39  下：40
                if (mario.getX() > 0 && ifCanGoL()) {
                    mario.setX(mario.getX()-mario.getSpeed());
                }else if (mario.getX() > 0 && ifCanGoLTraps()) {
                    heartNum--;
                    mario.setX(mario.getX() + mario.getSpeed() * 4);
                    int n = mario.getMoveStatus() > 0 ? 2 : -2;
                    mario.setMoveStatus(n);
                    initPicture(gameJFrame);
                }
                if(mario.getMoveStatus()==-2){
                    mario.setMoveStatus(-23);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==-23){
                    mario.setMoveStatus(-3);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==-3){
                    mario.setMoveStatus(-32);
                    initPicture(gameJFrame);
                }else if(mario.getMoveStatus()==-32){
                    mario.setMoveStatus(-2);
                    initPicture(gameJFrame);
                }else{
                    mario.setMoveStatus(-2);
                    initPicture(gameJFrame);
                }
            }
            //按g键收集特殊道具
            if(e.getKeyCode() == 71){ //对应键盘上的g键
                int n = -1;//如果主角左边或右边存在大于0的坐标，则将其赋值给n
                //判断主角正左或正右的位置对应的坐标是不是障碍物。不是的话更新n
                if((mario.getX()+30)%30 == 0 && (540 - mario.getY())%30 == 0){
                    if(maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1]>1){
                        n = maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1];
                    }
                    if(maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1]>1){
                        n = maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1];
                    }
                }
                //如果是特殊道具
                if(n >= 8 && n<=11){
                    //更新抬手的图片。先判断主角面朝向
                    int n1 = mario.getMoveStatus()>0?6:-6;
                    mario.setMoveStatus(n1);
                    //更新道具状态
                    tools[n-8].setIfGet(true);
                    //将道具的坐标对应的值变成0
                    maze[(tools[n-8].getY()/30)-1][tools[n-8].getX()/30] = 0;
                    initPicture(gameJFrame);
                }
            }
            //按k使用特殊道具
            if(e.getKeyCode() == 75){
                int n = -1;//如果主角左边或右边存在大于0的坐标，则将其赋值给n
                //判断主角正左或正右的位置对应的坐标是不是是不是障碍物。不是的话更新n
                if((mario.getX()+30)%30 == 0 && (540 - mario.getY())%30 == 0){
                    if(maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1]>1){
                        n = maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1];
                    }
                    if(maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1]>1){
                        n = maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1];
                    }
                    if(maze[((570 - mario.getY())/30)][(mario.getX() / 30)]>1){
                        n = maze[((570 - mario.getY())/30)][(mario.getX() / 30)];
                    }
                    if(maze[((570 - mario.getY())/30)-2][(mario.getX() / 30)]>1){
                        n = maze[((570 - mario.getY())/30)-2][(mario.getX() / 30)];
                    }
                }
                //如果是陷阱
                if(n >= 4 && n<=7){
                    int s = mario.getMoveStatus()>0?6:-6;
                    mario.setMoveStatus(s);
                    //判断冰块道具1是否获取了未使用
                    if(tool_iceCubeUpper.isIfGet() && !tool_iceCubeUpper.isIfUse()){
                        traps[n-4].setIfDisappear(true);
                        tool_iceCubeUpper.setIfUse(true);
                        maze[(traps[n-4].getY()/30)-1][traps[n-4].getX()/30] = 0;
                        initPicture(gameJFrame);
                    }
                    //判断冰块道具2是否获取了未使用
                    if(tool_iceCubeDown.isIfGet() && !tool_iceCubeDown.isIfUse()){
                        traps[n-4].setIfDisappear(true);
                        tool_iceCubeDown.setIfUse(true);
                        maze[(traps[n-4].getY()/30)-1][traps[n-4].getX()/30] = 0;
                        initPicture(gameJFrame);
                    }
                    //如果是npc1且获得过上面的道具花：
                }else if(n == 2 && tool_flowerUpper.isIfGet() && !tool_flowerUpper.isIfUse()){
                    //弹出超级马里奥小游戏
                    new MyFrame();
                    //更新道具和npc1状态
                    tool_flowerUpper.setIfUse(true);
                    maze[1][1] = 0;
                    npc1.setIfMakeFriend(true);
                    //如果是npc2且获得过下面的道具花：
                }else if(n == 3 && tool_flowerDown.isIfGet() && !tool_flowerDown.isIfUse()){
                    //弹出猜数字小游戏
                    new WindowGuessNumber();
                    //更新道具和npc2状态
                    tool_flowerDown.setIfUse(true);
                    maze[18][8] = 0;
                    npc2.setIfMakeFriend(true);
                }
            }
            if (e.getKeyCode() == 40) { //左：37  上：38  右：39  下：40
                //与向上走同理
                if(mario.getY()>-30 && ifCanGoD()) {//当前主角坐标不靠近下边框
                    mario.setY(mario.getY()-mario.getSpeed());
                }else if (mario.getY() > -30 && ifCanGoDTraps()) {
                    heartNum--;
                    mario.setY(mario.getY() + mario.getSpeed() * 4);
                    int n = mario.getMoveStatus() > 0 ? 2 : -2;
                    mario.setMoveStatus(n);
                    initPicture(gameJFrame);
                }
                if(mario.getMoveStatus()>0){
                    if(mario.getMoveStatus()==2){
                        mario.setMoveStatus(23);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==23){
                        mario.setMoveStatus(3);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==3){
                        mario.setMoveStatus(32);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==32){
                        mario.setMoveStatus(2);
                        initPicture(gameJFrame);
                    }else{
                        mario.setMoveStatus(2);
                        initPicture(gameJFrame);
                    }
                }else if(mario.getMoveStatus()<0){
                    if(mario.getMoveStatus()==-2){
                        mario.setMoveStatus(-23);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-23){
                        mario.setMoveStatus(-3);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-3){
                        mario.setMoveStatus(-32);
                        initPicture(gameJFrame);
                    }else if(mario.getMoveStatus()==-32){
                        mario.setMoveStatus(-2);
                        initPicture(gameJFrame);
                    }else{
                        mario.setMoveStatus(-2);
                        initPicture(gameJFrame);
                    }
                }
            }
            //如果没有剩余生命了
        }else if(heartNum == 0){
            //切换死亡状态（分向左和向右两种）
            int n = mario.getMoveStatus()>0?5:-5;
            mario.setMoveStatus(n);
            initPicture(gameJFrame);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(ifWin()){
            //关闭原窗口，弹出斗地主窗口
            startGame(false,false);
            new LoginJFrame();
        }
        //如果游戏未结束
        if(heartNum>0 && !ifWin()){
            //运动完后将运动状态切换到站立状态。
            if(e.getKeyCode() == 37){
                mario.setMoveStatus(-1);
                initPicture(gameJFrame);
            }
            if(e.getKeyCode() == 39){
                mario.setMoveStatus(1);
                initPicture(gameJFrame);
            }
            if(e.getKeyCode() == 38){
                int n = mario.getMoveStatus()>0?1:-1;
                mario.setMoveStatus(n);
                initPicture(gameJFrame);
            }
            if(e.getKeyCode() == 40){
                int n = mario.getMoveStatus()>0?1:-1;
                mario.setMoveStatus(n);
                initPicture(gameJFrame);
            }
            if(e.getKeyCode() == 71){ //对应键盘上的g键
                int n = mario.getMoveStatus()>0?1:-1;
                mario.setMoveStatus(n);
                initPicture(gameJFrame);
            }
            if(e.getKeyCode() == 75){ //对应键盘上的k键
                int n = mario.getMoveStatus()>0?1:-1;
                mario.setMoveStatus(n);
                initPicture(gameJFrame);
            }
            //死亡状态不改变。
        }else if(heartNum == 0){
            int n = mario.getMoveStatus()>0?5:-5;
            mario.setMoveStatus(n);
            initPicture(gameJFrame);
            //弹出失败对话框：
            JDialog jDialog = new JDialog();
            ImageIcon icon = new ImageIcon("image\\GameOver.png");
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(0,0,329,107);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(329,107);
            //置顶
            jDialog.setAlwaysOnTop(true);
            //居中
            jDialog.setLocationRelativeTo(null);
            //不关闭无法进行其他操作
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }

    boolean ifCanGoL(){
        //根据坐标将地图分为若干个30*30大小的方格
        if((mario.getX()+30)%30 == 0){ //如果人物图片左右边框在方格交界线处
            if((540 - mario.getY())%30 == 0){ //如果人物图片上下边框在方格交界线处
                //只需判断一个坐标点是否为零
                return maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1] == 0;
            }else{
                //需判断两个坐标点是否为零
                return maze[(540 - mario.getY())/30][(mario.getX() / 30) - 1] == 0
                        && maze[(540 - mario.getY()+30)/30][(mario.getX() / 30) - 1] == 0;
            }
        }else{ //人物图片左右边框不在交界线处
            //当前格子未走完，可以继续走
            return true;
        }
    }
    boolean ifCanGoR(){//和向左走同理
        if((mario.getX() / 30) + 1>19){
            return false;
        }else{
            if((mario.getX()+30)%30 == 0){ //如果人物图片左右边框在方格交界线处
                if((540 - mario.getY())%30 == 0){ //如果人物图片上下边框在方格交界线处
                    return maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1] == 0;
                }else{
                    return maze[(540 - mario.getY())/30][(mario.getX() / 30) + 1] == 0
                            && maze[(540 - mario.getY()+30)/30][(mario.getX() / 30) + 1] == 0;
                }
            }else{ //人物图片左右边框不在交界线处
                return true;
            }
        }

    }
    boolean ifCanGoU(){
        if((570 - mario.getY()+30)%30 == 0){////如果人物图片上下边框在方格交界线处
            if((mario.getX()+30)%30 == 0){//如果人物图片左右边框在方格交界线处
                return maze[((540 - mario.getY()) / 30)-1][mario.getX() / 30] == 0;
            }else{
                return maze[((540 - mario.getY()) / 30)-1][mario.getX() / 30] == 0
                        && maze[((540 - mario.getY()) / 30)-1][(mario.getX() / 30)+1] == 0;
            }
        }else{
            return true;
        }
    }
    boolean ifCanGoD(){
        if((570 - mario.getY()+30)%30 == 0){//如果人物图片上下边框在方格交界线处
            if((mario.getX()+30)%30 == 0){//如果人物图片左右边框在方格交界线处
                return maze[((540 - mario.getY()) / 30)+1][mario.getX() / 30] == 0;
            }else{
                return maze[((540 - mario.getY()) / 30)+1][mario.getX() / 30] == 0
                        && maze[((540 - mario.getY()) / 30)+1][(mario.getX() / 30)+1] == 0;
            }
        }else{
            return true;
        }
    }
    //判断是否碰到陷阱或道具花：
    boolean ifCanGoLTraps() {
        if ((mario.getX() + 30) % 30 == 0) { //如果人物图片左右边框在交界线处
            if ((540 - mario.getY()) % 30 == 0) { //如果人物图片上下边框在交界线处
                return (maze[(540 - mario.getY()) / 30][(mario.getX() / 30) -1] >=4)&&
                        (maze[(540 - mario.getY()) / 30][(mario.getX() / 30)- 1]<=9);
            } else {
                return (maze[(540 - mario.getY()) / 30][(mario.getX() / 30) -1] >=4&&
                        maze[(540 - mario.getY()) / 30][(mario.getX() / 30) -1] <=9)
                        || (maze[(540 - mario.getY() + 30) / 30][(mario.getX() / 30) - 1] >=4)&&
                        (maze[(540 - mario.getY() + 30) / 30][(mario.getX() / 30) - 1]<=9);
            }
        } else { //人物图片左右边框不在交界线处
            return false;
        }
    }
    boolean ifCanGoRTraps() {
        if((mario.getX() / 30) + 1 >19){
            return false;
        }else{
            if ((mario.getX() + 30) % 30 == 0) { //如果人物图片左右边框在交界线处
                if ((540 - mario.getY()) % 30 == 0) { //如果人物图片上下边框在交界线处
                    return (maze[(540 - mario.getY()) / 30][(mario.getX() / 30) + 1] >=4)&&
                            (maze[(540 - mario.getY()) / 30][(mario.getX() / 30) + 1]<=9);
                } else {
                    return (maze[(540 - mario.getY()) / 30][(mario.getX() / 30) + 1] >=4&&
                            maze[(540 - mario.getY()) / 30][(mario.getX() / 30) + 1] <=9)
                            &&(maze[(540 - mario.getY() + 30) / 30][(mario.getX() / 30) + 1] >=4)&&
                            (maze[(540 - mario.getY() + 30) / 30][(mario.getX() / 30) + 1]<=9);
                }
            } else { //人物图片左右边框不在交界线处
                return false;
            }
        }
    }
    boolean ifCanGoUTraps() {
        if ((570 - mario.getY() + 30) % 30 == 0) {
            if ((mario.getX() + 30) % 30 == 0) {
                return maze[((540 - mario.getY()) / 30) - 1][mario.getX() / 30] >=4&&
                        maze[((540 - mario.getY()) / 30) - 1][mario.getX() / 30]<=9;
            } else {
                return maze[((540 - mario.getY()) / 30) - 1][mario.getX() / 30] >=4&&
                        maze[((540 - mario.getY()) / 30) - 1][mario.getX() / 30]<=9
                        || maze[((540 - mario.getY()) / 30) - 1][(mario.getX() / 30) + 1] >=4&&
                        maze[((540 - mario.getY()) / 30) - 1][(mario.getX() / 30) + 1] <=9;
            }
        } else {
            return false;
        }
    }
    boolean ifCanGoDTraps() {
        if ((570 - mario.getY() + 30) % 30 == 0) {
            if ((mario.getX() + 30) % 30 == 0) {
                return maze[((540 - mario.getY()) / 30) +1][mario.getX() / 30] >=4&&
                        maze[((540 - mario.getY()) / 30)+ 1][mario.getX() / 30]<=9;
            } else {
                return maze[((540 - mario.getY()) / 30) +1][mario.getX() / 30] >=4&&
                        maze[((540 - mario.getY()) / 30)+ 1][mario.getX() / 30]<=9
                        || maze[((540 - mario.getY()) / 30) + 1][(mario.getX() / 30) + 1] >=4&&
                        maze[((540 - mario.getY()) / 30) + 1][(mario.getX() / 30) + 1] <=9;
            }
        } else {
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == againGameItem){//如果点击了重新开始
            //更新所有状态
            mario.setX(30);
            mario.setY(0);
            mario.setMoveStatus(1);
            npc1.setIfMakeFriend(false);
            npc2.setIfMakeFriend(false);
            tool_iceCubeUpper.setIfGet(false);
            tool_flowerUpper.setIfGet(false);
            tool_iceCubeDown.setIfGet(false);
            tool_flowerDown.setIfGet(false);
            tool_iceCubeUpper.setIfUse(false);
            tool_flowerUpper.setIfUse(false);
            tool_iceCubeDown.setIfUse(false);
            tool_flowerDown.setIfUse(false);
            trap1a.setIfDisappear(false);
            trap1b.setIfDisappear(false);
            trap2a.setIfDisappear(false);
            trap2b.setIfDisappear(false);
            heartNum=3;
            int[][] mazeNew= {
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,10,1,0,0,0,1,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1},
                    {1,0,0,0,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,8,0,0,0,1,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,1,1,1,1,1,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,0,0,0,0,4,0,0,0,0,1,6,7,1},
                    {1,0,0,0,1,0,0,0,0,0,0,5,0,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,11,0,0,0,0,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1},
                    {1,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1},
                    {1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,1,0,0,1,9,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1},
                    {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            };
            maze = mazeNew;
            //重新绘制画面
            initPicture(gameJFrame);
        }else if(obj == againLoginItem){
            //重新登录：
            startGame(false,false);
            try {
                new LoginJFrame2();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if(obj == exitGameItem){
            //退出游戏
            System.exit(0);
        } else if (obj ==ruleItem) {
            //显示游戏规则
            JDialog jDialog = new JDialog();
            ImageIcon icon = new ImageIcon("image\\help.png");
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(0,0,322,264);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(322,264);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭就无法执行其他操作：
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
