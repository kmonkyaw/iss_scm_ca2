package sg.edu.nus.iss.vmcs.system;

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

import java.awt.*;
import java.awt.event.*;


public class SimulatorControlPanel extends Frame {

    public static final int SIMUL_BEGIN    = 0;
    public static final int SIMUL_END      = 1;
    public static final int ACT_MAINTAINER = 2;
    public static final int ACT_MACHINERY  = 3;
    private static final int NUM_BUTTONS   = 4;


    private static final String title = "Simulation Control Panel";

    private static final String L_SIMUL_BEGIN    = "Begin Simulation";
    private static final String L_SIMUL_END      = "End Simulation";
    private static final String L_ACT_MAINTAINER = "Activate Maintainer Panel";
    private static final String L_ACT_MACHINERY  = "Activate Machinery Panel";

    private MainController          mainCtrl;
    private SimulationController    simulationCtrl;

    private Button buttons[] = new Button [NUM_BUTTONS];

    private Font   titleFont  = new Font ("Helvetica", Font.BOLD, 18);
    private Font   buttonFont = new Font ("Helvetica", Font.BOLD, 14);

    public SimulatorControlPanel (SimulationController controller) {
        super (title);

        this.simulationCtrl = controller;
        this.mainCtrl = simulationCtrl.getMainController();

        setLayout(new GridLayout(0, 1));

        add (createPanelLabel());

        addButton(SIMUL_BEGIN, L_SIMUL_BEGIN,
                  new BeginSimulationButtonListener(simulationCtrl));
        addButton(ACT_MAINTAINER, L_ACT_MAINTAINER,
                  new ActivateMaintainerPanelButtonListener(simulationCtrl));
        addButton(ACT_MACHINERY,  L_ACT_MACHINERY,
                  new ActivateMachineryPanelButtonListener(simulationCtrl));
        addButton(SIMUL_END,  L_SIMUL_END,
                  new EndSimulationButtonListener(mainCtrl));

        pack();
        setLocation(100, 100);

        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
                simulationCtrl.getMainController().closeDown();
                dispose();
            }
    });
    }

    public void display(){
      this.setVisible(true);
    }

    private Label createPanelLabel() {
        Label l = new Label (title);
        l.setBackground(Color.blue);
        l.setForeground(Color.white);
        l.setFont(titleFont);
        return (l);
    }

    private void addButton (int id, String label, ActionListener l) {
        Button b = new Button (label);
        b.setBackground(Color.white);
        b.setForeground(Color.blue);
        b.setFont(buttonFont);
        b.addActionListener(l);
        buttons[id] = b;
        add (b);
    }

    public void setSimulationActive (boolean isOn) {
        setButtonState (SIMUL_BEGIN,   !isOn);
        setButtonState (ACT_MAINTAINER, isOn);
        setButtonState (ACT_MACHINERY,  isOn);
        setButtonState (SIMUL_END,      isOn);
    }

    public void setButtonState (int id, boolean state) {
        buttons[id].setEnabled (state);
    }

    public void closeDown () {
        dispose ();
    }

    public void stop(){
        mainCtrl.closeDown();
    }

    public void setActive (int id, boolean state) {
        setButtonState(id, state);
    }
}