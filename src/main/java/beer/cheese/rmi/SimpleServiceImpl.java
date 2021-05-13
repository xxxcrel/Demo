package beer.cheese.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SimpleServiceImpl implements SimpleService{


    @Override
    public String getUsername() throws RemoteException {
       return "wuxc";
    }
}
