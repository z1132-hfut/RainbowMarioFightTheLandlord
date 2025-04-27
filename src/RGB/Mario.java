package RGB;

//彩虹马里奥主角类
public class Mario {
    private int moveStatus;  //运动状态
    private int speed;  //移动速度
    private int x; //x坐标
    private int y; //y坐标

    public Mario() {
    }

    public Mario(int moveStatus, int speed, int x, int y) {
        this.moveStatus = moveStatus;
        this.speed = speed;
        this.x = x;
        this.y = y;

    }

    public int getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(int moveStatus) {
        this.moveStatus = moveStatus;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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


}
