package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Main extends Application {
    @Override public void start(Stage stage) throws Exception {
        stage.setTitle("Pie chart");

        String line = "";
        String splitBy = ",";
        BufferedReader reader = null;
        TreeMap<String, Double> data = new TreeMap();
        File file = new File("weather-warnings.csv");
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String row[] = line.split(splitBy);
                String weather = row[5];
                if (!data.containsKey(weather)) {
                    data.put(weather,1.0);
                } else {
                    data.put(weather,data.get(weather)+1.0);
                }
            }

        } catch (IOException er) {
            er.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String[] keys = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO" };

        for (String key : keys) {
            PieChart.Data d = new PieChart.Data(key, data.get(key));
            pieChartData.add(d);
        }

        final PieChart chart = new PieChart();

        chart.getData().addAll(pieChartData);
        chart.setLegendSide(Side.LEFT);
        chart.setLabelsVisible(false);

        Scene scene = new Scene(chart,500,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}