package FightTheLandlord;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import m.GameJFrame.*;
//import m.Poker;

public class Common {
    public Common() {
    }

    //移动牌
    public static void move(Poker poker, Point from, Point to){
        if(to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;
            if (from.x < to.x)
                flag = 20;
            else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;
                poker.setLocation(i, (int) y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        poker.setLocation(to);
    }
    //参数一：游戏界面
    //参数二：要重新摆放数据的集合
    //参数三：标记（0左1中2右
    public static void rePosition(GameJFrame m, ArrayList<Poker> list, int flag) {
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = 60;
        }
        if (flag == 1) {
            p.x = 300;
            p.y = 400;
        }
        if (flag == 2) {
            p.x = 900;
            p.y = 60;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Poker poker = list.get(i);
            move(poker, poker.getLocation(), p);
            m.container.setComponentZOrder(poker, 0);
            if (flag == 1)
                p.x += 21;
            else
                p.y += 15;
        }
    }

    //排！
    public static void order(ArrayList<Poker> list) {
        Collections.sort(list, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                //获取花色和价值
                int a1 = Integer.parseInt(o1.getName().substring(0, 1));
                int a2 = Integer.parseInt(o2.getName().substring(0, 1));
                //获取数字和价值
                int b1 = Integer.parseInt(o1.getName().substring(2));
                int b2 = Integer.parseInt(o2.getName().substring(2));

                //大小王
                if (a1 == 5) {
                    b1 += 100;
                }
                if (a2 == 5) {
                    b2 += 100;
                }

                //倒序排序
                int flag = b2 - b1;
                //价值一样的排序
                if (flag == 0) {
                    return a2 - a1;
                } else {
                    return flag;
                }
            }
        });
    }



    public static int getScore(ArrayList<Poker> list) {
        int count = 0;
        int i = 0;

        for(int len = list.size(); i < len; ++i) {
            Poker poker = (Poker)list.get(i);
            if (poker.getName().substring(0, 1).equals("5")) {
                count += 5;
            }

            if (poker.getName().substring(2).equals("2")) {
                count += 2;
            }
        }

        return count;
    }

    public static int getColor(Poker poker) {
        return Integer.parseInt(poker.getName().substring(0, 1));
    }

    public static int getValue(Poker poker) {
        int i = Integer.parseInt(poker.getName().substring(2));
        if (poker.getName().substring(2).equals("2")) {
            i += 13;
        }

        if (poker.getName().substring(2).equals("1")) {
            i += 13;
        }

        if (getColor(poker) == 5) {
            i += 2;
        }

        return i;
    }
}