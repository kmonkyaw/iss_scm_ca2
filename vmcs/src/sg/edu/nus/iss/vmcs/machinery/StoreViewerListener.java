package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.event.*;
import java.awt.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

import sg.edu.nus.iss.vmcs.store.StoreController;

public class StoreViewerListener implements ActionListener {

    private int type;
    private int item;
    private StoreController storeCtrl;

    public StoreViewerListener(int type, int item, StoreController sctrl) {
        this.type = type;
        this.item = item;
        storeCtrl = sctrl;

    }
    public void actionPerformed(ActionEvent e) {
        TextField vf;
        int qty;
        String sqty;

        vf = (TextField) e.getSource();
        sqty = vf.getText();
        qty = Integer.parseInt(sqty);
        storeCtrl.changeStoreQty(type, item, qty);
    }
}