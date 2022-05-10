package com.example.timerandgame;

/**
 * Model class to store values of Game list API response.
 */
public class CountryModel {

    private String countyName;

    CountryModel(String countryName) {
        this.countyName = countryName;
    }

    public String getCountryName() {
        return countyName;
    }

    public void setCountryName(String countryName) {
        this.countyName =countryName;
    }
}
