package beer.cheese.grpc;

import beer.cheese.grpc.transport.HelloServiceGrpc;
import beer.cheese.grpc.transport.Transport;
import io.grpc.stub.StreamObserver;

public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void getFeature(Transport.Point request, StreamObserver<Transport.Feature> responseObserver) {
        super.getFeature(request, responseObserver);
    }

    @Override
    public void listFeatures(Transport.Rectangle request, StreamObserver<Transport.Feature> responseObserver) {
        super.listFeatures(request, responseObserver);
    }
}