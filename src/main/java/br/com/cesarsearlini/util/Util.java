/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
public class Util {

    public static File geraArquivo(String conteudo, String diretorioNomeArquivoExtencao) {
        try {
            File file = new File(diretorioNomeArquivoExtencao);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(conteudo.getBytes());
            fos.close();
            return file;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static File copyFile(File arquivo, String diretorioNomeArquivoExtencao) {
        try {
            File arquivoCopia = new File(diretorioNomeArquivoExtencao);
            Files.copy(arquivo.toPath(), arquivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return arquivoCopia;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    public static boolean verificaInstanciaRodando() {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress("127.0.0.1", 12790));
            AddShutdownHook shutDownHook = new AddShutdownHook();
            shutDownHook.attachShutDownHookSocket(channel);
        } catch (IOException e) {
            return true;
        }
        return false;
    }

}
