/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.ui.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

/**
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
@Component
public class PrincipalController extends AbstractController implements Initializable {

    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    @FXML
    private TextField valor100;

    @FXML
    private TextField valor50;

    @FXML
    private TextField valor20;

    @FXML
    private TextField valor10;

    @FXML
    private TextField valor5;

    @FXML
    private TextField valor2;

    @FXML
    private TextField valor1;

    @FXML
    private TextField valor050;

    @FXML
    private TextField valor025;

    @FXML
    private TextField valor010;

    @FXML
    private TextField valor005;

    @FXML
    private TextField valor001;

    @FXML
    private Label valorTotal;

    @FXML
    private TextField qtd100;

    @FXML
    private TextField qtd50;

    @FXML
    private TextField qtd20;

    @FXML
    private TextField qtd10;

    @FXML
    private TextField qtd5;

    @FXML
    private TextField qtd2;

    @FXML
    private TextField qtd1;

    @FXML
    private TextField qtd050;

    @FXML
    private TextField qtd025;

    @FXML
    private TextField qtd010;

    @FXML
    private TextField qtd005;

    @FXML
    private TextField qtd001;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadConfig();
    }

    public void loadConfig() {
        mascaraCallFuncMultiplicar(this.qtd100, this.valor100, 100d);
        mascaraCallFuncMultiplicar(this.qtd50, this.valor50, 50d);
        mascaraCallFuncMultiplicar(this.qtd20, this.valor20, 20d);
        mascaraCallFuncMultiplicar(this.qtd10, this.valor10, 10d);
        mascaraCallFuncMultiplicar(this.qtd5, this.valor5, 5d);
        mascaraCallFuncMultiplicar(this.qtd2, this.valor2, 2d);
        mascaraCallFuncMultiplicar(this.qtd1, this.valor1, 1d);
        mascaraCallFuncMultiplicar(this.qtd050, this.valor050, 0.50d);
        mascaraCallFuncMultiplicar(this.qtd025, this.valor025, 0.25d);
        mascaraCallFuncMultiplicar(this.qtd010, this.valor010, 0.10d);
        mascaraCallFuncMultiplicar(this.qtd005, this.valor005, 0.05d);
        mascaraCallFuncMultiplicar(this.qtd001, this.valor001, 0.01d);
    }

    @FXML
    public void limpar() {
        this.qtd100.setText("");
        this.qtd50.setText("");
        this.qtd20.setText("");
        this.qtd10.setText("");
        this.qtd5.setText("");
        this.qtd2.setText("");
        this.qtd1.setText("");
        this.qtd050.setText("");
        this.qtd025.setText("");
        this.qtd010.setText("");
        this.qtd005.setText("");
        this.qtd001.setText("");
    }

    public void mascaraCallFuncMultiplicar(final TextField textFieldQtd, final TextField textFieldResult, final Double multiplicador) {
        textFieldQtd.setAlignment(Pos.CENTER);
        textFieldQtd.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = textFieldQtd.getText();
                value = value.replaceAll("[^0-9]", "");
                textFieldQtd.setText(value);
                positionCaret(textFieldQtd);

                textFieldQtd.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17) {
                            textFieldQtd.setText(oldValue);
                        }
                    }
                });
            }
        });

        textFieldQtd.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                //System.out.println("");
            }
        });

        textFieldQtd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                Integer quantidade = 0;
                if (!textFieldQtd.getText().isEmpty()) {
                    quantidade = Integer.valueOf(textFieldQtd.getText());
                }
                BigDecimal valor = new BigDecimal(Double.valueOf(quantidade * multiplicador));
                textFieldResult.setText(nf.format(valor).replace("R$ ", ""));
                somaTotal();
            }
        });

    }

    private void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    private void somaTotal() {
        Double v100 = Double.valueOf(this.valor100.getText().replace(".", "").replace(",", "."));
        Double v50 = Double.valueOf(this.valor50.getText().replace(".", "").replace(",", "."));
        Double v20 = Double.valueOf(this.valor20.getText().replace(".", "").replace(",", "."));
        Double v10 = Double.valueOf(this.valor10.getText().replace(".", "").replace(",", "."));
        Double v5 = Double.valueOf(this.valor5.getText().replace(".", "").replace(",", "."));
        Double v2 = Double.valueOf(this.valor2.getText().replace(".", "").replace(",", "."));
        Double v1 = Double.valueOf(this.valor1.getText().replace(".", "").replace(",", "."));
        Double v050 = Double.valueOf(this.valor050.getText().replace(".", "").replace(",", "."));
        Double v025 = Double.valueOf(this.valor025.getText().replace(".", "").replace(",", "."));
        Double v010 = Double.valueOf(this.valor010.getText().replace(".", "").replace(",", "."));
        Double v005 = Double.valueOf(this.valor005.getText().replace(".", "").replace(",", "."));
        Double v001 = Double.valueOf(this.valor001.getText().replace(".", "").replace(",", "."));

        BigDecimal valor = new BigDecimal(v100 + v50 + v20 + v10 + v5 + v2 + v1 + v050 + v025 + v010 + v005 + v001);
        String formatado = nf.format(valor);
        this.valorTotal.setText(formatado);
    }

}
