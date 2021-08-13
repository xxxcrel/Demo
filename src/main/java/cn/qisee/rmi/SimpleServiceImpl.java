package cn.qisee.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

public class SimpleServiceImpl implements SimpleService, Serializable{
    private static final long serialVersionUID = 423423L;
    @Override
    public String getUsername() throws RemoteException {
       return "wuxc";
    }
}
