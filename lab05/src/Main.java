import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    private TableView<StudentRecord> table;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("UI Demo 1");

        BorderPane layout = new BorderPane();

        //Menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New", imageFile("images/new.png"));
        newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        fileMenu.getItems().add(newMenuItem);

        MenuItem exitMenuItem = new MenuItem("Exit", imageFile("images/exit.png"));
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        menuBar.getMenus().add(fileMenu);
        layout.setTop(menuBar);

        //Spreadsheet
        table = new TableView<>();

        table.setItems(DataSource.getAllStudents());

        TableColumn<StudentRecord, Integer> sidColumn = new TableColumn<>("Student ID");
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        sidColumn.setMinWidth(100);

        TableColumn<StudentRecord, Double> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midtermColumn.setMinWidth(100);

        TableColumn<StudentRecord, Double> assignemntColumn = new TableColumn<>("Assignments");
        assignemntColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        assignemntColumn.setMinWidth(100);

        TableColumn<StudentRecord, Double> finalExamColumn = new TableColumn<>("Final Exam");
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalExamColumn.setMinWidth(100);

        TableColumn<StudentRecord, Double> finalGradeColumn = new TableColumn<>("Final Grade");
        finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        finalGradeColumn.setMinWidth(100);

        TableColumn<StudentRecord, Character> letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        letterGradeColumn.setMinWidth(100);

        table.getColumns().add(sidColumn);
        table.getColumns().add(midtermColumn);
        table.getColumns().add(assignemntColumn);
        table.getColumns().add(finalExamColumn);
        table.getColumns().add(finalGradeColumn);
        table.getColumns().add(letterGradeColumn);

        layout.setCenter(table);



        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView imageFile(String path){
        return new ImageView(new Image("file:"+path));
    }
    public static void main(String[] args){
        launch(args);
    }
}
