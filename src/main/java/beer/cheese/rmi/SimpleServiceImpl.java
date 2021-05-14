package beer.cheese.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SimpleServiceImpl implements SimpleService, Serializable{
    private static final long serialVersionUID = 423423L;
    @Override
    public String getUsername() throws RemoteException {
       return "wuxc";
    }
}
