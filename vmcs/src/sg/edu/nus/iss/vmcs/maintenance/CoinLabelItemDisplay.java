package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS. The contents contained in this document may not be
 * reproduced in any form or by any means, without the written permission of
 * ISS, other than for the purpose for which it has been supplied.
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

public class CoinLabelItemDisplay extends Panel {

    private Label[] lbs;
    private CoinLabelItem items[];
    private int len;

    public CoinLabelItemDisplay(StoreItem sitem[], int length) {
        int i;
        len = length;
        System.out.println("CoinLabelItemDisplay:" + len);

        lbs = new Label[3];
        lbs[0] = new Label("Coin Value", Label.CENTER);
        lbs[1] = new Label("Quantity", Label.CENTER);
        lbs[2] = new Label("Amount", Label.CENTER);

        Panel tp = new Panel();

        for (i = 0; i < lbs.length; i++) {
            tp.add(lbs[i]);
        }

        this.setLayout(new GridLayout(0, 1, 5, 5));
        this.add(tp);

        items = new CoinLabelItem[len];

        GridLayout lm = new GridLayout(1, 2);
        StoreObject ob;

        for (i = 0; i < len; i++) {
            ob = sitem[i].getContent();

            items[i] = new CoinLabelItem(ob.getName(), CoinLabelItem.DEFAULT_LEN,
                CoinLabelItem.GRID);
            this.add(items[i]);
        }

    }

    public void setActive(boolean st) {
        int i;

        for (i = 0; i < lbs.length; i++) {
            lbs[i].setEnabled(st);
        }

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
        System.out.println("CoinLabelItemDisplay:" + idx);
        if (idx >= len)
            throw new VMCSException("CoinLabelItemDisplay.displayQty", "Index over flow");

        items[idx].setValue(CoinLabelItem.QTY, qty + " coins");
    }

    public void displayAmt(int idx, int amt) throws VMCSException {
        System.out.println("CoinLabelItemDisplay:" + idx);
        if (idx >= len)
            throw new VMCSException("CoinLabelItemDisplay.displayAmt", "Index over flow");

        items[idx].setValue(CoinLabelItem.AMT, amt + " C");
    }

}
