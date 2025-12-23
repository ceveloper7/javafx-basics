package com.ceva.jfx.ch02;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.When;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
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

public class B_MyShapesProperties extends Application {
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

            // 1.4 creamos una manejador de evento click (event handler) y lo agregamos al nodo text
            text.setOnMouseClicked(mouseEvent -> {
                System.out.println(mouseEvent.getSource().getClass() + " hizo click");
            });

        Text text2 = new Text("");
        text2.setFont(new Font("Arial Bold", 18));

        /**
         * 2. Layout container StackPane -> StackPane apila sus nodos hijos de atras hacia adelante, segun el orden que se agregan.
         * Los Layout Container organizan los nodos dentro de una escena
         */
        StackPane container = new StackPane();
            // 2.1 Aplicamos Animacion: RotateTransition de 2.5 segundos
            RotateTransition rotate = new RotateTransition(Duration.millis(2500), container);
            rotate.setToAngle(360); // la animacion continua linealmente hasta el angulo 360
            rotate.setFromAngle(0); // la animacion inicia en el angulo 0
            rotate.setInterpolator(Interpolator.LINEAR);
// JavaFX Properties
// InvalidationListener
            //2.2 Adjuntamos un InvalidationListener a la propiedad getter statusProperty() de rotate para obtener el estado de la animacion.
            // como accedemos al valor de la propiedad status podemos omitir el observable con el metodo getStatus().
//            rotate.statusProperty().addListener(observable -> {
//                text2.setText("Animation status: " + rotate.getStatus());});
            // vieja forma sin lambda
//            rotate.statusProperty().addListener(new InvalidationListener() {
//                @Override
//                public void invalidated(Observable observable) {
//                    text2.setText("Animation status: " + ((ObservableObjectValue<Animation.Status>)observable).getValue());
//                }
//            });

// ChangeListener: Nos permite acceder al valor anterior asi como al nuevo valor de un Observable
            // 2.3 Nueva forma, usando Lambda: ChangeListener, accedemos al old value y el new value de un Observable
            rotate.statusProperty().addListener((observable, oldValue, newValue) -> {
                text2.setText("Old value: " + oldValue + ", New value: " + newValue);});
            // vieja forma sin lambda
//            rotate.statusProperty().addListener(new ChangeListener<Animation.Status>() {
//                @Override
//                public void changed(ObservableValue<? extends Animation.Status> observableValue, Animation.Status oldValue, Animation.Status newValue) {
//                    text2.setText("Old value: " + oldValue + ", New value: " + newValue);
//                }
//            });
// JavaFX Properties end

            // 2.5 adjuntamos un manejador de evento click. La animacion se dispara cuando el usuario hace click dentro del container
            container.setOnMouseClicked(mouseEvent -> {
                // la animacion se aplica a todos los elementos secundarios del StackPane
                // comprobamos el estado de la transicion.
                if(rotate.getStatus().equals(Animation.Status.RUNNING)){
                    // si la animacion esta en ejecucion, la pausamos
                    rotate.pause();
                }
                else{
                    rotate.play();
                }
            });
        // agregamos multiples nodos al layout container (container)
        container.getChildren().addAll(ellipse, text, text2);

        // La alineacion por defecto es CENTER, pero podemos indicar una alineacion diferente
        container.setAlignment(text2, Pos.BOTTOM_CENTER);

        // 3. Binding properties
            // 3.1 Binding unidireccional:text2 gira junto a container cuando se inicia una transicion de rotacion.
//            text2.rotateProperty().bind(container.rotateProperty());

            // 3.2 Binding bidireccional: text2 muestra inicialmente el valor de text, cuando inicia animacion text toma el valor de text2
//            text2.textProperty().bindBidirectional(text.textProperty());

        // 4. Fluent Api - Binding Api
            // actualizamos el color de text2 dependiendo de si la transicion esta en ejecucion o no
            text2.strokeProperty().bind(new When(rotate.statusProperty()
                    .isEqualTo(Animation.Status.RUNNING))
                    .then(Color.GREEN).otherwise(Color.RED)
            );

        // 5. Add contendor a la escena
        Scene scene = new Scene(container, 350, 230,Color.LIGHTYELLOW);

        // 6. Add Escena al Escenario
        stage.setTitle("My Shapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
