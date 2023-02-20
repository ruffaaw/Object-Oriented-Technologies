import dataCollection.DataCollection;
import dataCollection.IDataCollection;
import dataProvider.DataProvider;
import dataProvider.IDataProvider;
import readData.IReadData;
import readData.ReadData;
import view.View;

import java.io.IOException;

public class Main {
    static IReadData readData;
    static IDataCollection dataCollection;
    static IDataProvider dataProvider;
    static View view;

    public static void main(String[] args) throws IOException {
        readData = new ReadData();
        dataCollection = new DataCollection();
        dataProvider = new DataProvider();
        view = new View();

        String result = readData.acquireRemoteData("https://www.ogimet.com/cgi-bin/getsynop?begin=202207270600&end=202207271200&state=Pol");
        dataProvider.provide(result, dataCollection);
        view.setDataCollection(dataCollection);
        view.menu();
    }
}
