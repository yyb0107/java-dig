package cn.bingo.dig.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
  private boolean running = true;
  private final int DEFULE_PORT = 8088;

  public static void main(String[] args) {
    Server server = new Server();
    server.init(8088);
  }

  public void init(int port) {
    if (port == 0) {
      port = DEFULE_PORT;
    }
    SocketAddress address = new InetSocketAddress(port);
    try (ServerSocketChannel ssc = ServerSocketChannel.open();) {
      Selector selector = Selector.open();
      ssc.bind(address);
      ssc.configureBlocking(false);
      ssc.register(selector, SelectionKey.OP_ACCEPT);
      log.info("The Server[port:{} ] is started up...", port);
      accpet(selector);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void accpet(Selector selector) throws IOException {
    while (running) {
      selector.select();
      Iterator<SelectionKey> it = selector.selectedKeys().iterator();
      ByteBuffer buf = ByteBuffer.allocate(1024);
      while (it.hasNext()) {
        SelectionKey key = it.next();
        log.debug("key info [key.isValid:{},]", key.isValid());
        if (key.isAcceptable()) {
          ServerSocketChannel channel = (ServerSocketChannel) key.channel();
          SocketChannel clientChannel = channel.accept();
          log.debug("clientChannel {}", clientChannel);
          clientChannel.configureBlocking(false);
          clientChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
          SocketChannel clientChannel = (SocketChannel) key.channel();
          int len = clientChannel.read(buf);
          String line = new String(buf.array(), 0, len);
          log.debug("the line send from client {}", line);
          buf.flip();
          clientChannel.write(buf);
          buf.clear();
        } else if (key.isWritable()) {
          // TODO
          log.info("current key is writable...");
        }
        
     // remove form the iterator
        it.remove();
      }
    }
  }

  public void shutdown() {
    running = false;
  }
}
