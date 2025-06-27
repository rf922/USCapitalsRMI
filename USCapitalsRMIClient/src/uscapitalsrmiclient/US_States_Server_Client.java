/*
 * File : US_States_Server_Cient.java
 * 
 * 
 */
package uscapitalsrmiclient;

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
                String[] capitalArray = srvObj.getCapital(args[1]);
                String[] stateArray = srvObj.getState(args[1]);
                System.out.println("Pattern Passed : " + args[1]);
                System.out.println("Capital Matches :");
                for (String y : capitalArray) {
                    System.out.println(y);
                }
                System.out.println("State Matches :");
                for (String y : stateArray) {
                    System.out.println(y);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
