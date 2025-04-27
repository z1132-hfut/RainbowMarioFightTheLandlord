package RGB.superMario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //当前场景的图片
    private BufferedImage bgImage = null;
    private int sort;
    private boolean flag;


    //用于存放所有障碍物的列表
    private  List<Obtacle> obtacleList = new ArrayList<>();
    public List<Obtacle> getObtacleList() {
        return obtacleList;
    }
    public BackGround() {

    }

    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }
        //判断是否第一关
        if (sort == 1) {
            //绘制第一关的地面，上地面type=1，下地面type=2
            for (int i = 0; i < 27; i++) {
                obtacleList.add(new Obtacle(i * 30, 420, 1, this));
            }
            for (int j = 0; j <= 120; j+=30) {
                for (int i = 0; i < 27; i++) {
                    obtacleList.add(new Obtacle(i * 30, 570 - j, 2, this));
                }

            }
            //绘制砖块A
            for(int i =120 ;i<=150;i+=30){
                obtacleList.add(new Obtacle(i,300,7,this));
            }
            //绘制砖块B
            for(int i=300;i<=570;i+=30){
                if(i==360||i==390||i==480||i==510||i==540){
                    obtacleList.add(new Obtacle(i,300,7,this));
                }else {
                    obtacleList.add(new Obtacle(i,300,0,this));
                }
            }
            //绘制砖块G
            for (int i =420;i<=450;i+=30){
                obtacleList.add(new Obtacle(i,240,7,this));
            }
            //绘制水管
            for (int i=360;i<=600;i+=25){
                if(i==360){
                    obtacleList.add(new Obtacle(620,i,3,this));
                    obtacleList.add(new Obtacle(645,i,4,this));
                }else{ obtacleList.add(new Obtacle(620,i,5,this));
                    obtacleList.add(new Obtacle(645,i,6,this));
                }
            }

        }
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public void setBgImage(BufferedImage bgImage) {
        this.bgImage = bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }
}

