package com.example.blackjack;

public class Card {

    String name;
    int image, value;

    public Card(String name, int value, int image) {
        this.name = name;
        this.image = image;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
