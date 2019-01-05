package cn.bingo.dig.java.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {



  private final int PORT = 8088;

  public void start() {
    try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();) {
      InetSocketAddress address = new InetSocketAddress(PORT);
      serverChannel.bind(address);
      serverChannel.accept(this, new AcceptHandler());
      log.debug("Server started up... port[{}]", PORT);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  public static void main(String[] args) {
    Server server = new Server();
    server.start();

  }
}


@Slf4j
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
  @Override
  public void completed(AsynchronousSocketChannel socketChannel, Server server) {
    // super.completed(socketChannel, server);
    try {
      ByteBuffer dst = ByteBuffer.allocate(1024);
      Future<Integer> future = socketChannel.read(dst);
      int len = future.get();
      dst.flip();
      log.debug("read len: {}", len);
      String request = new String(dst.array(), 0, len);
      log.debug("String from client {}", request);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void failed(Throwable arg0, Server arg1) {
    // TODO Auto-generated method stub
    log.error("failed... ");
  }
}
