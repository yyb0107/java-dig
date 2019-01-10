package cn.bingo.dig.java.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
  private int port = 8088;
  private boolean running = true;

  public static void main(String[] args) {
    Client client = new Client();
    client.connect();
  }

  public void connect() {
    InetSocketAddress address = new InetSocketAddress("127.0.0.1", port);
    try (AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();) {
      socketChannel.connect(address);
      log.debug("client connected server{} succeed!", address);
      ByteBuffer buf = ByteBuffer.allocate(1024);
      socketChannel.write(buf, socketChannel, new ClientWriteHandler());
      buf.clear();
      Future<Integer> future = socketChannel.read(buf);
      log.debug("future.get() = ", future.get());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }

  public void shutdown() {
    running = false;
  }
}
