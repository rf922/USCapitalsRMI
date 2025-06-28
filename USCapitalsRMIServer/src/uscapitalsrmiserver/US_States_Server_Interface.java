/*
 * File : US_States_Server_Interface.java
 * 
 * 
 */
package uscapitalsrmiserver;
import java.rmi.RemoteException;
import java.rmi.*;
/**
 *
 * @author rf922
 */
public interface US_States_Server_Interface extends Remote{
    public String getCapital(String state) throws RemoteException;
    public String getState(String capital) throws RemoteException;
    
}
