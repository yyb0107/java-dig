package cn.bingo.dig.java.io.bio.bio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
  private int port = 8088;
  private ServerSocket server;

  public static void main(String[] args) {
    Server server = new Server();
    server.init();
  }

  public void init() {
    try {
      server = new ServerSocket(port);
      log.debug("Server start port " + port);
      Acceptor acceptor = new Acceptor();
      while (true) {
        Socket socket = server.accept();
        acceptor.accept(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
