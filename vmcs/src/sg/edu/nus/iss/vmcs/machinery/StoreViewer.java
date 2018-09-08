package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class StoreViewer extends Panel {

    private LabelledDisplay viewItems[];
    private StoreController storeCtrl;

    private int type;

    public static final String CASH_VIEW_TITLE = "Quantity of Coins Available";
    public static final String DRINK_VIEW_TITLE = "Quantity of Drinks Available";

    public StoreViewer(int ti, StoreController sctrl) {

        storeCtrl = sctrl;
        type = ti;

        String title = null;
        switch (type) {
        case Store.CASH:
            title = CASH_VIEW_TITLE;
            break;
        case Store.DRINK:
            title = DRINK_VIEW_TITLE;
            break;
        }

        Panel pl = new Panel(new FlowLayout(FlowLayout.LEFT));
        pl.add(new Label(title));

        int sSize = storeCtrl.getStoreSize(type);
        viewItems = new LabelledDisplay[sSize];

        StoreItem[] storeItem = storeCtrl.getStoreItems(type);
        this.setLayout(new GridLayout(0, 1));
        this.add(pl);

        for (int i = 0; i < sSize; i++) {
            String name = storeItem[i].getContent().getName();
            viewItems[i] = new LabelledDisplay(name,
                        LabelledDisplay.DEFAULT,
                        LabelledDisplay.GRID);
            viewItems[i].addListener(
                        new StoreViewerListener(type, i, storeCtrl));
            this.add(viewItems[i]);
        }

        update();
    }

    public void update () {
        StoreItem[] storeItem = storeCtrl.getStoreItems(type);
        for (int i = 0; i < storeItem.length; i++) {
            int val = storeItem[i].getQuantity();
            String sval = String.valueOf(val);
            viewItems[i].setValue(sval);
        }
    }

    public void update(int idx, int qty) throws VMCSException {
        int sSize = storeCtrl.getStoreSize(type);
        if (idx >= sSize)
            throw new VMCSException("StoreViewer.update", "index overflow");
        viewItems[idx].setValue(qty);
    }

    public void closeDown() {
    }

    public void setActive(boolean state) {
        this.setEnabled(state);
    }
}
