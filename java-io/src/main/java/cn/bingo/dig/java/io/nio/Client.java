package cn.bingo.dig.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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
    try (SocketChannel socketChannel = SocketChannel.open();) {
      socketChannel.connect(address);
      log.debug("client connected server{} succeed!", address);
      socketChannel.configureBlocking(false);
      Selector selector = Selector.open();
      socketChannel.register(selector, SelectionKey.OP_WRITE);
      while (running) {
        selector.select();
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (it.hasNext()) {
          SelectionKey key = it.next();
          it.remove();
          if (key.isValid()) {
            if (key.isWritable()) {
              SocketChannel channel = (SocketChannel) key.channel();
              for (int i = 0; i < 6; i++) {
                channel.write(ByteBuffer.wrap(new String("Hello World " + i).getBytes()));
              }
              log.debug("client send msg succeed!");
              channel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
              buf.clear();
              SocketChannel channel = (SocketChannel) key.channel();
              channel.read(buf);
              buf.flip();
              log.debug("response from server {}", new String(buf.array()));
              // channel.close();
              // channel.register(selector, SelectionKey.OP_WRITE);
            }
          }
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void shutdown() {
    running = false;
  }
}
