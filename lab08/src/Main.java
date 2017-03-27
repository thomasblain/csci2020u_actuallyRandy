import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.*;

public class Main extends Application {

    private TableView<StudentRecord> table;
    String currentFile = "StudentRecord.csv";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CSVs and Stuff");

        BorderPane layout = new BorderPane();
        //Menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New", imageFile("images/new.png"));
        newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newMenuItem.setOnAction(e -> clear());
        fileMenu.getItems().add(newMenuItem);

        MenuItem openMenuItem = new MenuItem("Open", imageFile("images/open.png"));
        openMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openMenuItem.setOnAction(e -> open());
        fileMenu.getItems().add(openMenuItem);

        MenuItem saveMenuItem = new MenuItem("Save", imageFile("images/save.png"));
        saveMenuItem.setAccelerator(KeyCombination.keyCombination(("Ctrl+S")));
        saveMenuItem.setOnAction(e -> save());
        fileMenu.getItems().add(saveMenuItem);

        MenuItem saveAsMenuItem = new MenuItem("Save As", imageFile("images/saveas.png"));
        saveAsMenuItem.setAccelerator(KeyCombination.keyCombination(("Ctrl+Shift+S")));
        saveAsMenuItem.setOnAction(e -> saveAs());
        fileMenu.getItems().add(saveAsMenuItem);

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

    public void save() {
        File file = new File(currentFile);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            ObservableList<StudentRecord> m = table.getItems();

            for (StudentRecord s : m) {

                writer.append(s.getStudentID() + "," + s.getAssignments() + ","
                        + s.getMidterm() + "," + s.getFinalExam() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAs() {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showSaveDialog(fileStage);

        currentFile = selectedFile.getName();
        save();
    }

    public void clear() {
        for ( int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
        currentFile = "";
    }

    public void load(File selectedFile) {
        BufferedReader in = null;
        String line = "";
        String splitBy = ",";
        ObservableList<StudentRecord> data = FXCollections.observableArrayList();
        try {
            in = new BufferedReader(new FileReader(selectedFile));
            line = in.readLine();
            while(line != null) {
                String row[] = line.split(splitBy);
                System.out.println(line);

                String SID = row[0];
                double midterm = Double.parseDouble(row[1]);
                double assignments = Double.parseDouble(row[2]);
                double finalExam = Double.parseDouble(row[3]);
                data.add(new StudentRecord(SID,midterm, assignments, finalExam));
                line = in.readLine();

            }
            table.setItems(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void open() {
        clear();
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(fileStage);

        currentFile = selectedFile.getName();

        load(selectedFile);
    }

    public static void main(String[] args){
        launch(args);
    }
}
