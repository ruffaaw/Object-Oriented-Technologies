package collections;

import currency.ICurrency;

import java.util.List;

public interface IDataCollection {
    String toString(); //Zwraca ciąg znaków, reprezentujący wszystkie obiekty Currency komponowane w klasie
    List<ICurrency> getCurrencyList(); //Zwraca referencję do listy obiektów Currency w klasie
    ICurrency getCurrencyByCode(ICurrency currency); //Zwraca pełen obiekt Currency z listy po podaniu obiektu Currency, z ustawionym kodem waluty
}
