package cn.bingo.dig.java.io.aio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsynFileReader {

  public void read() {
    File file = new File(
        "D:\\workspace\\java\\java-dig\\category\\java-io\\src\\main\\resources\\logback.xml");
    log.debug("file.toPath {}", file.toPath());
    try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath());) {
      ByteBuffer dst = ByteBuffer.allocate(1024);
      Future<Integer> future = fileChannel.read(dst, 0l);
      Integer len = future.get();

      dst.flip();
      String line = new String(dst.array(), 0, len);
      log.debug("result {} ", line);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    AsynFileReader reader = new AsynFileReader();
    reader.read();
  }

}
