package com.semicolon.africa.House.Management.System.data.models;

public enum Payment {



    TWO_HUNDRED_THOUSAND(200_000),
    THREE_HUNDRED_THOUSAND(400_000),
    SIX_HUNDRED_THOUSAND(600_000);

    private final int value;

    Payment(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
