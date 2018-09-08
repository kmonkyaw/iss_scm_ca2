package sg.edu.nus.iss.vmcs.store;

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

public class Coin extends StoreObject {

    private int value;
    private double weight;

    public Coin () {
    }
    public Coin (int value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public void setValue(int v){
      value = v;
    }

    public void setWeight(double wt){
      weight = wt;
    }


    public double getWeight () {
        return (weight);
    }

    public int getValue () {
        return (value);
    }

  }
