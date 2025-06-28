/*
 * File : US_States_Server_Cient.java
 * 
 * 
 */
package uscapitalsrmiclient;

import java.net.MalformedURLException;
import java.rmi.*;
import uscapitalsrmiserver.US_States_Server_Interface;

/**
 *
 * @author rf922
 */
public class US_States_Server_Client {

    public static void main(String... args) {
        if (args.length < 2) {
            System.out.println("Not Enough arguements passed ");
        } else {
            try {
                String url = "rmi://" + args[0] + "/US_States_Server";
                US_States_Server_Interface srvObj = (US_States_Server_Interface) Naming.lookup(url);
                String capital = srvObj.getCapital(args[1]);
                String state = srvObj.getState(args[1]);
                System.out.println("Pattern Passed : " + args[1]);
                System.out.println("Capital Matches :" + capital);
                System.out.println("State Matches :"+ state);

            } catch (MalformedURLException | NotBoundException | RemoteException ex) {
                System.out.println(ex);
            }
        }
    }
}
