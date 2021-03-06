package com.BoostingWebsite.utils;

public class ConstDto {
    public static Builder builder(){
        return new Builder();
    }

    private final Long id;
    private final EnumConst enumConst;
    private final String value;

    private ConstDto(Builder builder){
        id = builder.id;
        enumConst = builder.enumConst;
        value = builder.value;
    }

    public static class Builder{
        private Long id;
        private EnumConst enumConst;
        private String value;

        private Builder(){}

        public ConstDto build(){
            return new ConstDto(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withEnumConst(EnumConst enumConst) {
            this.enumConst = enumConst;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }
    }

    public Long getId() {
        return id;
    }

    public EnumConst getEnumConst() {
        return enumConst;
    }

    public String getValue() {
        return value;
    }
}
