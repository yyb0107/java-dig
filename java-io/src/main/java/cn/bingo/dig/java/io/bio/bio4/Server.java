package cn.bingo.dig.java.io.bio.bio4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
  private int port = 8088;
  private ExecutorService executorService = Executors.newFixedThreadPool(10);
  private ServerSocket server;

  public static void main(String[] args) {
    Server server = new Server();
    server.init();
  }

  public void init() {
    try {
      server = new ServerSocket(port);
      log.debug("Server start port " + port);
      while (true) {
        Socket socket = server.accept();
        accept(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void accept(Socket socket) {
    HandlerService service = new HandlerService(socket);
    executorService.execute(clientHandler(service));
  }

  private Runnable clientHandler(HandlerService serverService) {
    return () -> {
      log.debug("the clientHandler[id={}] is start", Thread.currentThread().getId());
      executorService.execute(serverService.socketRead());
      executorService.execute(serverService.socketwrite());
    };
  }
}
