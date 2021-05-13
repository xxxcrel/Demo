package beer.cheese.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RmiClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(RmiClient.class);
        SimpleService simpleService = context.getBean(SimpleService.class);
        System.out.println(simpleService.getUsername());
//        Registry registry = LocateRegistry.getRegistry("10.66.0.226", 51477);
//        SimpleService simpleService = (SimpleService) registry.lookup("simple");
//        System.out.println(simpleService.getUsername());
    }

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://10.66.0.226:51477/simple");
        factoryBean.setServiceInterface(SimpleService.class);
        return factoryBean;
    }
}
