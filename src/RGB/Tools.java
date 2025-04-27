package RGB;
//用于通过陷阱的工具的类
public class Tools {
    private boolean ifGet;
    private boolean ifUse;
    private int x;
    private int y;

    public Tools() {
    }

    public Tools(boolean ifGet, boolean ifUse, int x, int y) {
        this.ifGet = ifGet;
        this.ifUse = ifUse;
        this.x = x;
        this.y = y;
    }

    public boolean isIfGet() {
        return ifGet;
    }

    public void setIfGet(boolean ifGet) {
        this.ifGet = ifGet;
    }

    public boolean isIfUse() {
        return ifUse;
    }

    public void setIfUse(boolean ifUse) {
        this.ifUse = ifUse;
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
