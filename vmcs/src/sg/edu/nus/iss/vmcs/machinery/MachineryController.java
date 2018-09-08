package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import sg.edu.nus.iss.vmcs.system.*;
import sg.edu.nus.iss.vmcs.util.*;
import sg.edu.nus.iss.vmcs.store.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MachineryController {

    public MainController mainCtrl;
    public StoreController storeCtrl;

    private MachinerySimulatorPanel ml;
    private Door door;

    public MachineryController(MainController mctrl) {
        mainCtrl = mctrl;
        storeCtrl = mctrl.getStoreController();
    }

    public MainController getMainController() {
        return mainCtrl;
    }

    public void initialize() throws VMCSException {
        door = new Door();
    }

    public void closeDown() {
        if (ml != null)
            ml.dispose();
    }

    /* ************************************************************
     * Panel Operations
     */

    public void displayMachineryPanel() {
        SimulatorControlPanel scp = mainCtrl.getSimulatorControlPanel();
        if (ml == null)
            ml = new MachinerySimulatorPanel(scp, this);
        ml.display();
        //System.out.println("get door status:" + door.isDoorClosed());
        scp.setActive(SimulatorControlPanel.ACT_MACHINERY, false);
    }

    public void closeMachineryPanel() {
        if (ml == null)
            return;
        boolean ds = isDoorClosed();

        if (ds == false) {
            MessageDialog msg =
                new MessageDialog(ml, "Please Lock the Door before You Leave");
            return;
        }
        ml.dispose();
        SimulatorControlPanel scp = mainCtrl.getSimulatorControlPanel();
        scp.setActive(SimulatorControlPanel.ACT_MACHINERY, true);
    }

    /* ************************************************************
     * Door operation
     */

    public boolean isDoorClosed() {
        return door.isDoorClosed();
    }

    public void setDoorState(boolean state) {
        //System.out.println("StoreController.setDoorState:" + state);
        door.setState(state);
        displayDoorState();
    }

    /* ************************************************************
     * Display operation
     */

    public void displayDoorState() {
        if (ml == null)
            return;
        ml.setDoorState(door.isDoorClosed());
    }

    // update drink stock view;
    public void displayDrinkStock() throws VMCSException {
        if (ml == null)
            return;
        ml.getDrinksStoreDisplay().update();
    }

    // update coin stock view after transfer all cash;
    public void displayCoinStock() throws VMCSException {
        if (ml == null)
            return;
        ml.getCashStoreDisplay().update();
    }

    /* ************************************************************
     * Interactions with the Store that need to update the display
     */

    public void storeCoin(Coin c) throws VMCSException {
        storeCtrl.storeCoin(c);
        if (ml != null)
            ml.getCashStoreDisplay().update();
    }

    public void dispenseDrink(int idx) throws VMCSException {
        storeCtrl.dispenseDrink(idx);
        if (ml != null)
            ml.getCashStoreDisplay().update();

    }

    public void giveChange(int idx, int numOfCoins) throws VMCSException {
        storeCtrl.giveChange(idx, numOfCoins);
        if (ml != null)
            ml.getCashStoreDisplay().update();
    }

}
