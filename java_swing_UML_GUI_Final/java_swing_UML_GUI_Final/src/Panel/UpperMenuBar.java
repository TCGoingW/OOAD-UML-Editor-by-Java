package Panel;

import Panel.CustomStuff.*;
import BarBehavior.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpperMenuBar extends MenuBar implements ActionListener, Subject {
    private int NUM_ITEM = 3;
    private String _menuName = "Edit";
    private String[] _itemsName = {"Group", "UnGroup", "ChangeShapeName"};
    private CustomMenuItem[] _menuItems;
    private IMenuBarBehavior[] _behaviors;
    private java.util.List<Observer> _observer = new ArrayList<>();
    private int _currentBarBehavior = 0;

    public UpperMenuBar() {
        Menu menu = new Menu(_menuName);
        // create behaviors array with each barBehavior
        this._behaviors = new IMenuBarBehavior[]{new GroupBehavior(), new UnGroupBehavior(), new ChangShapeNameBehavior()};
        // create menuItem array with number of customMenuItem
        this._menuItems = new CustomMenuItem[NUM_ITEM];
        for(int i = 0; i < NUM_ITEM; ++i) {
            // create customMenuItem with item name and put it into menuItem array
            _menuItems[i] = new CustomMenuItem(_itemsName[i]);
            _menuItems[i].setActionCommand("" + i);
            _menuItems[i].addActionListener( this);
            // connect the behavior with customMenuItem
            _menuItems[i].setBehavior(_behaviors[i]);
            menu.add(this._menuItems[i]);
        }
        this.add(menu);
    }

    public CustomMenuItem getNowMenuBarBehavior() {
        // return customMenuItem (it has behavior)
        return _menuItems[_currentBarBehavior];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get the action number
        _currentBarBehavior = Integer.valueOf(e.getActionCommand().trim());
        // notify observer to update Bar status
        notifyObserver();
    }

    @Override
    public void addObserver(Observer ob) {
        _observer.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        _observer.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for(Observer ob: _observer)
            ob.updateMenuBarState();
    }
}
