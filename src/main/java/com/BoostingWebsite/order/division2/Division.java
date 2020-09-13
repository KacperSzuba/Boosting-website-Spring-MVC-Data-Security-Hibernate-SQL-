package com.BoostingWebsite.order.division2;

public enum Division {
    IRON4("Iron IV"),
    IRON3("Iron III"),
    IRON2("Iron II"),
    IRON1("Iron I"),
    BRONZE4("Bronze IV"),
    BRONZE3("Bronze III"),
    BRONZE2("Bronze II"),
    BRONZE1("Bronze I"),
    SILVER4("Silver IV"),
    SILVER3("Silver III"),
    SILVER2("Silver II"),
    SILVER1("Silver I"),
    GOLD4("Gold IV"),
    GOLD3("Gold III"),
    GOLD2("Gold II"),
    GOLD1("Gold I"),
    PLATINUM4("Platinum IV"),
    PLATINUM3("Platinum III"),
    PLATINUM2("Platinum II"),
    PLATINUM1("Platinum I"),
    DIAMOND4("Diamond IV"),
    DIAMOND3("Diamond III"),
    DIAMOND2("Diamond II"),
    DIAMOND1("Diamond I");

    public final static String PLACEMENTS = "Placements";
    private String name;

    Division(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
