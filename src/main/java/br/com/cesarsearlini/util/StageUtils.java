/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
public class StageUtils {

    /**
     *
     * OBS: Para que esse metodo funcione corretamente é nescessario chama-lo
     * após o Stage.show() ou pre-definir a altura e largura do stage
     *
     * @param stage Tela a ser centrada no monitor.
     */
    public static void centerStage(Stage stage) {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setX(bounds.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(bounds.getHeight() / 2 - stage.getHeight() / 2);
    }

}
