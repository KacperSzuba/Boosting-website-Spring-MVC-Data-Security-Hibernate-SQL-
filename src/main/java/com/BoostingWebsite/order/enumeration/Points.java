package com.BoostingWebsite.order.enumeration;

public enum Points {
    ZERO_TO_NINETEEN("0-19"),
    TWENTY_TO_THIRTY_NINE("20-39"),
    FORTY_TO_FIFTY_NINE("40-59"),
    SIXTY_TO_SEVENTY_NINE("60-79"),
    EIGHTY_TO_NINETY_NINE("80-99"),
    SERIES("Series");

    private final String name;

    Points(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
