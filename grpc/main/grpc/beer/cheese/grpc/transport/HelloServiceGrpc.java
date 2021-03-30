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
  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Point,
      beer.cheese.grpc.transport.Transport.Feature> getGetFeatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFeature",
      requestType = beer.cheese.grpc.transport.Transport.Point.class,
      responseType = beer.cheese.grpc.transport.Transport.Feature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Point,
      beer.cheese.grpc.transport.Transport.Feature> getGetFeatureMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Point, beer.cheese.grpc.transport.Transport.Feature> getGetFeatureMethod;
    if ((getGetFeatureMethod = HelloServiceGrpc.getGetFeatureMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getGetFeatureMethod = HelloServiceGrpc.getGetFeatureMethod) == null) {
          HelloServiceGrpc.getGetFeatureMethod = getGetFeatureMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Point, beer.cheese.grpc.transport.Transport.Feature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFeature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Point.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Feature.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("GetFeature"))
              .build();
        }
      }
    }
    return getGetFeatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Rectangle,
      beer.cheese.grpc.transport.Transport.Feature> getListFeaturesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListFeatures",
      requestType = beer.cheese.grpc.transport.Transport.Rectangle.class,
      responseType = beer.cheese.grpc.transport.Transport.Feature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Rectangle,
      beer.cheese.grpc.transport.Transport.Feature> getListFeaturesMethod() {
    io.grpc.MethodDescriptor<beer.cheese.grpc.transport.Transport.Rectangle, beer.cheese.grpc.transport.Transport.Feature> getListFeaturesMethod;
    if ((getListFeaturesMethod = HelloServiceGrpc.getListFeaturesMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getListFeaturesMethod = HelloServiceGrpc.getListFeaturesMethod) == null) {
          HelloServiceGrpc.getListFeaturesMethod = getListFeaturesMethod =
              io.grpc.MethodDescriptor.<beer.cheese.grpc.transport.Transport.Rectangle, beer.cheese.grpc.transport.Transport.Feature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListFeatures"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Rectangle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  beer.cheese.grpc.transport.Transport.Feature.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("ListFeatures"))
              .build();
        }
      }
    }
    return getListFeaturesMethod;
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
    public void getFeature(beer.cheese.grpc.transport.Transport.Point request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFeatureMethod(), responseObserver);
    }

    /**
     */
    public void listFeatures(beer.cheese.grpc.transport.Transport.Rectangle request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListFeaturesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetFeatureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Point,
                beer.cheese.grpc.transport.Transport.Feature>(
                  this, METHODID_GET_FEATURE)))
          .addMethod(
            getListFeaturesMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                beer.cheese.grpc.transport.Transport.Rectangle,
                beer.cheese.grpc.transport.Transport.Feature>(
                  this, METHODID_LIST_FEATURES)))
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
    public void getFeature(beer.cheese.grpc.transport.Transport.Point request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFeatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listFeatures(beer.cheese.grpc.transport.Transport.Rectangle request,
        io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListFeaturesMethod(), getCallOptions()), request, responseObserver);
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
    public beer.cheese.grpc.transport.Transport.Feature getFeature(beer.cheese.grpc.transport.Transport.Point request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFeatureMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<beer.cheese.grpc.transport.Transport.Feature> listFeatures(
        beer.cheese.grpc.transport.Transport.Rectangle request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListFeaturesMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<beer.cheese.grpc.transport.Transport.Feature> getFeature(
        beer.cheese.grpc.transport.Transport.Point request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFeatureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_FEATURE = 0;
  private static final int METHODID_LIST_FEATURES = 1;

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
        case METHODID_GET_FEATURE:
          serviceImpl.getFeature((beer.cheese.grpc.transport.Transport.Point) request,
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature>) responseObserver);
          break;
        case METHODID_LIST_FEATURES:
          serviceImpl.listFeatures((beer.cheese.grpc.transport.Transport.Rectangle) request,
              (io.grpc.stub.StreamObserver<beer.cheese.grpc.transport.Transport.Feature>) responseObserver);
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
              .addMethod(getGetFeatureMethod())
              .addMethod(getListFeaturesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
