package cn.bingo.dig.java.io.bio.bio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
  private int port = 8088;

  public static void main(String[] args) {
    Client client = new Client();
    client.connect();
  }

  public void connect() {
    try (Socket socket = new Socket("127.0.0.1", port);) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      for (int i = 0; i < 6; i++) {
        writer.write("Hello World " + i);
        writer.write("\r");
        writer.flush();
      }
      String line = reader.readLine();
      while (line != null) {
        log.debug("from server: " + line);
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
