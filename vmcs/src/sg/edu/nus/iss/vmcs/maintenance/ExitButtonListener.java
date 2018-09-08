package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.event.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class ExitButtonListener implements ActionListener {
    private MaintenanceController mctrl;

    public ExitButtonListener(MaintenanceController mc) {
        mctrl = mc;
    }
    public void actionPerformed(ActionEvent e) {
        mctrl.logoutMaintainer();
    }
}