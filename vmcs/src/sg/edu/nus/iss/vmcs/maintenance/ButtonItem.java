package sg.edu.nus.iss.vmcs.maintenance;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.*;
import java.awt.event.*;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class ButtonItem extends Panel {

    final public static int DEFAULT_LEN = 5;
    public final static int FLOW = 1;
    public final static int GRID = 2;

    public final static Color DT_COLOR = Color.darkGray;

    public final static Color ACT_COLOR = Color.white;

    private Button btn;
    private Label value;

    public ButtonItem(String bn, int l, int lt) {

        btn = new Button(bn);
        value = new Label("          ");
        value.setBackground(Color.white);

        if (lt == FLOW)
            this.setLayout(new FlowLayout());
        else if (lt == GRID)
            this.setLayout(new GridLayout(1, 2));

        this.add(btn);
        this.add(value);
    }

    public void addListener(ActionListener l) {
        btn.addActionListener(l);
    }

    public void setActionCommand(String cmd) {
        btn.setActionCommand(cmd);
    }

    public void setActive(boolean st) {
        btn.setEnabled(st);
        value.setEnabled(st);
    }

    public void setTextBackground(Color cl) {
        value.setBackground(cl);
    }

    public void clear() {
        setTextBackground(DT_COLOR);
        value.setText("");
    }

    public void setValue(int vl) {
        String sqt;

        sqt = String.valueOf(vl);
        value.setBackground(ACT_COLOR);
        value.setText(sqt);
    }

    public void setValue(String vl) {
        value.setBackground(ACT_COLOR);
        value.setText(vl);
    }
}