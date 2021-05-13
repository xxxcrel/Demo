package beer.cheese.grpc;

import java.util.Iterator;

import beer.cheese.grpc.transport.HelloServiceGrpc;
import beer.cheese.grpc.transport.Transport;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcClient {

    private HelloServiceGrpc.HelloServiceBlockingStub blockingStub;
    private HelloServiceGrpc.HelloServiceStub asyncStub;
    private String name;

    public GrpcClient(Channel channel, String identity){
        this.blockingStub = HelloServiceGrpc.newBlockingStub(channel);
        this.asyncStub = HelloServiceGrpc.newStub(channel);
        this.name = identity;
    }
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:51477").usePlaintext().build();
        GrpcClient client = new GrpcClient(channel, "Client1");
        GrpcClient client1 = new GrpcClient(channel, "Client2");
        GrpcClient client2 = new GrpcClient(channel, "Client3");
        client.unary();
        client.fetch();
        client1.unary();
        client1.fetch();
        client2.unary();
        client2.fetch();
    }

    public void unary(){
        Thread thread = new Thread(() -> {
            blockingStub.unary(Transport.Request.newBuilder().setParams(name + "says unary").build());
        });
        thread.start();
    }

    public void fetch(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Iterator<Transport.Response> response = blockingStub.serverStream(Transport.Request.newBuilder().setParams(name + "says serverStream").build());
                    while(response.hasNext()){
                        Transport.Response resp = response.next();
                        System.out.println(resp.getMessage());
                    }
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
    }
}
