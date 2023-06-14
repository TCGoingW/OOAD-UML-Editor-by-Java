package Panel.CustomStuff;

import ButtonMode.*;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private Mode _mode;
    public CustomButton(ImageIcon imageIcon, Mode mode) {
        // create customButton with PNG image and mode correspondingly
        super(imageIcon);
        this._mode = mode;
    }

    public Mode getMode() {
        return _mode;
    }

    public void setBackgroundColor(Color c)
    {
        this.setBackground(c);
    }
}
