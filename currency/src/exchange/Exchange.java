package exchange;

import currency.ICurrency;

public class Exchange implements IExchange{
    @Override
    public double exchange(ICurrency src, ICurrency tgt, double amt) {
        double ret;
        double rate = src.getRate() / tgt.getRate();
        ret = rate * amt;
        return ret;
    }
}
