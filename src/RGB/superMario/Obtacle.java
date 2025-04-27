package RGB.superMario;

import java.awt.image.BufferedImage;

public class Obtacle {
    //用于表示坐标
    private int x;
    private int y;
    //用于记录障碍物的类型
    private int type;
    //用于显示图像
    private BufferedImage show = null;
    private BackGround bg = null;
    public Obtacle(int x,int y,int type,BackGround bg){//构造函数
        this.x=x;
        this.y=y;
        this.type=type;
        this.bg=bg;
        show = StaticValue.obstacle.get(type);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }
}

