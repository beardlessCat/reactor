package com.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) throws IOException {
        //开启服务端Socket
        ServerSocket serverSocket = new ServerSocket(8098);
        while (true) {
            //探测新连接，【阻塞】
            Socket socket = serverSocket.accept();
            System.out.println("新连接接入");
            try {
                byte[] bytes = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                while(true){
                    //读取数据【阻塞】
                    int read = inputStream.read(bytes);
                    System.out.println("接收到新数据");
                    if(read != -1){
                        System.out.println(new String(bytes, 0, read));
                    }else{
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    System.out.println("socket关闭");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

