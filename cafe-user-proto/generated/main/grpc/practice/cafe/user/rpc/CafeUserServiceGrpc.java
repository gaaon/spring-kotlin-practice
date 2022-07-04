package practice.cafe.user.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: rpc/user.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CafeUserServiceGrpc {

  private CafeUserServiceGrpc() {}

  public static final String SERVICE_NAME = "practice.cafe.user.CafeUserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<practice.cafe.user.rpc.ListCafeUsersReq,
      practice.cafe.user.rpc.ListCafeUsersResp> getListCafeUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCafeUsers",
      requestType = practice.cafe.user.rpc.ListCafeUsersReq.class,
      responseType = practice.cafe.user.rpc.ListCafeUsersResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<practice.cafe.user.rpc.ListCafeUsersReq,
      practice.cafe.user.rpc.ListCafeUsersResp> getListCafeUsersMethod() {
    io.grpc.MethodDescriptor<practice.cafe.user.rpc.ListCafeUsersReq, practice.cafe.user.rpc.ListCafeUsersResp> getListCafeUsersMethod;
    if ((getListCafeUsersMethod = CafeUserServiceGrpc.getListCafeUsersMethod) == null) {
      synchronized (CafeUserServiceGrpc.class) {
        if ((getListCafeUsersMethod = CafeUserServiceGrpc.getListCafeUsersMethod) == null) {
          CafeUserServiceGrpc.getListCafeUsersMethod = getListCafeUsersMethod =
              io.grpc.MethodDescriptor.<practice.cafe.user.rpc.ListCafeUsersReq, practice.cafe.user.rpc.ListCafeUsersResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCafeUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  practice.cafe.user.rpc.ListCafeUsersReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  practice.cafe.user.rpc.ListCafeUsersResp.getDefaultInstance()))
              .setSchemaDescriptor(new CafeUserServiceMethodDescriptorSupplier("ListCafeUsers"))
              .build();
        }
      }
    }
    return getListCafeUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<practice.cafe.user.rpc.GetCafeUserReq,
      practice.cafe.user.rpc.GetCafeUserResp> getGetCafeUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCafeUser",
      requestType = practice.cafe.user.rpc.GetCafeUserReq.class,
      responseType = practice.cafe.user.rpc.GetCafeUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<practice.cafe.user.rpc.GetCafeUserReq,
      practice.cafe.user.rpc.GetCafeUserResp> getGetCafeUserMethod() {
    io.grpc.MethodDescriptor<practice.cafe.user.rpc.GetCafeUserReq, practice.cafe.user.rpc.GetCafeUserResp> getGetCafeUserMethod;
    if ((getGetCafeUserMethod = CafeUserServiceGrpc.getGetCafeUserMethod) == null) {
      synchronized (CafeUserServiceGrpc.class) {
        if ((getGetCafeUserMethod = CafeUserServiceGrpc.getGetCafeUserMethod) == null) {
          CafeUserServiceGrpc.getGetCafeUserMethod = getGetCafeUserMethod =
              io.grpc.MethodDescriptor.<practice.cafe.user.rpc.GetCafeUserReq, practice.cafe.user.rpc.GetCafeUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCafeUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  practice.cafe.user.rpc.GetCafeUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  practice.cafe.user.rpc.GetCafeUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new CafeUserServiceMethodDescriptorSupplier("GetCafeUser"))
              .build();
        }
      }
    }
    return getGetCafeUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CafeUserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceStub>() {
        @java.lang.Override
        public CafeUserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CafeUserServiceStub(channel, callOptions);
        }
      };
    return CafeUserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CafeUserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceBlockingStub>() {
        @java.lang.Override
        public CafeUserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CafeUserServiceBlockingStub(channel, callOptions);
        }
      };
    return CafeUserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CafeUserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CafeUserServiceFutureStub>() {
        @java.lang.Override
        public CafeUserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CafeUserServiceFutureStub(channel, callOptions);
        }
      };
    return CafeUserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CafeUserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void listCafeUsers(practice.cafe.user.rpc.ListCafeUsersReq request,
        io.grpc.stub.StreamObserver<practice.cafe.user.rpc.ListCafeUsersResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListCafeUsersMethod(), responseObserver);
    }

    /**
     */
    public void getCafeUser(practice.cafe.user.rpc.GetCafeUserReq request,
        io.grpc.stub.StreamObserver<practice.cafe.user.rpc.GetCafeUserResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCafeUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListCafeUsersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                practice.cafe.user.rpc.ListCafeUsersReq,
                practice.cafe.user.rpc.ListCafeUsersResp>(
                  this, METHODID_LIST_CAFE_USERS)))
          .addMethod(
            getGetCafeUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                practice.cafe.user.rpc.GetCafeUserReq,
                practice.cafe.user.rpc.GetCafeUserResp>(
                  this, METHODID_GET_CAFE_USER)))
          .build();
    }
  }

  /**
   */
  public static final class CafeUserServiceStub extends io.grpc.stub.AbstractAsyncStub<CafeUserServiceStub> {
    private CafeUserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CafeUserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CafeUserServiceStub(channel, callOptions);
    }

    /**
     */
    public void listCafeUsers(practice.cafe.user.rpc.ListCafeUsersReq request,
        io.grpc.stub.StreamObserver<practice.cafe.user.rpc.ListCafeUsersResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListCafeUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCafeUser(practice.cafe.user.rpc.GetCafeUserReq request,
        io.grpc.stub.StreamObserver<practice.cafe.user.rpc.GetCafeUserResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCafeUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CafeUserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CafeUserServiceBlockingStub> {
    private CafeUserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CafeUserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CafeUserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public practice.cafe.user.rpc.ListCafeUsersResp listCafeUsers(practice.cafe.user.rpc.ListCafeUsersReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListCafeUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public practice.cafe.user.rpc.GetCafeUserResp getCafeUser(practice.cafe.user.rpc.GetCafeUserReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCafeUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CafeUserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CafeUserServiceFutureStub> {
    private CafeUserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CafeUserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CafeUserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<practice.cafe.user.rpc.ListCafeUsersResp> listCafeUsers(
        practice.cafe.user.rpc.ListCafeUsersReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListCafeUsersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<practice.cafe.user.rpc.GetCafeUserResp> getCafeUser(
        practice.cafe.user.rpc.GetCafeUserReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCafeUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_CAFE_USERS = 0;
  private static final int METHODID_GET_CAFE_USER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CafeUserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CafeUserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_CAFE_USERS:
          serviceImpl.listCafeUsers((practice.cafe.user.rpc.ListCafeUsersReq) request,
              (io.grpc.stub.StreamObserver<practice.cafe.user.rpc.ListCafeUsersResp>) responseObserver);
          break;
        case METHODID_GET_CAFE_USER:
          serviceImpl.getCafeUser((practice.cafe.user.rpc.GetCafeUserReq) request,
              (io.grpc.stub.StreamObserver<practice.cafe.user.rpc.GetCafeUserResp>) responseObserver);
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

  private static abstract class CafeUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CafeUserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return practice.cafe.user.rpc.CafeUserServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CafeUserService");
    }
  }

  private static final class CafeUserServiceFileDescriptorSupplier
      extends CafeUserServiceBaseDescriptorSupplier {
    CafeUserServiceFileDescriptorSupplier() {}
  }

  private static final class CafeUserServiceMethodDescriptorSupplier
      extends CafeUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CafeUserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CafeUserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CafeUserServiceFileDescriptorSupplier())
              .addMethod(getListCafeUsersMethod())
              .addMethod(getGetCafeUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
