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

2019年1月9日23:19:03
对于使用AsynchronousSocketChannel,client端和server端信息交互时可以设置结尾标识，以防止server端读完数据后顺畅的切换写操作来