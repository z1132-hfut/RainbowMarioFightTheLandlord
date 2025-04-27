package RGB;
//陷阱的类
public class Traps {
    private boolean ifDisappear;
    private int x;
    private int y;

    public Traps() {
    }

    public Traps(boolean ifDisappear, int x, int y) {
        this.ifDisappear = ifDisappear;
        this.x = x;
        this.y = y;
    }

    public boolean isIfDisappear() {
        return ifDisappear;
    }

    public void setIfDisappear(boolean ifDisappear) {
        this.ifDisappear = ifDisappear;
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
