package beer.cheese.grpc.transport;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: transport.proto")
public final class HelloServiceGrpc {

  private HelloServiceGrpc() {}

  public static final String SERVICE_NAME = "HelloService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getUnaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "unary",
      requestType = beer.cheese.grpc.transport.Transport.Request.class,
      responseType = beer.cheese.grpc.transport.Transport.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getUnaryMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response> getUnaryMethod;
    if ((getUnaryMethod = HelloServiceGrpc.getUnaryMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getUnaryMethod = HelloServiceGrpc.getUnaryMethod) == null) {
          HelloServiceGrpc.getUnaryMethod = getUnaryMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "unary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Response.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("unary"))
              .build();
        }
      }
    }
    return getUnaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getClientStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clientStream",
      requestType = beer.cheese.grpc.transport.Transport.Request.class,
      responseType = beer.cheese.grpc.transport.Transport.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getClientStreamMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response> getClientStreamMethod;
    if ((getClientStreamMethod = HelloServiceGrpc.getClientStreamMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getClientStreamMethod = HelloServiceGrpc.getClientStreamMethod) == null) {
          HelloServiceGrpc.getClientStreamMethod = getClientStreamMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clientStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Response.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("clientStream"))
              .build();
        }
      }
    }
    return getClientStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getServerStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "serverStream",
      requestType = beer.cheese.grpc.transport.Transport.Request.class,
      responseType = beer.cheese.grpc.transport.Transport.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getServerStreamMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response> getServerStreamMethod;
    if ((getServerStreamMethod = HelloServiceGrpc.getServerStreamMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getServerStreamMethod = HelloServiceGrpc.getServerStreamMethod) == null) {
          HelloServiceGrpc.getServerStreamMethod = getServerStreamMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "serverStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Response.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("serverStream"))
              .build();
        }
      }
    }
    return getServerStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getBidirectionalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "bidirectional",
      requestType = beer.cheese.grpc.transport.Transport.Request.class,
      responseType = beer.cheese.grpc.transport.Transport.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request,
      beer.cheese.grpc.transport.Transport.Response> getBidirectionalMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response> getBidirectionalMethod;
    if ((getBidirectionalMethod = HelloServiceGrpc.getBidirectionalMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getBidirectionalMethod = HelloServiceGrpc.getBidirectionalMethod) == null) {
          HelloServiceGrpc.getBidirectionalMethod = getBidirectionalMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Request, beer.cheese.grpc.transport.Transport.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "bidirectional"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Response.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("bidirectional"))
              .build();
        }
      }
    }
    return getBidirectionalMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub>() {
        @java.lang.Override
        public HelloServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceStub(channel, callOptions);
        }
      };
    return HelloServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub>() {
        @java.lang.Override
        public HelloServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceBlockingStub(channel, callOptions);
        }
      };
    return HelloServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub>() {
        @java.lang.Override
        public HelloServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceFutureStub(channel, callOptions);
        }
      };
    return HelloServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HelloServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void unary(beer.cheese.grpc.transport.Transport.Request request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnaryMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Request> clientStream(
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getClientStreamMethod(), responseObserver);
    }

    /**
     */
    public void serverStream(beer.cheese.grpc.transport.Transport.Request request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getServerStreamMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Request> bidirectional(
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBidirectionalMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUnaryMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Request,
                beer.cheese.grpc.transport.Transport.Response>(
                  this, METHODID_UNARY)))
          .addMethod(
            getClientStreamMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Request,
                beer.cheese.grpc.transport.Transport.Response>(
                  this, METHODID_CLIENT_STREAM)))
          .addMethod(
            getServerStreamMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Request,
                beer.cheese.grpc.transport.Transport.Response>(
                  this, METHODID_SERVER_STREAM)))
          .addMethod(
            getBidirectionalMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Request,
                beer.cheese.grpc.transport.Transport.Response>(
                  this, METHODID_BIDIRECTIONAL)))
          .build();
    }
  }

  /**
   */
  public static final class HelloServiceStub extends io.grpc.stub.AbstractAsyncStub<HelloServiceStub> {
    private HelloServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceStub(channel, callOptions);
    }

    /**
     */
    public void unary(beer.cheese.grpc.transport.Transport.Request request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Request> clientStream(
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getClientStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void serverStream(beer.cheese.grpc.transport.Transport.Request request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getServerStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Request> bidirectional(
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getBidirectionalMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class HelloServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HelloServiceBlockingStub> {
    private HelloServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public beer.cheese.grpc.transport.Transport.Response unary(beer.cheese.grpc.transport.Transport.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<beer.cheese.grpc.transport.Transport.Response> serverStream(
        beer.cheese.grpc.transport.Transport.Request request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getServerStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HelloServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HelloServiceFutureStub> {
    private HelloServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<beer.cheese.grpc.transport.Transport.Response> unary(
        beer.cheese.grpc.transport.Transport.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnaryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UNARY = 0;
  private static final int METHODID_SERVER_STREAM = 1;
  private static final int METHODID_CLIENT_STREAM = 2;
  private static final int METHODID_BIDIRECTIONAL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UNARY:
          serviceImpl.unary((beer.cheese.grpc.transport.Transport.Request) request,
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response>) responseObserver);
          break;
        case METHODID_SERVER_STREAM:
          serviceImpl.serverStream((beer.cheese.grpc.transport.Transport.Request) request,
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientStream(
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response>) responseObserver);
        case METHODID_BIDIRECTIONAL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bidirectional(
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return beer.cheese.grpc.transport.Transport.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloService");
    }
  }

  private static final class HelloServiceFileDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier {
    HelloServiceFileDescriptorSupplier() {}
  }

  private static final class HelloServiceMethodDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloServiceFileDescriptorSupplier())
              .addMethod(getUnaryMethod())
              .addMethod(getClientStreamMethod())
              .addMethod(getServerStreamMethod())
              .addMethod(getBidirectionalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
