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
import java.util.stream.Collectors;
import uscapitalsrmiserver.model.State;
/**
 *
 * @author rf922
 */
public class US_States_Server extends UnicastRemoteObject implements US_States_Server_Interface {

    private static final String US_CAPITALS_FILE_PATH = "capitals.csv";
    private static HashMap<String, String> stateMap;
    private static HashMap<String, State> stateMapPlus;
    
    public US_States_Server() throws RemoteException{
        super();
        parseDataFile();
    }
    
    private static void parseDataFile(){
        try {
            stateMap = (HashMap<String, String>) Files.lines(Path.of(US_CAPITALS_FILE_PATH))
                .skip(1)
                .map(x -> x.split(","))
                .filter(x -> x.length > 1)
                .collect(Collectors.toMap(x -> x[0], x -> x[1]));
            System.out.println("[US_STATES_SERVER] : State Map populated ");
            System.out.println("[US_STATES_SERVER] : State Map entries " +stateMap.entrySet().size());
            
            stateMapPlus = (HashMap<String, State>) Files.lines(Path.of(US_CAPITALS_FILE_PATH))
                .skip(1)
                .map(x -> x.split(","))
                .filter(x -> x.length > 1)
                .map(x -> new State(x[0].strip(), x[1].strip(), x[2].strip(), Double.valueOf(x[3].strip()), Double.valueOf(x[4].strip()), Long.valueOf(x[5].strip())))
                .collect(Collectors.toMap(x -> x.getState(), x -> x));
            System.out.println("[US_STATES_SERVER] : State Map+ populated ");
            System.out.println("[US_STATES_SERVER] : State Map+ entries " +stateMap.entrySet().size());
            
        } catch (IOException ex) {
            Logger.getLogger(US_States_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    @Override
    public String getCapital(String statePattern) throws RemoteException {
        if(!stateMap.containsValue(statePattern)){
            //Possible user passed an abbreviation 
        }
        String result = stateMap.getOrDefault(ref, "N/A");
        System.out.println("[US_STATES_SERVER] : Received \'" + statePattern + "\'" );
        System.out.println("[US_STATES_SERVER] : Yielded \'" + result + "\'" );
        return result;
    }

    @Override
    public String getState(String capitalPattern) throws RemoteException {
        String result = "N/A";
        if(stateMap.values().contains(capitalPattern)){
            result = stateMap.entrySet().stream().filter(x -> x.getValue().equalsIgnoreCase(capitalPattern)).findFirst().get().getKey();
        }
        return result;
    }
    
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.createRegistry(1066);
            US_States_Server server = new US_States_Server();
            reg.rebind("US_States_Server", server);
            System.out.println("US_States_Server Up " );
            
        }catch(RemoteException ex){
            System.out.println(ex);
        }
    }
}
