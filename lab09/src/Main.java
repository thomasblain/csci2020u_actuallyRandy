import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) {
        stage.setTitle("Stocks");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Stock Monitoring");

        lineChart.setCreateSymbols(false);

        ObservableList<Float> GOOGStockPrices = Stocks.downloadStockPrices("GOOG", "GOOG.csv");
        ObservableList<Float> AAPLStockPrices = Stocks.downloadStockPrices("AAPL", "AAPL.csv");

        XYChart.Series GOOGData = plotLine(GOOGStockPrices);
        GOOGData.setName("GOOG");
        XYChart.Series AAPLData = plotLine(AAPLStockPrices);
        AAPLData.setName("AAPL");

        Scene scene = new Scene(lineChart,1366,750);
        lineChart.getData().addAll(GOOGData, AAPLData);

        stage.setScene(scene);
        stage.show();
    }

    public static XYChart.Series plotLine(ObservableList<Float> data) {
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < data.size(); i++) {
            series.getData().add(new XYChart.Data(i, data.get(i)));
        }
        return series;
    }

    public static void main(String[] args) { launch(args); }
}
