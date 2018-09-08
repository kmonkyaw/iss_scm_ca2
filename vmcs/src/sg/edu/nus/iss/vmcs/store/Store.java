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

public abstract class Store {
    public final static int CASH  = 1;
    public final static int DRINK = 2;

    protected int size;

    protected StoreItem items[];

    public Store() {
    }

    public Store(int itemn) {
        size = itemn;
        items = new StoreItem[size];
    }

    public void setStoreSize(int sz) {
        size = sz;
        items = new StoreItem[size];
    }

    public StoreItem[] getItems() {
        return items;
    }

    public void addItem(int idx, StoreItem object) {
        if ((idx >= size) || (idx < 0))
            return;
        items[idx] = object;
    }

    public StoreItem getStoreItem(int idx) {
        if ((idx >= size) || (idx < 0))
            return null;
        return items[idx];
    }

    public StoreObject findObject(String name) {
        String en;
        StoreObject so;
        int i;

        for (i = 0; i < size; i++) {
            if (items[i] == null)
                return null;
            so = items[i].getContent();
            if (so == null)
                return null;
            en = so.getName();
            if (en != null) {
                if (en.compareTo(name) == 0)
                    return so;
            }
        }
        return null;
    }

    public void setQuantity(int idx, int qty) {
        System.out.println("Store: setQauntity- qty=" + qty);
        if ((idx >= size) || (idx < 0))
            return;
        items[idx].setQuantity(qty);
    }

    public int getStoreSize() {
        return size;
    }

}
