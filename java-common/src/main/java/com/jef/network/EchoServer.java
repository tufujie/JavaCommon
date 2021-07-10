package com.jef.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8189)) {
            Socket incoming = serverSocket.accept();
            InputStream inputStream = incoming.getInputStream();
            OutputStream outputStream = incoming.getOutputStream();
            Scanner in = new Scanner(inputStream);
            PrintWriter out = new PrintWriter(outputStream, true);
            out.print("Hello! Enter BYE to Exist.");
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.print("Echo:" + line);
                if(line.equals("BYE"))
                    done = true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
