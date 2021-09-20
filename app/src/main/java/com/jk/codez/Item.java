package com.jk.codez;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Locale;

public class Item {

    public Item(Integer number, String street, String[] codes, String notes) {
        this.number = number;
        this.street = street;
        this.codes = codes;
        this.notes = notes;
    }

    public Item() {
        this.number = null;
        this.street = "";
        this.codes = null;
        this.notes = "";
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%d %s - %d codes - %s",
                number, street, codes.length, notes);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String address) {
        this.street = address;
    }

    public String[] getCodes() {
        return codes;
    }

    public String getCodesString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.length; ++ i) {
            sb.append(codes[i]);
            if (i < codes.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public void addCode(String code) {

    }

    public void deleteCode(String code) {

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    String _id;
    Integer number;
    String street;
    String[] codes;
    String notes;
}
