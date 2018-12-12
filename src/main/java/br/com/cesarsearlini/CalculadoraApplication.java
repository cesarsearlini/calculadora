/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini;

import br.com.cesarsearlini.ui.controller.PrincipalController;
import br.com.cesarsearlini.util.AlertPadrao;
import br.com.cesarsearlini.util.Resources;
import br.com.cesarsearlini.util.SystemConstants;
import br.com.cesarsearlini.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
@SpringBootApplication
public class CalculadoraApplication extends Application {

    private static ConfigurableApplicationContext springContext;
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 863;
    private static final int SPLASH_HEIGHT = 610;

    public static Stage STAGE_PRINCIPAL;
    public static PrincipalController CONTROLLER_PRINCIPAL;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(springContext::getBean);
        return loader;
    }

    public static FXMLLoader getFXMLLoader(URL fxml) {
        FXMLLoader loader = getFXMLLoader();
        loader.setLocation(fxml);
        return loader;
    }

    @Override
    public void init() {
        ImageView imagem = new ImageView(Resources.image("bolaDeNeve.jpg"));
        imagem.setStyle("-fx-background-color:transparent");
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(imagem, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle("-fx-border-color: linear-gradient(to bottom, black, derive(black, 50%))");
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task() {
            @Override
            protected ObservableList<String> call() throws Exception {
                updateMessage("Carregando Spring...");
                springContext = SpringApplication.run(CalculadoraApplication.class);
                updateMessage("Sistema carregado.");
                return null;
            }
        };

        showSplash(initStage, friendTask);
        if (Util.verificaInstanciaRodando()) {
            AlertPadrao.warning(initStage, "Verificação calculadora", "Já Existe uma instância sendo executada. Não é possivel abrir outra.");
            System.exit(1);
        }
        new Thread(friendTask).start();
        Platform.setImplicitExit(false);
    }

    private void showSplash(final Stage initStage, Task task) {
        initStage.getIcons().add(SystemConstants.ICONE_SISTEMA);
        initStage.setTitle(SystemConstants.TITULO_SISTEMA);

        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    loadProgress.progressProperty().unbind();
                    loadProgress.setProgress(1);
                    initStage.toFront();
                    FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                    fadeSplash.setFromValue(1.0);
                    fadeSplash.setToValue(0.0);
                    fadeSplash.setOnFinished((ActionEvent actionEvent) -> {
                        initStage.hide();
                        loadPrincipal();
                    });
                    fadeSplash.play();
                } else if (newState == Worker.State.FAILED) {
                    loadProgress.progressProperty().unbind();
                    loadProgress.setProgress(0);
                    progressText.textProperty().unbind();
                    progressText.setText("");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erro ao carregar o sistema.");
                    alert.setContentText("Ocorreu um erro crítico ao carregar o sistema. Entre em contato com o suporte para maiores informações.");
                    alert.initOwner(initStage);
                    alert.showAndWait();
                    Platform.exit();
                    System.exit(0);
                }
            }
        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.DECORATED);
        initStage.getIcons().add(SystemConstants.ICONE_SISTEMA);
        splashScene.setFill(Color.TRANSPARENT);
        initStage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    private static void loadPrincipal() {
        try {
            STAGE_PRINCIPAL = new Stage(StageStyle.DECORATED);
            FXMLLoader loader = CalculadoraApplication.getFXMLLoader(Resources.fxml("Principal.fxml"));
            CONTROLLER_PRINCIPAL = loader.getController();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.getStylesheets().add(Resources.style("style.css"));

            STAGE_PRINCIPAL.setOnCloseRequest(e -> {
                System.exit(0);
            });
            STAGE_PRINCIPAL.setScene(scene);
            STAGE_PRINCIPAL.setMaximized(false);
            STAGE_PRINCIPAL.getIcons().add(SystemConstants.ICONE_SISTEMA);
            STAGE_PRINCIPAL.setTitle(SystemConstants.TITULO_SISTEMA);
            STAGE_PRINCIPAL.show();
        } catch (IOException ex) {
            Logger.getLogger(CalculadoraApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
