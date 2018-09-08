package sg.edu.nus.iss.vmcs.util;
/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.event.*;
import java.awt.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MessageDialog extends Dialog implements ActionListener {

    public MessageDialog(Dialog fr, String msg) {
        super(fr, "Message", true);

        this.setLayout(new BorderLayout());

        Label lb = new Label(msg);

        Button ok = new Button("OK");

        this.add("Center", lb);
        this.add("South", ok);

        ok.addActionListener(this);
        pack();
        setLocation(200, 100);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

}