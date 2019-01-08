package cn.bingo.dig.java.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AcceptFuture implements CompletionHandler<AsynchronousSocketChannel, Server> {
  @Override
  public void completed(AsynchronousSocketChannel socketChannel, Server server) {
    try {
      ByteBuffer dst = ByteBuffer.allocate(1024);
      Future<Integer> future = null;
      int len = -1;
      while (true) {
        dst.clear();
        future = socketChannel.read(dst);
        len = future.get(1000, TimeUnit.MILLISECONDS);
        if (len == -1) {
          break;
        }
        dst.flip();
        log.debug("read len: {}", len);
        String request = new String(dst.array(), 0, len);
        log.debug("String from client {}", request);
      }
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TimeoutException e) {
      log.error("future.get() timeout...");
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
      // server.getServerChannel().accept(server, this);
    }
  }
}