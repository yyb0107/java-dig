package cn.bingo.dig.java.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileChannelDemo {

  public void fileRead() {
    File file = new File("note/AsynchronousIODemo.md");
    try (FileChannel fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
        StringWriter writer = new StringWriter();) {
      ByteBuffer buf = ByteBuffer.allocate(1024);
      int len = fileChannel.read(buf);
      String line = null;
      while (len != -1) {
        // buf.flip();
        line = new String(buf.array(), 0, len);
        log.debug(line);
        log.debug("len {},buf {}", len, buf);
        buf.clear();
        len = fileChannel.read(buf);
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    FileChannelDemo demo = new FileChannelDemo();
    demo.fileRead();
  }
}
