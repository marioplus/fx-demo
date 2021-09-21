package net.marioplus.demo.fx.fxdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.marioplus.demo.fx.fxdemo.common.log.TextAreaOutStreamAppender;
import net.marioplus.demo.fx.fxdemo.common.log.TextAreaOutputStream;
import net.marioplus.demo.fx.fxdemo.controller.HelloController;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author marioplus
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(this::setTextArea);
    }

    private void setTextArea() {
        OutputStream os = new TextAreaOutputStream(HelloController.INSTANCE.taLog);
        TextAreaOutStreamAppender.setStaticOutputStream(os);
    }
}
