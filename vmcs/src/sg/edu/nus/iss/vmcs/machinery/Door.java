package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class Door {

    private boolean isClosed;

    public Door() {
        isClosed = true;
    }

    public void setState(boolean isClosed) {
        System.out.println("Door.setState: " + isClosed);
        this.isClosed = isClosed;
    }

    public boolean isDoorClosed() {
        return isClosed;
    }

}