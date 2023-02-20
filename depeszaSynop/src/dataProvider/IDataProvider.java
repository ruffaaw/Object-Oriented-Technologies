package dataProvider;

import dataCollection.DataCollection;
import dataCollection.IDataCollection;

public interface IDataProvider {

    void provide(String input, IDataCollection output);

}
