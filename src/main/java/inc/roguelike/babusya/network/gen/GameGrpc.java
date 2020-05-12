package inc.roguelike.babusya.network.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: game.proto")
public final class GameGrpc {

  private GameGrpc() {}

  public static final String SERVICE_NAME = "inc.roguelike.babusya.network.gen.Game";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player,
      inc.roguelike.babusya.network.gen.Response> getCreateRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createRoom",
      requestType = inc.roguelike.babusya.network.gen.Player.class,
      responseType = inc.roguelike.babusya.network.gen.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player,
      inc.roguelike.babusya.network.gen.Response> getCreateRoomMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player, inc.roguelike.babusya.network.gen.Response> getCreateRoomMethod;
    if ((getCreateRoomMethod = GameGrpc.getCreateRoomMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getCreateRoomMethod = GameGrpc.getCreateRoomMethod) == null) {
          GameGrpc.getCreateRoomMethod = getCreateRoomMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Player, inc.roguelike.babusya.network.gen.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("createRoom"))
              .build();
        }
      }
    }
    return getCreateRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.Room> getGetRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRooms",
      requestType = inc.roguelike.babusya.network.gen.Empty.class,
      responseType = inc.roguelike.babusya.network.gen.Room.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.Room> getGetRoomsMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.Room> getGetRoomsMethod;
    if ((getGetRoomsMethod = GameGrpc.getGetRoomsMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getGetRoomsMethod = GameGrpc.getGetRoomsMethod) == null) {
          GameGrpc.getGetRoomsMethod = getGetRoomsMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.Room>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Room.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("getRooms"))
              .build();
        }
      }
    }
    return getGetRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player,
      inc.roguelike.babusya.network.gen.Response> getJoinRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinRoom",
      requestType = inc.roguelike.babusya.network.gen.Player.class,
      responseType = inc.roguelike.babusya.network.gen.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player,
      inc.roguelike.babusya.network.gen.Response> getJoinRoomMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Player, inc.roguelike.babusya.network.gen.Response> getJoinRoomMethod;
    if ((getJoinRoomMethod = GameGrpc.getJoinRoomMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getJoinRoomMethod = GameGrpc.getJoinRoomMethod) == null) {
          GameGrpc.getJoinRoomMethod = getJoinRoomMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Player, inc.roguelike.babusya.network.gen.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "joinRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("joinRoom"))
              .build();
        }
      }
    }
    return getJoinRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.State> getGetStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getState",
      requestType = inc.roguelike.babusya.network.gen.Empty.class,
      responseType = inc.roguelike.babusya.network.gen.State.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.State> getGetStateMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.State> getGetStateMethod;
    if ((getGetStateMethod = GameGrpc.getGetStateMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getGetStateMethod = GameGrpc.getGetStateMethod) == null) {
          GameGrpc.getGetStateMethod = getGetStateMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.State>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.State.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("getState"))
              .build();
        }
      }
    }
    return getGetStateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameStub>() {
        @java.lang.Override
        public GameStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameStub(channel, callOptions);
        }
      };
    return GameStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameBlockingStub>() {
        @java.lang.Override
        public GameBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameBlockingStub(channel, callOptions);
        }
      };
    return GameBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameFutureStub>() {
        @java.lang.Override
        public GameFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameFutureStub(channel, callOptions);
        }
      };
    return GameFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GameImplBase implements io.grpc.BindableService {

    /**
     */
    public void createRoom(inc.roguelike.babusya.network.gen.Player request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateRoomMethod(), responseObserver);
    }

    /**
     */
    public void getRooms(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Room> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRoomsMethod(), responseObserver);
    }

    /**
     */
    public void joinRoom(inc.roguelike.babusya.network.gen.Player request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinRoomMethod(), responseObserver);
    }

    /**
     */
    public void getState(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.State> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateRoomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Player,
                inc.roguelike.babusya.network.gen.Response>(
                  this, METHODID_CREATE_ROOM)))
          .addMethod(
            getGetRoomsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Empty,
                inc.roguelike.babusya.network.gen.Room>(
                  this, METHODID_GET_ROOMS)))
          .addMethod(
            getJoinRoomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Player,
                inc.roguelike.babusya.network.gen.Response>(
                  this, METHODID_JOIN_ROOM)))
          .addMethod(
            getGetStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Empty,
                inc.roguelike.babusya.network.gen.State>(
                  this, METHODID_GET_STATE)))
          .build();
    }
  }

  /**
   */
  public static final class GameStub extends io.grpc.stub.AbstractAsyncStub<GameStub> {
    private GameStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameStub(channel, callOptions);
    }

    /**
     */
    public void createRoom(inc.roguelike.babusya.network.gen.Player request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRooms(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Room> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinRoom(inc.roguelike.babusya.network.gen.Player request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getState(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.State> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GameBlockingStub extends io.grpc.stub.AbstractBlockingStub<GameBlockingStub> {
    private GameBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameBlockingStub(channel, callOptions);
    }

    /**
     */
    public inc.roguelike.babusya.network.gen.Response createRoom(inc.roguelike.babusya.network.gen.Player request) {
      return blockingUnaryCall(
          getChannel(), getCreateRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<inc.roguelike.babusya.network.gen.Room> getRooms(
        inc.roguelike.babusya.network.gen.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetRoomsMethod(), getCallOptions(), request);
    }

    /**
     */
    public inc.roguelike.babusya.network.gen.Response joinRoom(inc.roguelike.babusya.network.gen.Player request) {
      return blockingUnaryCall(
          getChannel(), getJoinRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public inc.roguelike.babusya.network.gen.State getState(inc.roguelike.babusya.network.gen.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetStateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GameFutureStub extends io.grpc.stub.AbstractFutureStub<GameFutureStub> {
    private GameFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<inc.roguelike.babusya.network.gen.Response> createRoom(
        inc.roguelike.babusya.network.gen.Player request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<inc.roguelike.babusya.network.gen.Response> joinRoom(
        inc.roguelike.babusya.network.gen.Player request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<inc.roguelike.babusya.network.gen.State> getState(
        inc.roguelike.babusya.network.gen.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ROOM = 0;
  private static final int METHODID_GET_ROOMS = 1;
  private static final int METHODID_JOIN_ROOM = 2;
  private static final int METHODID_GET_STATE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GameImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_ROOM:
          serviceImpl.createRoom((inc.roguelike.babusya.network.gen.Player) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response>) responseObserver);
          break;
        case METHODID_GET_ROOMS:
          serviceImpl.getRooms((inc.roguelike.babusya.network.gen.Empty) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Room>) responseObserver);
          break;
        case METHODID_JOIN_ROOM:
          serviceImpl.joinRoom((inc.roguelike.babusya.network.gen.Player) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Response>) responseObserver);
          break;
        case METHODID_GET_STATE:
          serviceImpl.getState((inc.roguelike.babusya.network.gen.Empty) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.State>) responseObserver);
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

  private static abstract class GameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return inc.roguelike.babusya.network.gen.GameOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Game");
    }
  }

  private static final class GameFileDescriptorSupplier
      extends GameBaseDescriptorSupplier {
    GameFileDescriptorSupplier() {}
  }

  private static final class GameMethodDescriptorSupplier
      extends GameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GameMethodDescriptorSupplier(String methodName) {
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
      synchronized (GameGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameFileDescriptorSupplier())
              .addMethod(getCreateRoomMethod())
              .addMethod(getGetRoomsMethod())
              .addMethod(getJoinRoomMethod())
              .addMethod(getGetStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
