package practice.cafe.user.rpc;

import static practice.cafe.user.rpc.CafeUserServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: rpc/user.proto")
public final class ReactorCafeUserServiceGrpc {
    private ReactorCafeUserServiceGrpc() {}

    public static ReactorCafeUserServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorCafeUserServiceStub(channel);
    }

    public static final class ReactorCafeUserServiceStub extends io.grpc.stub.AbstractStub<ReactorCafeUserServiceStub> {
        private CafeUserServiceGrpc.CafeUserServiceStub delegateStub;

        private ReactorCafeUserServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = CafeUserServiceGrpc.newStub(channel);
        }

        private ReactorCafeUserServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = CafeUserServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorCafeUserServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorCafeUserServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.ListCafeUsersResp> listCafeUsers(reactor.core.publisher.Mono<practice.cafe.user.rpc.ListCafeUsersReq> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::listCafeUsers, getCallOptions());
        }

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.GetCafeUserResp> getCafeUser(reactor.core.publisher.Mono<practice.cafe.user.rpc.GetCafeUserReq> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getCafeUser, getCallOptions());
        }

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.ListCafeUsersResp> listCafeUsers(practice.cafe.user.rpc.ListCafeUsersReq reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::listCafeUsers, getCallOptions());
        }

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.GetCafeUserResp> getCafeUser(practice.cafe.user.rpc.GetCafeUserReq reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getCafeUser, getCallOptions());
        }

    }

    public static abstract class CafeUserServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.ListCafeUsersResp> listCafeUsers(reactor.core.publisher.Mono<practice.cafe.user.rpc.ListCafeUsersReq> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<practice.cafe.user.rpc.GetCafeUserResp> getCafeUser(reactor.core.publisher.Mono<practice.cafe.user.rpc.GetCafeUserReq> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            practice.cafe.user.rpc.CafeUserServiceGrpc.getListCafeUsersMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            practice.cafe.user.rpc.ListCafeUsersReq,
                                            practice.cafe.user.rpc.ListCafeUsersResp>(
                                            this, METHODID_LIST_CAFE_USERS)))
                    .addMethod(
                            practice.cafe.user.rpc.CafeUserServiceGrpc.getGetCafeUserMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            practice.cafe.user.rpc.GetCafeUserReq,
                                            practice.cafe.user.rpc.GetCafeUserResp>(
                                            this, METHODID_GET_CAFE_USER)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

    }

    public static final int METHODID_LIST_CAFE_USERS = 0;
    public static final int METHODID_GET_CAFE_USER = 1;

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
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((practice.cafe.user.rpc.ListCafeUsersReq) request,
                            (io.grpc.stub.StreamObserver<practice.cafe.user.rpc.ListCafeUsersResp>) responseObserver,
                            serviceImpl::listCafeUsers);
                    break;
                case METHODID_GET_CAFE_USER:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((practice.cafe.user.rpc.GetCafeUserReq) request,
                            (io.grpc.stub.StreamObserver<practice.cafe.user.rpc.GetCafeUserResp>) responseObserver,
                            serviceImpl::getCafeUser);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
