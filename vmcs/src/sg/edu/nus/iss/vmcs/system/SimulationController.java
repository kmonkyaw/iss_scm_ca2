package sg.edu.nus.iss.vmcs.system;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.util.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class SimulationController {

    private SimulatorControlPanel scp = null;
    public MainController mCtrl = null;

    public SimulationController(MainController ctrl) {
        mCtrl = ctrl;
        scp = new SimulatorControlPanel(this);
    }

    public SimulatorControlPanel getSimulatorControlPanel() {
        return (scp);
    }

    public void displaySimulatorControlPanel() {
        scp.display();
    }

    public void stop() {
        mCtrl.closeDown();
    }

    public void closeDown() {
        scp.closeDown();
    }

    public void start() {
        scp.setSimulationActive(true);
        scp.setActive(SimulatorControlPanel.SIMUL_BEGIN, false);
    }

    public void setSimulationActive(boolean vl) {
        scp.setSimulationActive(vl);
    }

    public void setupSimulator() {
        MaintenanceController maintenanceCtrl;
        maintenanceCtrl = mCtrl.getMaintenanceController();
        MachineryController machCtrl;

        machCtrl = mCtrl.getMachineryController();
        scp.setActive(SimulatorControlPanel.ACT_MACHINERY, false);
        try {
            // activate when not login
            // always diaply the door locked; isOpen false
            machCtrl.displayMachineryPanel();

            // display drink stock;
            machCtrl.displayDrinkStock();

            // display coin quantity;
            machCtrl.displayCoinStock();

            machCtrl.displayDoorState();
        } catch (VMCSException e) {
            System.out.println("SimulationController.setupSimulator:" + e);
        }
    }

    public void setupMaintainer() {
        MaintenanceController mctrl;
        mctrl = mCtrl.getMaintenanceController();
        scp.setActive(SimulatorControlPanel.ACT_MAINTAINER, false);
        mctrl.displayMaintenancePanel();
    }

    public MainController getMainController() {
        return mCtrl;
    }

}
