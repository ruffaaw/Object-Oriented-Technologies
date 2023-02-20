package view;

import collections.IDataCollection;
import currency.Currency;
import currency.ICurrency;
import exchange.IExchange;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View implements IView {
    IExchange exchange;
    IDataCollection collection;

    @Override
    public void setExchange(IExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void setDataCollection(IDataCollection collection) {
        this.collection = collection;
    }

    @Override
    public void ViewAll(IDataCollection coll) {
        System.out.println(collection.toString());
    }

    @Override
    public ICurrency StringToCurrency(String code) {
        ICurrency currency = new Currency();
        currency.setCode(code);
        currency = collection.getCurrencyByCode(currency);
        return currency;
    }

    @Override
    public ICurrency ChooseCurrency(String label) {
        Scanner scanner = new Scanner(System.in);
        ICurrency currency = null;
        Boolean bool = true;
        for (int i = 0; bool; i++) {
            String code = scanner.nextLine();
            currency = StringToCurrency(code);
            if(currency != null){
                bool = false;
            }else{
                System.err.printf("Prosze podac prawidlowa walute\n");
            }
        }
        return currency;
    }

    @Override
    public void exchange() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj walute ktora chcesz wymienic:");
        ICurrency currencyFrom = ChooseCurrency("");
        System.out.println("Podaj ilosc:");
        double amt = scanner.nextDouble();
        System.out.println("Podaj walute na ktora chcesz wymienic:");
        ICurrency currencyTo = ChooseCurrency("");
        System.out.println(exchange.exchange(currencyFrom, currencyTo, amt));
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pokaz mozliwe waluty - 1");
        System.out.println("Wymien waluty - 2");
        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                ViewAll(collection);
            } else if (choice == 2) {
                exchange();
            } else {
                System.err.println("Zly wybor");
            }
        }catch (InputMismatchException e){
            System.err.println("Wprowadz prawidlowe dane");
        }
    }
}
