package FightTheLandlord;
//import m.GameJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Poker extends JLabel implements MouseListener {
    //属性

    FightTheLandlord.GameJFrame gameJFrame;
    private String name;//花色数字
    private boolean up;//正反
    private boolean canClick = false;//是否能点击
    private boolean clicked = false;


    public Poker(
            FightTheLandlord.GameJFrame m, String name, boolean up){
        this.gameJFrame = m;
        this.name = name;
        this.up = up;

        //判断正反
        if(this.up){
            turnFront();
        }else {
            turnRear();
        }
        //设置宽高
        this.setSize(70, 100);
        //显示牌
        this.setVisible(true);
        //监听每张牌
        this.addMouseListener(this);
    }
    public Poker() {
    }

    public Poker(
            FightTheLandlord.GameJFrame gameJFrame, String name, boolean up, boolean canClick, boolean clicked) {
        this.gameJFrame = gameJFrame;
        this.name = name;
        this.up = up;
        this.canClick = canClick;
        this.clicked = clicked;
    }

    //显示正面
    public void turnFront(){
        //设置正面
        this.setIcon(new ImageIcon("poker\\"+name+".jpg"));
        //修改成员变量
        this.up = true;
    }
    //显示反面
    public void turnRear(){
        //设置反面
        this.setIcon(new ImageIcon("poker\\牌底.jpg"));
        //修改成员变量
        this.up = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //判断能否被点击
        if(canClick){
            int step = 0;//牌的位移像素
            if(clicked){
                step = 20;
            }else {
                step = -20;
            }
            //修改clicked变量记录的值
            clicked = !clicked;
            //修改牌的值
            Point from = this.getLocation();
            //创建一个Point表示结束位置
            Point to = new Point(from.x,from.y + step);
            //把最新位置设置给牌
            this.setLocation(to);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public
    FightTheLandlord.GameJFrame getGameJFrame() {
        return this.gameJFrame;
    }

    public void setGameJFrame(
            FightTheLandlord.GameJFrame gameJFrame) {
        this.gameJFrame = gameJFrame;
    }

    /**
     * 获取
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * 设置
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取
     * @return up
     */
    public Boolean isUp(){
        return this.up;
    }
    /**
     * 设置
     * @param up
     */
    public void setUp(Boolean up){
        this.up = up;
    }
    /**
     * 获取
     * @return canClick
     */
    public Boolean iscanClick(){
        return canClick;
    }
    /**
     * 设置
     * @param canClick
     */
    public void setcanClick(Boolean canClick){
        this.canClick = canClick;
    }
    /**
     * 获取
     * @return clicked
     */
    public Boolean isclicked(){
        return clicked;
    }
    /**
     * 设置
     * @param clicked
     */
    public void setclicked(Boolean clicked){
        this.clicked = clicked;
    }
    public  String toString(){
        String var10000 = String.valueOf(this.gameJFrame);
        return "Poker{gameJFrame = " + var10000 + ", name = " + this.name + ", up = " + this.up + ", canClick = " + this.canClick + ", clicked = " + this.clicked + "}";
    }
}