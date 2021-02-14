package eg.gov.iti.contract.net;

import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.RegisterServiceInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServicesLocator {
    private static Registry registry;
    private static ServicesLocator instance;

    //services instances
    private static ChatServerInterface chatServerInterface;
    private static LoginServiceInterface loginService;
    private static RegisterServiceInterface registerServiceInterface;


    private static boolean connectionEstablished;
    private ServicesLocator(){

    }

    public static boolean isConnectionEstablished() {
        return connectionEstablished;
    }
    public static ServicesLocator getInstance() {
        if(instance==null)
            instance = new ServicesLocator();
        return instance;
    }

    public static ChatServerInterface getChatServerInterface() {
        return chatServerInterface;
    }

    public static LoginServiceInterface getLoginService() {
        return loginService;
    }

    public static RegisterServiceInterface getRegisterService(){return registerServiceInterface;}

    public static boolean servicesInit(){
        if(!connectionEstablished){
            try {
                //registry
                registry = LocateRegistry.getRegistry("127.0.0.1");

                //services lookup
                chatServerInterface = (ChatServerInterface) registry.lookup("chatApplication");
                loginService = (LoginServiceInterface) registry.lookup("loginService");
                registerServiceInterface = (RegisterServiceInterface) registry.lookup("registerService");


                connectionEstablished = true;
                return true;
            } catch (Exception e) {
                connectionEstablished = false;
                System.out.println("RMI-Registry Couldn't Establish a Connection...");
                return false;
            }
        }else {
            connectionEstablished = false;
            System.out.println("RMI-Registry Connection is Already Established...");
            return true;
        }
    }

}
