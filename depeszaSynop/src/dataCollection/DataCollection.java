package dataCollection;

import synop.ISynop;

import java.util.ArrayList;
import java.util.List;

public class DataCollection implements IDataCollection {

    List<ISynop> s_list;

    public DataCollection() {
        s_list = new ArrayList<>();
    }

    public List<ISynop> getList() {
        return s_list;
    }

}
