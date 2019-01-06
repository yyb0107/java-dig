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
  private AsynchronousServerSocketChannel serverChannel;


  private final int PORT = 8088;

  public void start() {
    try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();) {
      this.serverChannel = serverChannel;
      InetSocketAddress address = new InetSocketAddress(PORT);
      this.serverChannel.bind(address, 100);
      this.serverChannel.accept(this, new AcceptHandler());
      log.debug("Server started up... port[{}]", PORT);
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            // System.out.println("运行中...");
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      t.start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  public AsynchronousServerSocketChannel getServerChannel() {
    return serverChannel;
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
//      ByteBuffer dst = ByteBuffer.allocate(1024);
//      Future<Integer> future = socketChannel.read(dst);
//      int len = future.get();
//      dst.flip();
//      log.debug("read len: {}", len);
//      String request = new String(dst.array(), 0, len);
      log.debug("String from client {}", server);
    } finally {
      server.getServerChannel().accept(server, this);
    }
  }

  @Override
  public void failed(Throwable exception, Server server) {
    // TODO Auto-generated method stub
    try {
      log.error("failed... ");
      exception.printStackTrace();
      log.error("{}", server.getServerChannel());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
//      server.getServerChannel().accept(server, this);
    }
  }
}
