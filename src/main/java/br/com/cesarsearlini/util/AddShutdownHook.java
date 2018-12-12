/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.util;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 *
 * @author Flavio Tiba
 */
public class AddShutdownHook {
    
    public void attachShutDownHookSocket(ServerSocketChannel serverSocket) {
        final ServerSocketChannel server = serverSocket;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    server.close();
                } catch (IOException ex) {

                }
            }
        });
    }
    
}
