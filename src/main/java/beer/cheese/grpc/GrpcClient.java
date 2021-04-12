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

    public GrpcClient(Channel channel){
        this.blockingStub = HelloServiceGrpc.newBlockingStub(channel);
        this.asyncStub = HelloServiceGrpc.newStub(channel);
    }
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:51477").usePlaintext().build();
        GrpcClient client = new GrpcClient(channel);
        client.fetch();
    }

    public void fetch(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Iterator<Transport.Response> response = blockingStub.serverStream(Transport.Request.newBuilder().setParams("hello").build());
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
