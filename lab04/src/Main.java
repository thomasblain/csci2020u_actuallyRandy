import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab04");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label password = new Label("Password:");
        grid.add(password, 0, 1);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        Label fullName = new Label("Full Name:");
        grid.add(fullName, 0, 2);

        TextField fullNameTextField = new TextField();
        grid.add(fullNameTextField, 1, 2);

        Label email = new Label("E-mail:");
        grid.add(email, 0, 3);

        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 3);

        Label phoneNum = new Label("Phone #:");
        grid.add(phoneNum, 0, 4);

        TextField phoneNumTextField = new TextField();
        grid.add(phoneNumTextField, 1, 4);

        Label date = new Label("Date of birth:");
        grid.add(date, 0, 5);

        DatePicker dateValue = new DatePicker();
        grid.add(dateValue, 1, 5);

        Button register = new Button("Register");
        grid.add(register, 1, 6);
        register.setOnAction(event -> {
            System.out.println(userTextField.getText());
            System.out.println(passwordField.getText());
            System.out.println(fullNameTextField.getText());
            System.out.println(emailTextField.getText());
            System.out.println(phoneNumTextField.getText().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
            System.out.println(dateValue.getValue());
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}