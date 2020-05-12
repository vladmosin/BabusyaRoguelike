package inc.roguelike.babusya.network.gen

import inc.roguelike.babusya.network.gen.GameGrpc.getServiceDescriptor
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.serverStreamingRpc
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.serverStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlinx.coroutines.flow.Flow

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * inc.roguelike.babusya.network.gen.Game.
 */
object GameGrpcKt {
  /**
   * A stub for issuing RPCs to a(n) inc.roguelike.babusya.network.gen.Game service as suspending
   * coroutines.
   */
  @StubFor(GameGrpc::class)
  class GameCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<GameCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): GameCoroutineStub =
        GameCoroutineStub(channel, callOptions)

    /**
     * Returns a [Flow] that, when collected, executes this RPC and emits responses from the
     * server as they arrive.  That flow finishes normally if the server closes its response with
     * [`Status.OK`][Status], and fails by throwing a [StatusException] otherwise.  If
     * collecting the flow downstream fails exceptionally (including via cancellation), the RPC
     * is cancelled with that exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return A flow that, when collected, emits the responses from the server.
     */
    fun receiveMessage(request: Empty): Flow<Message> = serverStreamingRpc(
      channel,
      GameGrpc.getReceiveMessageMethod(),
      request,
      callOptions,
      Metadata()
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return The single response from the server.
     */
    suspend fun sendMessage(request: Message): Empty = unaryRpc(
      channel,
      GameGrpc.getSendMessageMethod(),
      request,
      callOptions,
      Metadata()
    )}

  /**
   * Skeletal implementation of the inc.roguelike.babusya.network.gen.Game service based on Kotlin
   * coroutines.
   */
  abstract class GameCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns a [Flow] of responses to an RPC for
     * inc.roguelike.babusya.network.gen.Game.receiveMessage.
     *
     * If creating or collecting the returned flow fails with a [StatusException], the RPC
     * will fail with the corresponding [Status].  If it fails with a
     * [java.util.concurrent.CancellationException], the RPC will fail with status
     * `Status.CANCELLED`.  If creating
     * or collecting the returned flow fails for any other reason, the RPC will fail with
     * `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open fun receiveMessage(request: Empty): Flow<Message> = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.receiveMessage is unimplemented"))

    /**
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.sendMessage.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun sendMessage(request: Message): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.sendMessage is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(serverStreamingServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getReceiveMessageMethod(),
      implementation = ::receiveMessage
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getSendMessageMethod(),
      implementation = ::sendMessage
    )).build()
  }
}
