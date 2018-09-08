package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.*;
import java.awt.event.*;

import sg.edu.nus.iss.vmcs.store.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MachinerySimulatorPanel extends Dialog {

    private static final String TITLE = "Machinery Panel";

    private StoreViewer cashDisplay;
    private StoreViewer drinksDisplay;
    private Checkbox doorDisplay;
    private StoreController storeCtrl;
    private MachineryController machineryCtrl;

    public MachinerySimulatorPanel(Frame fr, MachineryController machCtrl) {
        super(fr, TITLE, false);

        machineryCtrl = machCtrl;
        storeCtrl = machineryCtrl.getMainController().getStoreController();

        Label lb = new Label(TITLE);
        lb.setFont(new Font("Helvetica", Font.BOLD, 24));
        lb.setAlignment(Label.CENTER);

        cashDisplay = new StoreViewer(Store.CASH, storeCtrl);
        drinksDisplay = new StoreViewer(Store.DRINK, storeCtrl);

        Panel tp = new Panel();
        tp.setLayout(new GridLayout(0, 1));
        tp.add(cashDisplay);
        tp.add(drinksDisplay);

        Panel dp = new Panel();
        doorDisplay = new Checkbox();
        doorDisplay.addItemListener(new DoorListener(machineryCtrl));
        doorDisplay.setLabel("Door Locked");
        dp.add(doorDisplay);

        this.setLayout(new BorderLayout());
        this.add("North", lb);
        this.add("Center", tp);
        this.add("South", dp);
        pack();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                machineryCtrl.closeMachineryPanel();
            }
        });
    }

    public void display() {
        this.setVisible(true);
    }

    public void closeDown() {
        dispose();
    }

    public StoreViewer getCashStoreDisplay() {
        return cashDisplay;
    }

    public StoreViewer getDrinksStoreDisplay() {
        return drinksDisplay;
    }

    public void setDoorState(boolean state) {
        doorDisplay.setState(state);
        this.setActive(!state);

    }

    public void setActive(boolean state) {
        cashDisplay.setActive(state);
        drinksDisplay.setActive(state);
        doorDisplay.setEnabled(state);
    }

}
