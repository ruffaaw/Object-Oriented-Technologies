package view;

import currency.ICurrency;
import collections.IDataCollection;
import exchange.IExchange;

public interface IView {
    public void setExchange(IExchange exchange); //Ustawia referencję do obiektu typu implementującego IExchange
    public void setDataCollection(IDataCollection collection); //Ustawia referencję do obiektu typu implementującego IDataCollection
    public void ViewAll(IDataCollection coll); //Wyświetla wszystkie waluty
    public ICurrency StringToCurrency(String code); //Po podaniu kodu tworzy obiekt Currency z ustawionym kodem, a następnie z jego wykorzystaniem przeszukuje kolekcję typu IDataCollection
    public ICurrency ChooseCurrency(String label); //Prosi uytkownika o podanie kodu, a następnie deleguje wybór waluty do metody StringToCurrency
    public void exchange(); //Interakcyjnie na bazie metody ChooseCurrency dokonuje przeliczenia walut na bazie IExchange
    public void menu(); //Interakcyjnie wyświetla menu
}
