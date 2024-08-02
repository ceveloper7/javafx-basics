package com.ceva.jfx.ch02;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class A_MyShapes extends Application {
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

            // 1.2 Agregamos un efecto de sombra al Ellipse
            ellipse.setEffect(new DropShadow(30,10,10, Color.GRAY));

//      ellipse.setFill(Color.rgb(173, 216, 230, .5)); // relleno azul claro con .5 opacidad

        Text text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));
            // 1.3 Agregamos un reflejo al texto
            Reflection r = new Reflection();
            r.setFraction(.8);
            r.setTopOffset(1.0);
            text.setEffect(r);

        // 2. container StackPane
        StackPane container = new StackPane();
        container.getChildren().addAll(ellipse, text);
//        container.setAlignment(text, Pos.BOTTOM_CENTER); // Alineamos unicamente el nodo text

        // 3. Add contendor a la escena
        Scene scene = new Scene(container, 350, 230,Color.LIGHTYELLOW);

        // 4. Add Escena al Escenario
        stage.setTitle("My Shapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
