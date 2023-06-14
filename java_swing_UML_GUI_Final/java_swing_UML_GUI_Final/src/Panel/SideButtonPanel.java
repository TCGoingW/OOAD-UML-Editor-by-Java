package Panel;

import Panel.CustomStuff.*;
import ButtonMode.*;
import Object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SideButtonPanel extends JPanel implements ActionListener, Subject {
    private int NUM_BUTTONS = 6;
    private String[] _iconPath = {"./icon/0.png", "./icon/1.png", "./icon/2.png", "./icon/3.png", "./icon/4.png", "./icon/5.png"};
    private String[] _buttonName = {"Select", "AssociationLine", "GeneralizationLine", "CompositionLine", "Class", "UseCase"};
    private CustomButton[] _buttons;
    private Mode[] _modes;
    private java.util.List<Observer> _observers = new ArrayList<>();
    private int _currentButtonMode = 0;

    public SideButtonPanel(MyGraphic SingletonGraphics) {
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(6, 1));

        // create buttons array with number of customButton
        this._buttons = new CustomButton[NUM_BUTTONS];
        // create modes array with each mode
        this._modes = new Mode[]
                {new SelectMode(SingletonGraphics), new AssociationLineMode(SingletonGraphics), new GeneralizationLineMode(SingletonGraphics),
                        new CompositionLineMode(SingletonGraphics), new ClassMode(SingletonGraphics), new UseCaseMode(SingletonGraphics)};

        for(int i = 0; i < NUM_BUTTONS; ++i) {
            // create customButton with PNG image and mode correspondingly and put it into button array
            _buttons[i] = new CustomButton(new ImageIcon(_iconPath[i]), _modes[i]);
            _buttons[i].setBackground(Color.white);
            // add action command number
            _buttons[i].setActionCommand("" + i);
            _buttons[i].addActionListener(this);
            // add button into Panel.SideButtonPanel!
            this.add(_buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get the action number
        _currentButtonMode = Integer.valueOf(e.getActionCommand().trim());
        // set each button with white color
        for(CustomButton cb: _buttons) {
            cb.setBackground(Color.white);
        }
        // set the action number's button with black color
        _buttons[_currentButtonMode].setBackgroundColor(Color.BLACK);
        // notify observer to update button status
        notifyObserver();
    }

    public CustomButton getNowButtonMode() {
        // return customButton (it has mode)
        return _buttons[_currentButtonMode];
    }

    @Override
    public void addObserver(Observer ob) { _observers.add(ob); }

    @Override
    public void removeObserver(Observer ob) {
        _observers.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for(Observer ob: _observers) {
            ob.updateButtonState();
        }
    }
}
