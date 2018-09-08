package sg.edu.nus.iss.vmcs.system;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.io.*;
import java.util.*;

import sg.edu.nus.iss.vmcs.util.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class Environment {

//    public final static String PROP_FILE = "d:/Java/Projects/Classes/propFile.txt";
    // property keys
    public final static String DRINK_PROP_FILE = "drinkFile";
    public final static String CASH_PROP_FILE = "cashFile";
    public final static String PASSWORD = "password";

    private static Properties prop;

    public static void initialize(String propFile) throws VMCSException {
        try {
            prop = new Properties();
            FileInputStream stream = new FileInputStream(propFile);
            prop.load(stream);
            stream.close();
        } catch (Exception e) {
            throw new VMCSException(
                "Environment.initialize",
                "Error loading environment properties: " + e);
        }
    }

    public static String getDrinkPropFile() {
        return prop.getProperty(DRINK_PROP_FILE);
    }

    public static String getCashPropFile() {
        String fn;
        fn = prop.getProperty(CASH_PROP_FILE);
        return fn;
    }

    // if you put password in the property file, the password is obtained here.
    public static String getPassword() {
        return prop.getProperty(PASSWORD);
    }
}