package sg.edu.nus.iss.vmcs.system;

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

public class BeginSimulationButtonListener implements ActionListener {

    private SimulationController ctrl;

    public BeginSimulationButtonListener(SimulationController c) {
        ctrl = c;
    }

    public void actionPerformed(ActionEvent event) {
        ctrl.start();
    }

}
