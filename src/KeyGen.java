//Librerías utilizadas:

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.io.FileOutputStream;
import java.security.*;

/*
Hecho por: José Raymundo Baca Hernández
Algoritmos y Sistemas de Cifrado - Criptografía
Instituto Tecnológico de Chihuahua ll
 */
public class KeyGen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear botón para generar claves RSA
        Button generateButton = new Button("Generar Claves RSA");
        generateButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");

        // Centrar el botón
        StackPane.setAlignment(generateButton, Pos.CENTER);

        // Evento del botón
        generateButton.setOnAction(e -> generateRSAKeys());

        // Crear diseño de la ventana
        StackPane root = new StackPane();
        root.getChildren().add(generateButton);
        root.setStyle("-fx-background-color: #333333;");

        // Crear escena
        Scene scene = new Scene(root, 250, 100, Color.BLACK);

        // Configurar escenario
        primaryStage.setTitle("Generador RSA");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Hacer que la ventana no sea redimensionable
        primaryStage.show();
    }

    private void generateRSAKeys() {
        try {
            // Generar par de claves RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            // Guardar clave privada en archivo
            byte[] encPriv = priv.getEncoded();
            FileOutputStream privfos = new FileOutputStream("RSAPrivateKey.key");
            privfos.write(encPriv);
            privfos.close();

            // Guardar clave pública en archivo
            byte[] encPub = pub.getEncoded();
            FileOutputStream pubfos = new FileOutputStream("RSAPublicKey.key");
            pubfos.write(encPub);
            pubfos.close();

            System.out.println("Códigos generados satisfactoriamente en la ruta del programa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
