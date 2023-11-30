import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PrimeServer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label serverStatusLabel = new Label("");
        VBox vbox = new VBox(serverStatusLabel);
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.show();

        startServer(serverStatusLabel);
    }

    private void startServer(Label serverStatusLabel) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                updateServerStatus(serverStatusLabel, "Server started at " + getCurrentTime());

                while (true) {
                    Socket socket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    int number = dis.readInt();
                    boolean isPrime = checkPrime(number);

                    Platform.runLater(() -> updateReceivedNumber(serverStatusLabel, "\nNumber received from client: " + number));

                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeBoolean(isPrime);

                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean checkPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void updateServerStatus(Label serverStatusLabel, String status) {
        Platform.runLater(() -> serverStatusLabel.setText(status));
    }

    private void updateReceivedNumber(Label serverStatusLabel, String message) {
        Platform.runLater(() -> serverStatusLabel.setText(serverStatusLabel.getText() + message));
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
