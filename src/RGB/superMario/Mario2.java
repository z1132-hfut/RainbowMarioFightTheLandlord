package RGB.superMario;

import java.awt.image.BufferedImage;

public class Mario2 implements Runnable {
    private int x;
    private int y;
    private String status1;
    private BufferedImage show = null;
    private BackGround backGround = new BackGround();
    private Thread thread = null;
    private int xSpeed;
    private int ySpeed;
    private int index;//表示马里奥上升的时间
    private int uptime = 0;

    public Mario2() {//定义构造函数
    }

    public Mario2(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_R;
        this.status1 = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    //马里奥向左移动
    public void leftMove() {
        //改变速度
        xSpeed = -5;
        //判断马里奥是否处于空中
        if (status1.indexOf("jump") != -1) {
            status1 = "jump--left";
        } else {
            status1 = "move--left";
        }
    }

    //马里奥向右移动
    public void rightMove() {
        xSpeed = 5;
        if (status1.indexOf("jump") != -1) {
            status1 = "jump--right";
        } else {
            status1 = "move--right";
        }
    }

    //马里奥向左停止的方法
    public void leftStop() {
        xSpeed = 0;
        if (status1.indexOf("jump") != -1) {
            status1 = "jump--left";
        } else {
            status1 = "stop--left";
        }

    }

    //马里奥向右停止方法
    public void rightStop() {
        xSpeed = 0;
        if (status1.indexOf("jump") != -1) {
            status1 = "jump--right";
        } else {
            status1 = "stop--right";
        }

    }

    public void jump() {
        if (status1.indexOf("jump") == -1) {
            if (status1.indexOf("left") != -1) {
                status1 = "jump--left";
            } else {
                status1 = "jump--right";
            }
            ySpeed = -10;
            uptime = 7;
        }
    }

    public void fall() {
        if (status1.indexOf("left") != -1) {
            status1 = "jump--left";
        } else {
            status1 = "jump--right";
        }
        ySpeed = 10;

    }

    @Override
    public void run() {
        while (true) {
            //判断是否处于障碍物上
            boolean onObstacle = false;
            //判断是否可以往右走
            boolean canRight = true;
            //判断是否可以向左走
            boolean canleft = true;




            for (int i = 0; i < backGround.getObtacleList().size(); i++) {
                Obtacle ob = backGround.getObtacleList().get(i);
                if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                    onObstacle = true;
                }

                //判断是否跳起来顶到砖块
                if((ob.getY()>=this.y-30&&ob.getY()<=this.y-20)&&(ob.getX()>this.x-30&&ob.getX()<this.x +25)) {
                    if (ob.getType()==0) {
                        backGround.getObtacleList().remove(ob);
                    }
                    uptime =0;
                }
                // 判断是否可以往右
                if(ob.getX()==this.x +25&&(ob.getY()>this.y-30&&ob.getY()<this.y+25)){
                    canRight = false;
                }
                //判断是否可以向左走
                if(ob.getX()==this.x-30&&(ob.getY()>this.y-30&&ob.getY()<this.y+25)){
                    canleft=false;
                }
            }

            //进行马里奥跳跃的操作
            if (onObstacle && uptime == 0) {
                if (status1.indexOf("left") !=-1) {
                    if (xSpeed != 0) {
                        status1 = "move--left";
                    } else {
                        status1 = "stop--left";
                    }
                } else {
                    if (xSpeed != 0) {
                        status1 = "move--right";
                    } else {
                        status1 = "stop--right";
                    }
                }
            } else {
                if (uptime != 0) {
                    uptime--;
                } else {
                    fall();
                }
                y += ySpeed;
            }


            if ((canleft&&xSpeed<0)||canRight&&xSpeed>0) {
                x += xSpeed;
                // 判断马里奥是否到头
                if (x < 0) {
                    x = 0;
                }
            }

            if (status1.contains("move")) {//判断是否移动
                index = index == 0 ? 1 : 0;
            }
            if ("move--left".equals(status1)) {//判断向左移动
                show = StaticValue.run_L.get(index);
            }
            if ("move--right".equals(status1)) {//判断向右移动
                show = StaticValue.run_R.get(index);
            }
            if ("stop--left".equals(status1)) {//判断是否向左停止
                show = StaticValue.stand_L;
            }
            if ("stop--right".equals(status1)) {//判断是否向右停止
                show = StaticValue.stand_R;
            }
            if ("junmp--left".equals(status1)) {
                show = StaticValue.jump_L;
            }
            if ("junmp--right".equals(status1)) {
                show = StaticValue.jump_R;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }
}


