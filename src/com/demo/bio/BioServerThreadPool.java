package com.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServerThreadPool {
    public static void main(String[] args) throws IOException {

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(8098);
        System.out.println("服务器启动！");
        while (true) {
            //获取一个套接字（阻塞）
            final Socket socket = server.accept();
            System.out.println("来个一个新客户端！");
            //创建一个线程池处理多消息
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //业务处理
                    handler(socket);
                }
            });

        }
    }
    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                //读取数据（阻塞）
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("socket关闭");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
