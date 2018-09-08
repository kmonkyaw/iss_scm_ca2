
package sg.edu.nus.iss.vmcs.store;
/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.io.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public interface PropertyLoader {

    public void initialize() throws IOException;

    public void saveProperty() throws IOException;

    public int getNumOfItems();

    public void setNumOfItems(int numItems);

    public StoreItem getItem (int index);

    public void setItem (int index, StoreItem item);

}
