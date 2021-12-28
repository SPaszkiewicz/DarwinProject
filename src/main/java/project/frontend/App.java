package project.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;


public class App  extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setMaxHeight(520);
        primaryStage.setMinHeight(520);
        primaryStage.setMinWidth(370);
        primaryStage.setMaxWidth(370);
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/icon.png")));
        primaryStage.setTitle("Game of Life");
        StartScene startScene = new StartScene(primaryStage, this);
        primaryStage.setScene(startScene.getMainScene());
        primaryStage.show();
    }

    public void gameScene(Stage stage, Options options, boolean left, boolean right)
    {
        GameScene gameLeft = new GameScene(options, false, left);
        GameScene gameRight = new GameScene(options, true, right);
        stage.setOnCloseRequest(action ->
        {
            gameLeft.closeThreads();
            gameRight.closeThreads();
        });
        stage.setMaxHeight(900);
        stage.setMinHeight(900);
        stage.setMinWidth(1820);
        stage.setMaxWidth(1820);
        BorderPane container = new BorderPane();
        HBox center = new HBox(gameLeft.getContainer(), gameRight.getContainer());
        center.setBackground(new Background(new BackgroundFill(Color.web("FFE6BC"), CornerRadii.EMPTY, Insets.EMPTY)));
        container.setBackground(new Background(new BackgroundFill(Color.web("FFE6BC"), CornerRadii.EMPTY, Insets.EMPTY)));
        container.setCenter(center);
        Scene scene = new Scene(container, 1000, 900);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
