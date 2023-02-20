import org.xml.sax.SAXException;
import collections.DataCollection;
import collections.IDataCollection;
import collections.providers.IStringCurrencyCollectionProvider;
import collections.providers.XMLCurrencyCollectionProvider;
import data.IRemoteDataProvider;
import data.RemoteDataProvider;
import exchange.Exchange;
import exchange.IExchange;
import view.IView;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    static IRemoteDataProvider provider;
    static IDataCollection LastA;
    static IStringCurrencyCollectionProvider xmlProvider;
    static IExchange exchange;
    static IView view;
    public static void main(String[] args) {
        provider = new RemoteDataProvider();
        xmlProvider = new XMLCurrencyCollectionProvider();

        LastA = new DataCollection();
        exchange = new Exchange();
        view = new View();
        try {
            String result = provider.acquireRemoteData("https://www.nbp.pl/kursy/xml/LastA.xml");
            xmlProvider.provide(result,LastA);

            view.setDataCollection(LastA);
            view.setExchange(exchange);
            view.menu();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}
