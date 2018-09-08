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

public class CashStore extends Store {

    public final static int INVALID_COIN_WEIGHT = 9999;

    public CashStore() {
    }

    public int findCashStoreIndx(Coin coin) {

        int _idx = -1;
        StoreItem _si[] = getItems();

        for ( int i = 0; i < _si.length; i++ ) {
            if ( ( (Coin)_si[i].getContent() ).equals( coin ) )
                return i;
        }

        return _idx;
    }

    public Coin findCoin(double weight) {

        StoreItem _si[] = (StoreItem[])this.getItems();

        try {
            for ( int i = 0; i < _si.length; i++ ) {
                Coin _coin = (Coin)_si[i].getContent();
                if ( _coin.getWeight() == weight )
                    return _coin;
            }
        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }

        return null;
    }
}
