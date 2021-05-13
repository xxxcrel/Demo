package beer.cheese.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration(proxyBeanMethods = false)
public class RmiConfig implements Serializable {

    private static final long serialVersionUID = 123L;
    @Bean
    public RmiServiceExporter rmiServiceExporter() {
        SimpleService simpleService = new SimpleServiceImpl();
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("simple");
        exporter.setServiceInterface(SimpleService.class);
        exporter.setService(simpleService);
//        exporter.setRegistryHost("127.0.0.1");
        exporter.setRegistryPort(51477);
        exporter.setServicePort(51488);
        exporter.setRegistryServerSocketFactory(new RMIServerSocketFactory() {
            @Override
            public ServerSocket createServerSocket(int port) throws IOException {
                return new ServerSocket(port, 0, InetAddress.getByName("10.66.0.226"));
            }
        });
        exporter.setRegistryClientSocketFactory(Socket::new);
        exporter.setServerSocketFactory(new RMIServerSocketFactory() {
            @Override
            public ServerSocket createServerSocket(int port) throws IOException {
               return new ServerSocket(port, 0, InetAddress.getByName("10.66.0.226"));
            }
        });
        exporter.setClientSocketFactory(new CustomClientSocket());
        return exporter;
    }

}

//See @RMIClientSocketFactory docs
//must implements Object.equals and return true
class CustomClientSocket implements RMIClientSocketFactory, Serializable{

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(InetAddress.getByName("10.66.0.226"), port);
    }
}