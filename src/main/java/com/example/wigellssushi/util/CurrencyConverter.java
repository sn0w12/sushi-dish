package com.example.wigellssushi.util;

public class CurrencyConverter {

    private static final double EXCHANGE_RATE_SEK_TO_EURO = 0.089;

    public static double convertSEKToEuro(double priceSEK) {
        return priceSEK * EXCHANGE_RATE_SEK_TO_EURO;
    }
}
