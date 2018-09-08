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

public class StoreController {

    private CashStore cStore;
    private DrinksStore dStore;

    private PropertyLoader cashLoader;
    private PropertyLoader drinksLoader;

    public StoreController(
        PropertyLoader cashLoader,
        PropertyLoader drinksLoader) {
        this.cashLoader = cashLoader;
        this.drinksLoader = drinksLoader;
    }

    public void initialize() throws IOException {
        cStore = new CashStore();
        dStore = new DrinksStore();
        initializeStores();
    }

    private void initializeStores() throws IOException {
        initializeCashStore();
        initializeDrinkStore();
    }

    private void initializeDrinkStore() throws IOException {

        // get the drink file from the environment property file;
        int numOfItems = drinksLoader.getNumOfItems();
        dStore.setStoreSize(numOfItems);

        for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
            StoreObject brand = item.getContent();
            StoreObject existingBrand = dStore.findObject(brand.getName());
            if (existingBrand != null) {
                item.setContent(existingBrand);
            }
            dStore.addItem(i, item);
        }
    }

    private void initializeCashStore() throws IOException {

        // get the cash file from the environment property file;
        int numOfItems = cashLoader.getNumOfItems();
        cStore.setStoreSize(numOfItems);

        for (int i = 0; i < numOfItems; i++) {
            CashStoreItem item = (CashStoreItem) cashLoader.getItem(i);
            cStore.addItem(i, item);
        }
    }

    public void storeCoin(Coin c) {
        int idx = cStore.findCashStoreIndx(c);
        CashStoreItem item;
        item = (CashStoreItem) this.getStoreItem(Store.CASH, idx);
        item.increment();
        int qty = item.getQuantity();
    }

    public int getStoreSize(int type) {
        if (type == Store.CASH)
            return cStore.getStoreSize();
        else
            return dStore.getStoreSize();
    }

    public StoreItem[] getStoreItems(int type) {
        if (type == Store.CASH)
            return cStore.getItems();
        else
            return dStore.getItems();
    }

    public void changeStoreQty(int type, int idx, int qty) {
            System.out.println(
                "StoreController.changeStoreQty: type:"
                    + type
                    + "   qty:"
                    + qty);
            if (type == Store.CASH)
                cStore.setQuantity(idx, qty);
            else
                dStore.setQuantity(idx, qty);
    }

    public StoreItem getStoreItem(int type, int idx) {
        if (type == Store.CASH)
            return cStore.getStoreItem(idx);
        else
            return dStore.getStoreItem(idx);
    }

    public void setPrice(int idx, int pr)  {
        DrinksStoreItem item;

        item = (DrinksStoreItem) dStore.getStoreItem(idx);
        DrinksBrand bd;

        bd = (DrinksBrand) item.getContent();

        bd.setPrice(pr);
    }

    public int getTotalCash()   {
        int i;
        int size;

        size = cStore.getStoreSize();
        CashStoreItem item;
        int qty;
        int val;
        int tc = 0;
        Coin c;

        for (i = 0; i < size; i++) {
            item = (CashStoreItem) cStore.getStoreItem(i);
            qty = item.getQuantity();
            c = (Coin) item.getContent();
            val = c.getValue();
            tc = tc + qty * val;
        }
        return tc;
    }

    public int transferAll()  {
        int i;
        int cc = 0; // coin quauntity;
        int size = cStore.getStoreSize();

        CashStoreItem item;
        for (i = 0; i < size; i++) {
            item = (CashStoreItem) cStore.getStoreItem(i);
            cc = cc + item.getQuantity();
            item.setQuantity(0);
        }

        return cc;
    }

    public void closeDown() throws IOException {
        // save back cash property;
        saveCashProperties();
        saveDrinksProperties();
    }

    private void saveCashProperties() throws IOException {
        int size = cStore.getStoreSize();
        cashLoader.setNumOfItems(size);
        for (int i = 0; i < size; i++) {
            cashLoader.setItem(i, cStore.getStoreItem(i));
        }
        cashLoader.saveProperty();
    }

    /*
     * save the drink property when simulation is ended.
     */
    private void saveDrinksProperties() throws IOException {
        int size = dStore.getStoreSize();
        drinksLoader.setNumOfItems(size);
        for (int i = 0; i < size; i++) {
            drinksLoader.setItem(i, dStore.getStoreItem(i));
        }
        drinksLoader.saveProperty();
    }

    public void dispenseDrink(int idx)  {
        DrinksStoreItem item;
        item = (DrinksStoreItem) getStoreItem(Store.DRINK, idx);
        item.decrement();
    }

    public Store getStore(int type) {
        if (type == Store.CASH)
            return (Store) cStore;
        else
            return (Store) dStore;
    }

    public void giveChange(int idx, int numOfCoins)  {
        CashStoreItem item;
        item = (CashStoreItem) getStoreItem(Store.CASH, idx);
        for (int i = 0; i < numOfCoins; i++)
            item.decrement();
    }
}
