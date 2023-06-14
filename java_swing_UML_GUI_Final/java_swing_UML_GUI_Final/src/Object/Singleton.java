package Object;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance;
    private java.util.List<MyGraphic> MyGraphics;

    private Singleton() {
        MyGraphics = new ArrayList<>();
    }

    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public java.util.List<MyGraphic> getMyGraphics() {
        return MyGraphics;
    }
}
