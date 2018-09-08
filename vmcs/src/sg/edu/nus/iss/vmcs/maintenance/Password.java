package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import sg.edu.nus.iss.vmcs.system.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class Password {

    public Password() {
    }

    public boolean validatePassword(String psi) {
        // the correct password is obtained from the property file.
        // The input password psi is compared to the correct one from the property file.
        if (psi.compareTo(Environment.getPassword()) == 0)
            return true;
        else
            return false;
    }
}