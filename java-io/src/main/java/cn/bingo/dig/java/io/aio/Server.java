package cn.bingo.dig.java.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
      log.debug("this.serverChannel.isOpen() {}", this.serverChannel.isOpen());
      // this.serverChannel.accept(this, new AcceptFuture());
      this.serverChannel.accept(this, new AcceptHandler());
      log.debug("Server started up... port[{}]", PORT);
      // to avoid progress terminate after call serverChannel.accept()
      while (true) {
        try {
          // log.debug("the main thread[{}] is sleeping",Thread.currentThread().getName());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
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


