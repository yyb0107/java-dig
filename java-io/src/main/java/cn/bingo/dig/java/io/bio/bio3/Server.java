package cn.bingo.dig.java.io.bio.bio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
  private int port = 8088;
  private ServerSocket server;
  private ExecutorService executorService = Executors.newFixedThreadPool(10);

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
        requestHandler(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void requestHandler(Socket socket) {
    byte[] buf = new byte[1024];
    try (DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

      int len = dis.read(buf);
      while (len != -1) {
        String line = new String(buf,0,len);
        log.debug(line);
        dos.write(buf,0,len);
        dos.flush();
        len = dis.read(buf);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
