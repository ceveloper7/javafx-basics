package com.ceva.jfx.ch02;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class G_MyShapesProperties extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // 1. creacion de nodos Ellipse y Text
        Ellipse ellipse = new Ellipse(110, 70);

            // 1.1 Apply Linear Gradient to Ellipse. Gradients necesitan dos o mas colores llamados Stops
            Stop[] stops = new Stop[]{
                    new Stop(0, Color.DODGERBLUE),
                    new Stop(0.5, Color.LIGHTBLUE),
                    new Stop(1, Color.LIGHTGREEN)
            };
            // Creamos un Gradient Lineal vertical startX=0, startY=0, endX=0, endY=1
            LinearGradient gradient = new LinearGradient(0,0,1,0, true, CycleMethod.NO_CYCLE, stops);
            // aplicamos el gradient al node ellipse
            ellipse.setFill(gradient);

            // 1.2 DropShadow: Agregamos un efecto de sombra al Ellipse
            ellipse.setEffect(new DropShadow(30,10,10, Color.GRAY));

//      ellipse.setFill(Color.rgb(173, 216, 230, .5)); // relleno azul claro con .5 opacidad

        Text text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));

        Text text2 = new Text("Animation Status: ");
        text2.setFont(new Font("Arial Bold", 18));

            // 1.3 Agregamos un reflejo al texto
            Reflection r = new Reflection();
            r.setFraction(.8);
            r.setTopOffset(1.0);
            text.setEffect(r);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);
        VBox vBox = new VBox(stackPane, text2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50.0);

        // Define RotateTransition for stackPane
        RotateTransition rotate = new RotateTransition(Duration.millis(2500), stackPane);
        rotate.setToAngle(360);
        rotate.setFromAngle(0);
        rotate.setInterpolator(Interpolator.LINEAR);

        // Invalidation Listener using lambda expression
        rotate.statusProperty().addListener(observable -> {
            text2.setText("Animation status: " +
                    ((ObservableObjectValue<Animation.Status>)observable).getValue());
            text2.setText("Animation status: " + rotate.getStatus());
        });

        // Change Listener using lambda expression
        rotate.statusProperty().addListener((observableValue, oldValue, newValue) -> {
            text2.setText("Was " + oldValue + ", Now " + newValue);
        });

        // Bind expression with When
        text2.strokeProperty().bind(new When(rotate.statusProperty()
                .isEqualTo(Animation.Status.RUNNING)) .then(Color.GREEN).otherwise(Color.RED));

        // configure mouse click handler
        stackPane.setOnMouseClicked(mouseEvent -> {
            if (rotate.getStatus().equals(Animation.Status.RUNNING)) {
                rotate.pause();
            } else {
                rotate.play();
            }
        });

        Scene scene = new Scene(vBox, 350, 350, Color.LIGHTYELLOW);
        //scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("MyShapesProperties");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
