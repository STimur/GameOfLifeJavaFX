package ui;

import core.World;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    World world = new World("......\n.**...\n.**...\n...**.\n...**.\n......");
    Circle[] circles;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String str = world.toString().replace("\n","");
        int n = str.length();
        circles = new Circle[n];
        int g = (int) Math.sqrt(n);
        for (int i = 0; i < n; i++) {
            circles[i] = new Circle();
            circles[i].setCenterX(25 + (i % g) * 50);
            circles[i].setCenterY(25 + (i / g) * 50);
            circles[i].setRadius(20);
            if (str.charAt(i) == '.')
                circles[i].setFill(Color.BLACK);
            else
                circles[i].setFill(Color.WHITE);
        }
        Group root = new Group(circles);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> doSomething()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        int width = (int) (Math.sqrt(n) * 50);
        int height = (int) (Math.sqrt(n) * 50);
        Scene scene = new Scene(root, width, height);
        scene.setFill(Color.MEDIUMPURPLE);

        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void doSomething() {
        String str = world.nextGeneration().toString().replace("\n","");
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '.')
                circles[i].setFill(Color.BLACK);
            else
                circles[i].setFill(Color.WHITE);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
