package sg.edu.nus.iss.vmcs.util;

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

public class WarningDisplay extends Panel {

    private static final Color DEFAULT_FG = Color.yellow;
    private static final Color DEFAULT_BG = Color.red;
    private static final Color DEFAULT_OFF = Color.white;

    private Color onFg = DEFAULT_FG, onBg = DEFAULT_BG;
    private Color offFg = DEFAULT_OFF, offBg = Color.black;
    private Label lb;

    public WarningDisplay(String label) {
        this.setLayout(new FlowLayout());
        lb = new Label(label);
        this.add(lb);
        setState(false);
    }

    public WarningDisplay(String label, Color onFg, Color onBg) {
        this(label, onFg, onBg, DEFAULT_OFF, DEFAULT_OFF);
        setState(false);
    }

    public WarningDisplay(
        String label,
        Color onFg,
        Color onBg,
        Color offFg,
        Color offBg) {

        this.onBg = onBg;
        this.onFg = onFg;
        this.offBg = offBg;
        this.offFg = offFg;
        lb = new Label(label);
        lb.setAlignment(Label.CENTER);

        this.add(lb);
        setState(false);
    }

    public void setState(boolean isOn) {
        if (isOn) {
            lb.setForeground(onFg);
            lb.setBackground(onBg);
        } else {
            lb.setForeground(offFg);
            lb.setBackground(offBg);
        }
    }

}