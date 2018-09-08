package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class AccessManager {

    private Panel passwordPanel = null;
    private MaintenanceController mctrl;
    private Password pswd;
    private boolean loginState = false;

    public AccessManager(MaintenanceController mc) {
        mctrl = mc;
        pswd = new Password();
    }
    public void closeDown() {
    }

    public void processPassword(String ps) {
        boolean psr;

        psr = pswd.validatePassword(ps);
        loginState = psr;
        mctrl.loginMaintainer(psr);
    }

    public boolean getLoginState() {
        return loginState;
    }

    public void logout() {
        loginState = false;
    }
}