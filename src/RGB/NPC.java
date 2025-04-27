package RGB;
//其他两个彩色马里奥的类
public class NPC {
    private boolean ifMakeFriend;

    public NPC() {
    }

    public NPC(boolean ifMakeFriend) {
        this.ifMakeFriend = ifMakeFriend;
    }

    public boolean isIfMakeFriend() {
        return ifMakeFriend;
    }

    public void setIfMakeFriend(boolean ifMakeFriend) {
        this.ifMakeFriend = ifMakeFriend;
    }
}
