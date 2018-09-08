package sg.edu.nus.iss.vmcs.util;

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

public class VMCSException extends Exception {

  public VMCSException() {
  }

  public VMCSException(String location, String msg){
    super (location + " :" + msg);
  }

  public  VMCSException (VMCSException e){
    super(e.getMessage());
  }
}