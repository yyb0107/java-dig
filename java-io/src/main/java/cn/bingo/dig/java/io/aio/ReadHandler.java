package cn.bingo.dig.java.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
  private ByteBuffer buf;
  private String line;

  public ReadHandler(ByteBuffer buf) {
    super();
    this.buf = buf;
  }

  @Override
  public void completed(Integer result, AsynchronousSocketChannel socketChannel) {
    // TODO Auto-generated method stub
    buf.flip();
    line = new String(buf.array(), 0, result);
    log.debug("result={},string={}", result, line);
    buf.clear();
    log.debug("Thread-ID[{}]", Thread.currentThread().getId());
    if (line.endsWith("\r")) {
      buf.clear();
      buf = ByteBuffer.wrap(new String("Server had received the message!").getBytes());
      socketChannel.write(buf, socketChannel, new WriterHandler(buf));
    } else {
      socketChannel.read(buf, socketChannel, this);
    }
  }

  @Override
  public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
    // TODO Auto-generated method stub
    log.error("server failed...");
    exc.printStackTrace();
  }


}
