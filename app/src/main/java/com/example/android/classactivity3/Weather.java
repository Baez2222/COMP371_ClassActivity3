package com.example.android.classactivity3;

public class Weather {
    private String date;
    private String description;
    private Double feelsNumber;

    // constructor
    public Weather(String date, String description, Double feelsNumber) {
        this.date = date;
        this.description = description;
        this.feelsNumber = feelsNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFeelsNumber() {
        return feelsNumber;
    }

    public void setFeelsNumber(Double feelsNumber) {
        this.feelsNumber = feelsNumber;
    }
}
