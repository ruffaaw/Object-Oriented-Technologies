package currency;

public class Currency implements ICurrency{
    String name;
    String code;
    double factor;
    double rate;

    public Currency(){
        name = "";
        code = "";
        factor = 0;
        rate = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return this.code.equals(currency.getCode());
    }

    public String toString(){
        return name + " " + code;
    }
}
