package cn.bingo.dig.java.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriterHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {

  private ByteBuffer buf;

  public WriterHandler(ByteBuffer buf) {
    super();
    this.buf = buf;
  }


  public WriterHandler() {
    super();
  }


  @Override
  public void completed(Integer result, AsynchronousSocketChannel attachment) {
    log.debug("WriterHandler completed ,length[{}]", result);

  }

  @Override
  public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
    // TODO Auto-generated method stub

  }


}
