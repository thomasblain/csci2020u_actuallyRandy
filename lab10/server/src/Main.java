import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    static TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SimpleBBS Server v1.0");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);

        textArea = new TextArea();
        grid.add(textArea, 0, 0, 1, 2);


        Button exitButton = new Button("Exit");
        grid.add(exitButton, 0, 2);
        exitButton.setOnAction(event -> {
            System.exit(0);
        });
        primaryStage.show();

        HttpServer server = new HttpServer(8080);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    public static void displayMessage(String entry){
        textArea.appendText(entry);
    }

    public static void main(String[] args) {
        launch(args);
    }
}