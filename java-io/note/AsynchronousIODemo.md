# Java AIO

[*copy form stackoverflow : How should I use AsynchronousServerSocketChannel for accepting connections?*](https://stackoverflow.com/questions/8940747/how-should-i-use-asynchronousserversocketchannel-for-accepting-connections)
	
A simple (but ugly) way to prevent the thread from terminating is simply to loop until the thread is interrupted.

```java
// yes, sleep() is evil, but sometimes I don't care
while (true) {
    Thread.sleep(1000);
}
```

A cleaner way is to use AsynchronousChannelGroup. For instance:
```java
AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors
            .newSingleThreadExecutor());
AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(
            new InetSocketAddress(port));

// (insert server.accept() logic here)

// wait until group.shutdown()/shutdownNow(), or the thread is interrupted:
group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
```

You can tune how threads are handled, see the AsynchronousChannelGroup API docs for more information.

-------------

<!-- 2019年1月9日23:19:03-->
对于使用AsynchronousSocketChannel,client端和server端信息交互时可以设置结尾标识，以防止server端读完数据后顺畅的切换写操作来

* __异步__： 执行一个任务的同时可以执行另一个任务
* **非阻塞**：执行一个任务立马返回结果



**谨慎使用Asynchronous*SocketChannel,避免`WritePendingException ` `ReadPendingException`**

```
WritePendingException - If the channel does not allow more than one write to be outstandingand a previous write has not completed
ReadPendingException - If a read operation is already in progress on this channel
```

AsynchronousSocketChannel 与  SocketChannel 的区别在于

AsynchronousSocketChannel | SocketChannel 
----|----
异步 可以非阻塞也可以阻塞|非阻塞