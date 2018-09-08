package sg.edu.nus.iss.vmcs.system;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.util.*;
import java.io.*;

import sg.edu.nus.iss.vmcs.store.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public abstract class FilePropertyLoader implements PropertyLoader {

    private static final String PROP_NUM_ITEMS = "NumOfItems";

    private Properties prop;
    private String fileName;

    public FilePropertyLoader(String fileName) {
        this.fileName = fileName;
    }

    public void initialize() throws IOException {
        prop = new Properties(System.getProperties());
        FileInputStream stream = new FileInputStream(fileName);
        prop.load(stream);
        stream.close();
    }

    public void saveProperty() throws IOException {
        FileOutputStream stream = new FileOutputStream(fileName);
        prop.store(stream, "");
        stream.close();
    }

    public int getNumOfItems() {
        String nm = prop.getProperty(PROP_NUM_ITEMS);
        int nmi;
        nmi = Integer.parseInt(nm);
        return nmi;
    }

    public void setNumOfItems(int vl) {
        prop.setProperty(PROP_NUM_ITEMS, String.valueOf(vl));
    }

    public abstract StoreItem getItem(int index);

    public abstract void setItem(int index, StoreItem item);

    // Utility methoids for accessing the hashtable

    public String getValue(String key) {
        return prop.getProperty(key);
    }

    public void setValue(String key, String value) {
        prop.setProperty(key, value);
    }

}