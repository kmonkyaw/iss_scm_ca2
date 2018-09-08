package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.event.*;
import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class ButtonItemDisplay extends Panel {

    private ButtonItem items[];
    private int len;
    private Label lb;

    public ButtonItemDisplay(String title, StoreItem sitem[], int length) {

        len = length;
        System.out.println("ButtonItemDisplay:" + len);
        Panel tp1 = new Panel();

        lb = new Label(title);
        tp1.add(lb);

        this.setLayout(new GridLayout(0, 1));

        this.add(tp1);

        int i;
        items = new ButtonItem[len];

        GridLayout lm = new GridLayout(1, 2);
        StoreObject ob;

        for (i = 0; i < len; i++) {
            ob = sitem[i].getContent();

            items[i] =
                new ButtonItem(
                    ob.getName(),
                    ButtonItem.DEFAULT_LEN,
                    ButtonItem.GRID);
            this.add(items[i]);
        }

    }

    public void addListener(ActionListener l) {
        int i;
        for (i = 0; i < len; i++) {
            items[i].addListener(l);
            items[i].setActionCommand(String.valueOf(i));
        }
    }

    public void setActive(boolean st) {
        int i;
        lb.setEnabled(st);
        for (i = 0; i < len; i++) {
            items[i].setActive(st);
        }
    }

    public void clear() {
        int i;
        for (i = 0; i < len; i++) {
            items[i].clear();
        }
    }

    public void displayQty(int idx, int qty) throws VMCSException {
        System.out.println("ButtonItemDisplay:" + idx);
        if (idx >= len)
            throw new VMCSException("ButtomDisplay.setQty", "Index over flow");

        items[idx].setValue(qty);
    }

}
