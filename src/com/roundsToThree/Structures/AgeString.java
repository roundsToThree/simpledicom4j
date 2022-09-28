package com.roundsToThree.Structures;

public class AgeString {
    // Structure variable
    // Age in days alive
    public int age = 0;

    public AgeString(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        // Account for different precisions
        if (age < 14)
            // If < 2wks, report age in days
            return age + " day" + (age > 0 || age == 0 ? "s" : "");

        if (age < 60)
            // If < 2mo, report age in weeks
            return Integer.toString(age / 7) + " weeks";

        if (age < 730)
            // if < 2yr, report age in months
            return Integer.toString(age / 30) + " months";

        // Otherwise report age in years
        return Integer.toString(age / 365) + " years";
    }
}
