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
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.serverStreamingRpc
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
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
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return The single response from the server.
     */
    suspend fun createRoom(request: Player): Response = unaryRpc(
      channel,
      GameGrpc.getCreateRoomMethod(),
      request,
      callOptions,
      Metadata()
    )
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
    fun getRooms(request: Empty): Flow<Room> = serverStreamingRpc(
      channel,
      GameGrpc.getGetRoomsMethod(),
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
    suspend fun joinRoom(request: Player): Response = unaryRpc(
      channel,
      GameGrpc.getJoinRoomMethod(),
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
    suspend fun getState(request: Player): State = unaryRpc(
      channel,
      GameGrpc.getGetStateMethod(),
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
    suspend fun sendInputData(request: InputData): Empty = unaryRpc(
      channel,
      GameGrpc.getSendInputDataMethod(),
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
    suspend fun receiveNextId(request: Empty): PlayerId = unaryRpc(
      channel,
      GameGrpc.getReceiveNextIdMethod(),
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
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.createRoom.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun createRoom(request: Player): Response = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.createRoom is unimplemented"))

    /**
     * Returns a [Flow] of responses to an RPC for inc.roguelike.babusya.network.gen.Game.getRooms.
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
    open fun getRooms(request: Empty): Flow<Room> = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.getRooms is unimplemented"))

    /**
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.joinRoom.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun joinRoom(request: Player): Response = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.joinRoom is unimplemented"))

    /**
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.getState.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getState(request: Player): State = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.getState is unimplemented"))

    /**
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.sendInputData.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun sendInputData(request: InputData): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.sendInputData is unimplemented"))

    /**
     * Returns the response to an RPC for inc.roguelike.babusya.network.gen.Game.receiveNextId.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun receiveNextId(request: Empty): PlayerId = throw
        StatusException(UNIMPLEMENTED.withDescription("Method inc.roguelike.babusya.network.gen.Game.receiveNextId is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getCreateRoomMethod(),
      implementation = ::createRoom
    ))
      .addMethod(serverStreamingServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getGetRoomsMethod(),
      implementation = ::getRooms
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getJoinRoomMethod(),
      implementation = ::joinRoom
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getGetStateMethod(),
      implementation = ::getState
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getSendInputDataMethod(),
      implementation = ::sendInputData
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GameGrpc.getReceiveNextIdMethod(),
      implementation = ::receiveNextId
    )).build()
  }
}
