package mobile.app.dynamicfragments;

/**
 * Created by madhu on 28/6/17.
 */

public class Singleton {
    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos;
}