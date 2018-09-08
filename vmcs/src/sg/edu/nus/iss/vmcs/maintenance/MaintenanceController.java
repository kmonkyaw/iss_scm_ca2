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
import sg.edu.nus.iss.vmcs.system.*;
import sg.edu.nus.iss.vmcs.machinery.*;
import sg.edu.nus.iss.vmcs.util.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MaintenanceController {

    private MainController mCtrl;
    private MaintenancePanel mpanel;
    private AccessManager am;

    public MaintenanceController(MainController mctrl) {
        mCtrl = mctrl;
        am = new AccessManager(this);
    }

    public MainController getMainController() {
        return mCtrl;
    }

    /**
     * setup the maintenance panel and display it.
     */
    public void displayMaintenancePanel() {
        SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
        if (mpanel == null)
            mpanel = new MaintenancePanel((Frame) scp, this);
        mpanel.display();
        mpanel.setActive(MaintenancePanel.DIALOG, true);
        // setActive of password, invalid and valid display.
    }

    public AccessManager getAccessManager() {
        return am;
    }

    public void loginMaintainer(boolean st) {
        mpanel.displayPasswordState(st);
        mpanel.clearPassword();
        if (st == true) {
            // login successful
            mpanel.setActive(MaintenancePanel.WORKING, true);
            mpanel.setActive(MaintenancePanel.PSWD, false);
            MachineryController machctrl = mCtrl.getMachineryController();
            machctrl.setDoorState(false);
        }
    }

    // invoked in CoinDisplayListener
    public void displayCoin(int idx) {
        StoreController sctrl = mCtrl.getStoreController();
        CashStoreItem item;
        try {
            item = (CashStoreItem) sctrl.getStoreItem(Store.CASH, idx);
            mpanel.getCoinDisplay().displayQty(idx, item.getQuantity());
        } catch (VMCSException e) {
            System.out.println("MaintenanceController.displayCoin:" + e);
        }

    }

    // invoked in DrinkDisplayListener;
    public void displayDrinks(int idx) {
        StoreController sctrl = mCtrl.getStoreController();
        DrinksStoreItem item;
        try {
            item = (DrinksStoreItem) sctrl.getStoreItem(Store.DRINK, idx);
            DrinksBrand db = (DrinksBrand) item.getContent();
            mpanel.getDrinksDisplay().displayQty(idx, item.getQuantity());
            mpanel.displayPrice(db.getPrice());
        } catch (VMCSException e) {
            System.out.println("MaintenanceController.displayDrink:" + e);
        }

    }

    // invoked by PriceDisplayListener
    public void setPrice(int pr) {
        StoreController sctrl = mCtrl.getStoreController();
        int curIdx = mpanel.getCurIdx();
        sctrl.setPrice(curIdx, pr);
        mpanel.getDrinksDisplay().getPriceDisplay().setValue(pr + "C");
    }

    // TotalCashButtonListener
    public void getTotalCash() {
        StoreController sctrl = mCtrl.getStoreController();
        int tc = sctrl.getTotalCash();
        mpanel.displayTotalCash(tc);

    }

    // TransferCashButtonListener
    // get all the cash from store and set store cash 0;
    public void transferAll() {
        StoreController sctrl = mCtrl.getStoreController();
        MachineryController machctrl = mCtrl.getMachineryController();

        int cc; // coin quantity;

        try {

            cc = sctrl.transferAll();
            mpanel.displayCoins(cc);
            machctrl.displayCoinStock();
            // the cash qty current is displayed in the Maintenance panel needs to be update to be 0;
            // not required.
            mpanel.updateCurrentQtyDisplay(Store.CASH, 0);
        } catch (VMCSException e) {
            System.out.println("MaintenanceController.transferAll:" + e);
        }
    }

    // StoreViewerListener
    public void changeStoreQty(char type, int idx, int qty) {
        StoreController sctrl = mCtrl.getStoreController();

        try {
            mpanel.updateQtyDisplay(type, idx, qty);
            mpanel.initCollectCash();
            mpanel.initTotalCash();
        } catch (VMCSException e) {
            System.out.println("MaintenanceController.changeStoreQty:" + e);
        }
    }

    // exit button listener;
    public void logoutMaintainer() {

        MachineryController machctrl = mCtrl.getMachineryController();

        boolean ds = machctrl.isDoorClosed();

        if (ds == false) {
            MessageDialog msg =
                new MessageDialog(
                    mpanel,
                    "Please Lock the Door before You Leave");
            msg.setLocation(500, 500);
            return;
        }

        mpanel.initCollectCash();
        mpanel.initTotalCash();
        mpanel.setActive(MaintenancePanel.DIALOG, true);

    }

    public void closeDown() {
        if (mpanel != null)
            mpanel.closeDown();
    }

}
