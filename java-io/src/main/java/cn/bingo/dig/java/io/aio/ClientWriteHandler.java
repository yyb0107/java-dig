package cn.bingo.dig.java.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientWriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {

  @Override
  public void completed(Integer result, AsynchronousSocketChannel attachment) {
    String line = null;
    for (int i = 0; i < 6; i++) {
      line = new String("Hello World " + i);
      log.debug("{}", line);
      if (i == 5) {
        line += "\r";
      }
      Future<Integer> rs = attachment.write(ByteBuffer.wrap(line.getBytes()));
      try {
        log.debug("rs.get() = " + rs.get());
      } catch (InterruptedException | ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    log.debug("client send msg succeed!");
  }

  @Override
  public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
    // TODO Auto-generated method stub

  }


}
