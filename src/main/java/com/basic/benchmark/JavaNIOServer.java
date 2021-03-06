package com.basic.benchmark;

import com.basic.util.BenchmarkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * locate com.basic.benchmark
 * Created by 79875 on 2017/11/5.
 * JavaNIO测试 JavaNIOServer
 * java -cp nioAction-1.0-SNAPSHOT.jar com.basic.benchmark.JavaNIOServer
 */
public class JavaNIOServer {
    private static Logger logger= LoggerFactory.getLogger(JavaNIOServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get(BenchmarkConstants.filePath), StandardOpenOption.READ, StandardOpenOption.CREATE);
        ssChannel.bind(new InetSocketAddress(9898));

        SocketChannel socketChannel = ssChannel.accept();

        long startTimeMills = System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(1024*1024);
        while(outChannel.read(buf) != -1){
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        socketChannel.shutdownOutput();

        long endTimeMills = System.currentTimeMillis();
        logger.info("delayTime: "+(endTimeMills-startTimeMills));
        socketChannel.close();
        outChannel.close();
        ssChannel.close();
    }
}
