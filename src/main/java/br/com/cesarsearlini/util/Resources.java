/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarsearlini.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 *
 * @author Cesar Searlini <cesar.searlini@gmail.com>
 */
public class Resources {

    public static final String FXMLS_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/ui/fxml/" : "/br/com/cesarsearlini/ui/fxml/";
    public static final String IMAGES_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/ui/images/" : "/br/com/cesarsearlini/ui/images/";
    public static final String JASPER_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/ui/jasper/" : "/br/com/cesarsearlini/ui/jasper/";
    public static final String STYLES_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/ui/styles/" : "/br/com/cesarsearlini/ui/styles/";
    public static final String HTMLS_TEMPLATE_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/html/templates/" : "/br/com/cesarsearlini/html/templates/";
    public static final String SOUND_PATH = isRunningFromJarFile() ? "/BOOT-INF/classes/br/com/cesarsearlini/sound/" : "/br/com/cesarsearlini/sound/";

    private static final Image icon = Resources.image("sistema.icone.png");

    /**
     *
     * @param fxmlName nome do arquivo ex: tela.fxml
     * @return
     */
    public static URL fxml(String fxmlName) {
        return Resources.class.getResource(FXMLS_PATH + fxmlName);
    }

    /**
     *
     * @param cssName nome do arquivo ex: tela.css
     * @return
     */
    public static String style(String cssName) {
        return Resources.class.getResource(STYLES_PATH + cssName).toExternalForm();
    }

    public static Image image(String imageName) {
        return new Image(Resources.class.getResourceAsStream(IMAGES_PATH + imageName));
    }

    public static InputStream jasper(String jasperName) {
        return Resources.class.getResourceAsStream(JASPER_PATH + jasperName);
    }

    public static String jasperPATH(String jasperName) {
        return Resources.class.getResource(JASPER_PATH + jasperName).toExternalForm();
    }

    public static String htmlTemplate(String templateName) {
        return new Scanner(Resources.class.getResourceAsStream(HTMLS_TEMPLATE_PATH + templateName), "UTF-8").useDelimiter("\\A").next();
    }

    public static String sound(String soundName) {
        return Resources.class.getResource(SOUND_PATH + soundName).toExternalForm();
    }

    public static Image getIcons() {
        return icon;
    }

    public static BufferedImage getTrayIcon() {
        return SwingFXUtils.fromFXImage(icon, null);
    }

    public static boolean isRunningFromJarFile() {
        return Resources.class.getResource("Resources.class").toString().startsWith("jar");
    }

}
