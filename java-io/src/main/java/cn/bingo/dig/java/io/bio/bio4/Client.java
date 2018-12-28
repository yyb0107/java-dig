package cn.bingo.dig.java.io.bio.bio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      for (int i = 0; i < 6; i++) {
        dos.write(new String("Hello World " + i).getBytes());
        dos.flush();
      }
      byte[] buf = new byte[1024];
      int len = dis.read(buf);
      while (len != -1) {
        log.debug("from server: " + new String(buf,0,len));
        len = dis.read(buf);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
