package practice.cafe.user.rpc

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import practice.cafe.user.rpc.CafeUserServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for practice.cafe.user.CafeUserService.
 */
object CafeUserServiceGrpcKt {
  const val SERVICE_NAME: String = CafeUserServiceGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = CafeUserServiceGrpc.getServiceDescriptor()

  val listCafeUsersMethod: MethodDescriptor<ListCafeUsersReq, ListCafeUsersResp>
    @JvmStatic
    get() = CafeUserServiceGrpc.getListCafeUsersMethod()

  val getCafeUserMethod: MethodDescriptor<GetCafeUserReq, GetCafeUserResp>
    @JvmStatic
    get() = CafeUserServiceGrpc.getGetCafeUserMethod()

  /**
   * A stub for issuing RPCs to a(n) practice.cafe.user.CafeUserService service as suspending
   * coroutines.
   */
  @StubFor(CafeUserServiceGrpc::class)
  class CafeUserServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<CafeUserServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): CafeUserServiceCoroutineStub =
        CafeUserServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun listCafeUsers(request: ListCafeUsersReq, headers: Metadata = Metadata()):
        ListCafeUsersResp = unaryRpc(
      channel,
      CafeUserServiceGrpc.getListCafeUsersMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getCafeUser(request: GetCafeUserReq, headers: Metadata = Metadata()):
        GetCafeUserResp = unaryRpc(
      channel,
      CafeUserServiceGrpc.getGetCafeUserMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the practice.cafe.user.CafeUserService service based on Kotlin
   * coroutines.
   */
  abstract class CafeUserServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for practice.cafe.user.CafeUserService.ListCafeUsers.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun listCafeUsers(request: ListCafeUsersReq): ListCafeUsersResp = throw
        StatusException(UNIMPLEMENTED.withDescription("Method practice.cafe.user.CafeUserService.ListCafeUsers is unimplemented"))

    /**
     * Returns the response to an RPC for practice.cafe.user.CafeUserService.GetCafeUser.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getCafeUser(request: GetCafeUserReq): GetCafeUserResp = throw
        StatusException(UNIMPLEMENTED.withDescription("Method practice.cafe.user.CafeUserService.GetCafeUser is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = CafeUserServiceGrpc.getListCafeUsersMethod(),
      implementation = ::listCafeUsers
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = CafeUserServiceGrpc.getGetCafeUserMethod(),
      implementation = ::getCafeUser
    )).build()
  }
}
