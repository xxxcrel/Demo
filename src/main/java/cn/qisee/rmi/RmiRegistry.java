package cn.qisee.rmi;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class RmiRegistry {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RmiConfig.class);
        RmiServiceExporter exporter = context.getBean(RmiServiceExporter.class);
    }
}
