/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
public class AlertPadrao {

    public static void success(Stage stage, String titulo, String mensagem) {
        Alert alertCupom = new Alert(Alert.AlertType.INFORMATION);
        alertCupom.setHeaderText(titulo);
        alertCupom.setContentText(mensagem);
        alertCupom.initOwner(stage);
        alertCupom.showAndWait();
    }

    public static void warning(Stage stage, String titulo, String mensagem) {
        Alert alertCupom = new Alert(Alert.AlertType.WARNING);
        alertCupom.setHeaderText(titulo);
        alertCupom.setContentText(mensagem);
        alertCupom.initOwner(stage);
        alertCupom.showAndWait();
    }

}
