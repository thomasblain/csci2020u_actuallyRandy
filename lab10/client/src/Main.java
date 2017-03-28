import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Socket socket = new Socket("127.0.0.1", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        primaryStage.setTitle("SimpleBBS Client v1.0");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label messageLabel = new Label("Message:");
        grid.add(messageLabel, 0, 1);

        TextField messageField = new TextField();
        grid.add(messageField, 1, 1);

        Button sendButton = new Button("Send");
        grid.add(sendButton, 0, 2);
        sendButton.setOnAction(event -> {

            String message = userTextField.getText() + ": " + messageField.getText();
            System.out.println("Send: " +  message);

            out.println(message);
            out.flush();

        });

        Button exitButton = new Button("Exit");
        grid.add(exitButton, 0, 3);
        exitButton.setOnAction(event -> {
            System.exit(0);
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}