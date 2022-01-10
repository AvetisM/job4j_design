package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    boolean close = false;
                    String msgHello = "";
                    String msgOther = "";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Exit")) {
                            close = true;
                        }
                        if (str.contains("Hello")) {
                            msgHello = "Hello";
                        }
                        if (!str.contains("Hello") && !str.contains("Exit")) {
                            msgOther = "What";
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (!msgHello.isEmpty()) {
                        out.write(msgHello.getBytes());
                    } else {
                        out.write(msgOther.getBytes());
                    }
                    out.flush();
                    if (close) {
                        server.close();
                    }
                }
            }
        }
    }
}
