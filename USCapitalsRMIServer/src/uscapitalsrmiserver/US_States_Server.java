/*
 * File : US_States_Server.java
 * 
 * 
 */
package uscapitalsrmiserver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author rf922
 */
public class US_States_Server extends UnicastRemoteObject implements US_States_Server_Interface {

    private static final String US_CAPITALS_FILE_PATH = "C://Users//usr//Desktop//US_States";
    private static HashMap<String, String> statesMap;
    
    public US_States_Server() throws RemoteException{
        super();
    }
    
    
    @Override
    public String[] getCapital(String statePattern) throws RemoteException {
            String[] resultArray = new String[]{"No results found "};
        try {
            
            
            resultArray = Files.lines(Path.of(US_CAPITALS_FILE_PATH))
                    .skip(2)
                    .map(x -> x.split("\\s\\s+")[0].trim())
                    .filter(x -> {
                        Pattern sp = Pattern.compile(statePattern);
                        Matcher m = sp.matcher(x); 
                        return m.find();
                    })
                    .toArray(String[]::new);
            
        } catch (IOException ex) {
            Logger.getLogger(US_States_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
    }

    @Override
    public String[] getState(String capitalPattern) throws RemoteException {
            String[] resultArray = new String[]{"No results found "};
        try {
            
            
            resultArray = Files.lines(Path.of(US_CAPITALS_FILE_PATH))
                    .skip(2)
                    .map(x -> x.split("\\s\\s+")[1].trim())
                    .filter(x -> {
                        Pattern sp = Pattern.compile(capitalPattern);
                        Matcher m = sp.matcher(x); 
                        return m.find();
                    })
                    .toArray(String[]::new);
            
        } catch (IOException ex) {
            Logger.getLogger(US_States_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
    }
    
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.createRegistry(1066);
            US_States_Server server = new US_States_Server();
            reg.rebind("US_States_Server", server);
            System.out.println("US_States_Server Up " );
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
