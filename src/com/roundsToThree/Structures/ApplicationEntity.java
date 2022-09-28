package com.roundsToThree.Structures;

public class ApplicationEntity {
    public String value;

    public ApplicationEntity(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
