package com.roundsToThree.Structures;

public class Date {

    // Since year is mandatory, it is used to determine whether Date is specified or not
    public short year = -1;
    public byte month = 1;
    public byte day = 1;

    public Date(short year, byte month, byte day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Secondary method that typecasts internally for convenience
    public Date(int year, int month, int day) {
        this.year = (short) year;
        this.month = (byte) month;
        this.day = (byte) day;
    }

    @Override
    public String toString() {
        if (year == -1)
            return "N/A";
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
