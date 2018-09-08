package sg.edu.nus.iss.vmcs.util;

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

public class LabelledDisplay extends Panel {

    public final static int DEFAULT = 5;
    public final static int FLOW = 1;
    public final static int GRID = 2;

    private TextField value;
    private Label lb;

    public LabelledDisplay(String label, int length, int lt) {

        if (lt == FLOW)
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
        else if (lt == GRID)
            this.setLayout(new GridLayout(1, 2));

        lb = new Label(label);
        System.out.println("LabelledDisplay:" + label);
        lb.setAlignment(Label.RIGHT);
        Panel tp = new Panel();
        tp.setLayout(new FlowLayout());
        value = new TextField();
        value.setColumns(length);

        tp.add(value);

        this.add(lb);
        this.add(tp);
    }

    public void addListener(ActionListener listener) {
        value.addActionListener(listener);
    }

    public void setEditable(boolean v) {
        value.setEditable(v);
        if (v == false) {
            value.setBackground(Color.lightGray);
        }
    }
    public void setValue(String vl) {
        value.setText(vl);
    }

    public void setValue(int vl) {
        setValue(String.valueOf(vl));
    }

    public void setActive(boolean st) {
        System.out.println("LabelledDisplay.setActive");
        value.setEnabled(st);
        lb.setEnabled(st);
    }

    public void setTextBackground(Color c) {
        value.setBackground(c);
    }

    public void setTextForeground(Color c) {
        value.setForeground(c);
    }

}