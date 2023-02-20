package collections.providers;

import org.xml.sax.SAXException;
import collections.IDataCollection;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IStringCurrencyCollectionProvider {
    public void provide(String input, IDataCollection output) throws IOException, SAXException, ParserConfigurationException;
}
