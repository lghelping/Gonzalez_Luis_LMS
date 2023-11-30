import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PrimeClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter a Number to Evaluate:");
        TextField textField = new TextField();
        textField.setStyle("-fx-border-color: black; -fx-border-width: 1px;-fx-alignment: CENTER-RIGHT;");

        VBox resultContainer = new VBox();
        ScrollPane scrollPane = new ScrollPane(resultContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button sendButton = new Button("Send");
        Label resultLabel = new Label();

        sendButton.setOnAction(e -> {
            String host = "127.0.0.1";
            int port = 5000;

            try {
                Socket socket = new Socket(host, port);
                int number = Integer.parseInt(textField.getText());

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(number);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                boolean isPrime = dis.readBoolean();

                resultContainer.getChildren().add(new Label("The number is " + number));
                if (isPrime) {
                    resultContainer.getChildren().add(new Label("The response fromthe server is: The number is a prime number."));
                } else {
                    resultContainer.getChildren().add(new Label("The response fromthe server is: The number is NOT a prime number."));
                }

                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        HBox inputBox = new HBox();
        inputBox.getChildren().addAll(label, textField);
        inputBox.setStyle("-fx-border-color: green; -fx-border-width: 1px; -fx-padding: 5px;");

        VBox vbox = new VBox(inputBox, sendButton, scrollPane);
        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
