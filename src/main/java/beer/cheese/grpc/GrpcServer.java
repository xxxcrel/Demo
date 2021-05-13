package beer.cheese.grpc;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import beer.cheese.grpc.transport.HelloServiceGrpc;
import beer.cheese.grpc.transport.Transport;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcServer {

    private Server server;
    private int port;

    public GrpcServer(int port) {
        this(ServerBuilder.forPort(port), port);
    }

    public GrpcServer(ServerBuilder builder, int port) {
        this.port = port;
        this.server = builder
                .executor(new ThreadPoolExecutor(3, 3, 20L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("biz").build(), new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Rejected Runnable" + r);
                    }
                }))
                .addService(new GrpcService()).build();
    }

    public static void main(String[] args) throws Exception {
        GrpcServer grpcServer = new GrpcServer(51477);
        grpcServer.start();
        grpcServer.blockUntilShutdown();
    }

    public void start() throws IOException {
        server.start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                log.error("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GrpcServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                log.error("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    @CommonsLog
    private static class GrpcService extends HelloServiceGrpc.HelloServiceImplBase {

        @Override
        public void unary(Transport.Request request, StreamObserver<Transport.Response> responseObserver) {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(request.getParams());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(Transport.Response.newBuilder().setMessage("unary: bye bye").build());
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<Transport.Request> clientStream(StreamObserver<Transport.Response> responseObserver) {
            return super.clientStream(responseObserver);
        }

        @Override
        public void serverStream(Transport.Request request, StreamObserver<Transport.Response> responseObserver) {
            log.info("client in");
            int count = 10;
            while (count-- > 0) {
                log.info("empty while");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            responseObserver.onNext(Transport.Response.newBuilder().setMessage("first").build());
            responseObserver.onNext(Transport.Response.newBuilder().setMessage("second").build());
            responseObserver.onNext(Transport.Response.newBuilder().setMessage("third").build());
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<Transport.Request> bidirectional(StreamObserver<Transport.Response> responseObserver) {
            return super.bidirectional(responseObserver);
        }

    }

}