package cn.bingo.dig.java.io.bio.bio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
      while (true) {
        Socket socket = server.accept();
        requestHandler(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void requestHandler(Socket socket) {
    try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(
            new BufferedReader(new InputStreamReader(socket.getInputStream())))) {

      String line = reader.readLine();
      while (line != null) {
        log.debug(line);
        out.write(line + "\r");
        out.flush();
        line = reader.readLine();
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
