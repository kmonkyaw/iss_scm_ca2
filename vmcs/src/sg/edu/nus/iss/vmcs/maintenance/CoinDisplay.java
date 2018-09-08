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

    public final static String TITLE = "Quantity of Coins Available";

    private StoreController storeCtrl;
    private MaintenanceController mCtrl;
    private ButtonItemDisplay bi;
    private int len;
    private int curIdx;

    public CoinDisplay(MaintenanceController mctrl) {
        mCtrl = mctrl;
        storeCtrl = mCtrl.getMainController().getStoreController();

        int i;
        len = storeCtrl.getStoreSize(Store.CASH);
        StoreItem[] items = storeCtrl.getStoreItems(Store.CASH);

        bi = new ButtonItemDisplay(TITLE, items, len);

        bi.addListener(new CoinDisplayListener(mCtrl));

        bi.clear();

        this.add(bi);

    }

    public void setActive(boolean st) {
        bi.setActive(st);
    }

    /**
     * Display the quantity of selected coin, clear other display.
     */
    public void displayQty(int idx, int qty) throws VMCSException {
        curIdx = idx;
        bi.clear();
        bi.displayQty(idx, qty);
    }

    /**
     * get the current displayed coin index. This is used for updating when store MachinerySimulatorPanel is changed.
     * Not required.
     */
    public int getCurIdx() {
        return curIdx;
    }
}