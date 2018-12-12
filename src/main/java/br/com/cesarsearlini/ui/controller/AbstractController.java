/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.ui.controller;

import br.com.cesarsearlini.util.Resources;
import javafx.stage.Stage;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
public class AbstractController implements InterefaceController {

    Stage stage;

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setStage(Stage stage) {
        stage.getIcons().addAll(Resources.getIcons());
        this.stage = stage;
    }

    @Override
    public void ini() {
    }
}
