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
  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.Message> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveMessage",
      requestType = inc.roguelike.babusya.network.gen.Empty.class,
      responseType = inc.roguelike.babusya.network.gen.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty,
      inc.roguelike.babusya.network.gen.Message> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.Message> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = GameGrpc.getReceiveMessageMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getReceiveMessageMethod = GameGrpc.getReceiveMessageMethod) == null) {
          GameGrpc.getReceiveMessageMethod = getReceiveMessageMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Empty, inc.roguelike.babusya.network.gen.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Message.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("receiveMessage"))
              .build();
        }
      }
    }
    return getReceiveMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Message,
      inc.roguelike.babusya.network.gen.Empty> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = inc.roguelike.babusya.network.gen.Message.class,
      responseType = inc.roguelike.babusya.network.gen.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Message,
      inc.roguelike.babusya.network.gen.Empty> getSendMessageMethod() {
    io.grpc.MethodDescriptor<inc.roguelike.babusya.network.gen.Message, inc.roguelike.babusya.network.gen.Empty> getSendMessageMethod;
    if ((getSendMessageMethod = GameGrpc.getSendMessageMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getSendMessageMethod = GameGrpc.getSendMessageMethod) == null) {
          GameGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<inc.roguelike.babusya.network.gen.Message, inc.roguelike.babusya.network.gen.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.roguelike.babusya.network.gen.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("sendMessage"))
              .build();
        }
      }
    }
    return getSendMessageMethod;
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
    public void receiveMessage(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
    }

    /**
     */
    public void sendMessage(inc.roguelike.babusya.network.gen.Message request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReceiveMessageMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Empty,
                inc.roguelike.babusya.network.gen.Message>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                inc.roguelike.babusya.network.gen.Message,
                inc.roguelike.babusya.network.gen.Empty>(
                  this, METHODID_SEND_MESSAGE)))
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
    public void receiveMessage(inc.roguelike.babusya.network.gen.Empty request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Message> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(inc.roguelike.babusya.network.gen.Message request,
        io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
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
    public java.util.Iterator<inc.roguelike.babusya.network.gen.Message> receiveMessage(
        inc.roguelike.babusya.network.gen.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public inc.roguelike.babusya.network.gen.Empty sendMessage(inc.roguelike.babusya.network.gen.Message request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<inc.roguelike.babusya.network.gen.Empty> sendMessage(
        inc.roguelike.babusya.network.gen.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_MESSAGE = 0;
  private static final int METHODID_SEND_MESSAGE = 1;

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
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((inc.roguelike.babusya.network.gen.Empty) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Message>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((inc.roguelike.babusya.network.gen.Message) request,
              (io.grpc.stub.StreamObserver<inc.roguelike.babusya.network.gen.Empty>) responseObserver);
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
              .addMethod(getReceiveMessageMethod())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
