package cn.bingo.dig.java.io.bio.bio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Acceptor {
  private ExecutorService executorService = Executors.newFixedThreadPool(10);
  private Queue<String> cache = new LinkedList<String>();
  boolean running = true;

  public void accept(Socket socket) {
    executorService.execute(clientHandler(socket));
  }

  private Runnable clientHandler(Socket socket) {
    return () -> {
      log.debug("the clientHandler[id={}] is start", Thread.currentThread().getId());
      executorService.execute(socketRead(socket));
      executorService.execute(socketwrite(socket));
    };
  }

  private Runnable socketRead(Socket socket) {
    return () -> {
      log.debug("the socketRead[id={}] is start", Thread.currentThread().getId());
      try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
        byte[] buf = new byte[1024 * 2];
        int len = -1;
        String line = null;
        synchronized (cache) {
          while (running) {
            len = dis.read(buf);
            if (len != -1 && cache.size() <= 100) {
              line = new String(buf, 0, len);
              log.debug("string read from client[threadId:{}]: {}", Thread.currentThread().getId(),
                  line);
              cache.add(line);
            }
            if (cache.size() > 0) {
              cache.notifyAll();
              cache.wait();
            }
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    };
  }

  private Runnable socketwrite(Socket socket) {
    return () -> {
      log.debug("the socketwrite[id={}] is start", Thread.currentThread().getId());
      try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
        String line = null;
        while (running) {
          synchronized (cache) {
            line = cache.poll();
            if (line == null) {
              log.debug("cache is empty,the current thread[id={}] is release the lock",
                  Thread.currentThread().getId());
              cache.notifyAll();
              cache.wait();
            } else {
              log.debug("string write to client[threadId:{}]: {}", Thread.currentThread().getId(),
                  line);
              dos.write(line.getBytes());
              dos.flush();
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }

  public void shutdown() {
    running = false;
  }
}
