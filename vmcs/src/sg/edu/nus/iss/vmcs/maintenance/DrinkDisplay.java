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
import sg.edu.nus.iss.vmcs.util.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class DrinkDisplay extends Panel {

    public final static String TITLE = "Quantity of Drinks Available";

    private StoreController storeCtrl;
    private MaintenanceController mCtrl;
    private ButtonItemDisplay bi;
    private LabelledDisplay price;
    private int curIdx; //current displayed item index;

    public DrinkDisplay(MaintenanceController mctrl) {
        mCtrl = mctrl;
        storeCtrl = mCtrl.getMainController().getStoreController();

        this.setLayout(new BorderLayout());
        int i, len;
        len = storeCtrl.getStoreSize(Store.DRINK);
        System.out.println("DrinkDisplay: len:" + len);
        StoreItem[] items = storeCtrl.getStoreItems(Store.DRINK);

        bi = new ButtonItemDisplay(TITLE, items, len);

        bi.addListener(new DrinkDisplayListener(mCtrl));
        bi.clear();
        price = new LabelledDisplay("Brand Price", 4, LabelledDisplay.FLOW);

        PriceDisplayListener pdl;

        pdl = new PriceDisplayListener(mCtrl);
        price.addListener(pdl);
        Panel tp = new Panel();
        tp.setLayout(new FlowLayout(FlowLayout.CENTER));
        tp.add(bi);
        curIdx = 0;
        this.add("Center", tp);
        this.add("South", price);
        price.setEnabled(false);
    }

    public LabelledDisplay getPriceDisplay() {
        return price;
    }

    public void setActive(boolean st) {
        price.setActive(st);
        bi.setActive(st);
    }

    public void displayQty(int idx, int qty) throws VMCSException {
        curIdx = idx;
        bi.clear();
        price.setEnabled(true);
        bi.displayQty(idx, qty);
    }

    public int getCurIdx() {
        return curIdx;
    }

}