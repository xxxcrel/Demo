package beer.cheese.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

//See @RMIClientSocketFactory docs
//must implements Object.equals and return true
public class CustomClientSocket implements RMIClientSocketFactory, Serializable {

    private static final long serialVersionUID = 123123L;
    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new int[]{1, 2, 3});
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(InetAddress.getByName("10.66.0.226"), port);
    }
}
