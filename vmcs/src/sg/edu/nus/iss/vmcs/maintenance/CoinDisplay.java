package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class CoinDisplay extends Panel {

    private StoreController storeCtrl;
    private MaintenanceController mCtrl;
    private CoinLabelItemDisplay li;
    private int len;
    private int curIdx;

    public CoinDisplay(MaintenanceController mctrl) {
        mCtrl = mctrl;
        storeCtrl = mCtrl.getMainController().getStoreController();

        len = storeCtrl.getStoreSize(Store.CASH);
        StoreItem[] items = storeCtrl.getStoreItems(Store.CASH);

        li = new CoinLabelItemDisplay(items, len);

        li.clear();

        this.add(li);

    }

    public void setActive(boolean st) {
        li.setActive(st);
        li.clear();
    }

    /**
     * Update the quantity and amount of all coins on the display.
     */
    public void updateDisplay() throws VMCSException {
        StoreItem[] items = storeCtrl.getStoreItems(Store.CASH);

        int i;
        int value;
        int qty;
        Coin coin;

        for ( i = 0; i < len; i++ ) {
            coin = (Coin) items[i].getContent();
            value = coin.getValue();
            qty = items[i].getQuantity();

            li.displayQty(i, qty);
            li.displayAmt(i, qty*value);
        }
    }

    /**
     * Display the quantity of selected coin, clear other display.
     */
    public void displayQty(int idx, int qty) throws VMCSException {
        curIdx = idx;
        li.clear();
        li.displayQty(idx, qty);
    }

    /**
     * get the current displayed coin index. This is used for updating when store MachinerySimulatorPanel is changed.
     * Not required.
     */
    public int getCurIdx() {
        return curIdx;
    }
}
