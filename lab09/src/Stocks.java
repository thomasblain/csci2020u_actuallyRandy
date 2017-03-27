import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Stocks {

    public static ObservableList<Float> downloadStockPrices(String ticker, String file) {
        ObservableList<Float> data = FXCollections.observableArrayList();
        try {
            String tickerURL = "http://ichart.finance.yahoo.com/table.csv?s=" + ticker +
                    "&a=01&b=01&c=2007&d=30&e=01&f=2007&g=d";
            URL url = new URL(tickerURL);
            System.out.println("Opening connection...");
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream((new File(file)));

            System.out.println("reading..");
            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            in.close();
            System.out.println("File downloaded.");

            File CSVfile = new File(file);

            String line = "";
            String splitBy = ",";
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(CSVfile));
                reader.readLine();
                while((line = reader.readLine()) != null) {
                    String row[] = line.split(splitBy);

                    float closingPrice = Float.parseFloat(row[4]);
                    data.add(closingPrice);
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException r) {
            r.printStackTrace();
        }
        return data;
    }
}
