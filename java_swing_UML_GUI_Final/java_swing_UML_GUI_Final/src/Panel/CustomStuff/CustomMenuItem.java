package Panel.CustomStuff;

import BarBehavior.IMenuBarBehavior;

import java.awt.*;

public class CustomMenuItem extends MenuItem {
    private IMenuBarBehavior _behavior;
    public CustomMenuItem(String name) {
        super(name);
    }
    public void setBehavior(IMenuBarBehavior b) {
        _behavior = b;
    }
    public IMenuBarBehavior getBehavior() {
        return _behavior;
    }
}
