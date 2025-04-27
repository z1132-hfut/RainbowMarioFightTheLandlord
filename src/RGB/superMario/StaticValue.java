package RGB.superMario;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue<i> {
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    public static BufferedImage jump_L = null;//左跳
    public static BufferedImage jump_R = null;//右跳
    public static BufferedImage stand_L = null;//左站
    public static BufferedImage stand_R = null;//右站
    public static BufferedImage tower = null;//塔
    public static BufferedImage gan = null; //旗杆
    public static List<BufferedImage> obstacle = new ArrayList<>();
    public static List<BufferedImage> run_L = new ArrayList<>();
    public static List<BufferedImage> run_R = new ArrayList<>();
    public static List<BufferedImage> flower = new ArrayList<>();
    public static List<BufferedImage> mogu = new ArrayList<>();

    public static void init() {
        try {
            bg = ImageIO.read(new File("Image/bg.png"));
            bg2 = ImageIO.read(new File("Image/bg2.png"));
            stand_L = ImageIO.read(new File("Image/s_mario_stand_L.png"));
            stand_R = ImageIO.read(new File("Image\\s_mario_stand_R.png"));
            jump_L = ImageIO.read(new File("Image\\s_mario_jump1_L.png"));
            jump_R = ImageIO.read(new File("Image\\s_mario_jump1_R.png"));
            tower = ImageIO.read(new File("Image\\tower.png"));
            gan = ImageIO.read(new File("Image\\gan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i = 0;i<=2;i++){
            try {
                run_L.add(ImageIO.read(new File( "Image\\s_mario_run"+i+"_L.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0;i<=2;i++){
            try {
                run_R.add(ImageIO.read(new File( "Image/s_mario_run"+i+"_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {//加载障碍物
            obstacle.add(ImageIO.read(new File("Image\\brick.png")));
            obstacle.add(ImageIO.read(new File("Image\\soil_up.png")));
            obstacle.add(ImageIO.read(new File("Image\\soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=1;i<=4;i++){//加载水管
            try {
                obstacle.add(ImageIO.read(new File("Image\\pipe"+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //加载棋子
        try {//加载障碍物
            obstacle.add(ImageIO.read(new File("Image\\brick2.png")));
            obstacle.add(ImageIO.read(new File("Image\\flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=1;i<=3;i++){//加载mogu
            try {
                obstacle.add(ImageIO.read(new File("Image\\fungus"+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //加载食人花
        for(int i =1;i<=2;i++){
            try {
                flower.add(ImageIO.read(new File("Image\\flower1."+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

