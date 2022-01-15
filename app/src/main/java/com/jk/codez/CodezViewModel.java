package com.jk.codez;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jk.codez.ad.AestheticDialog;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class CodezViewModel extends ViewModel {
    MutableLiveData<ArrayList<Item>> items = new MutableLiveData<>();

    Comparator<Item> sortByNumber = Comparator.comparing(Item::getNumber);
    Comparator<Item> sortByStreet = Comparator.comparing(Item::getStreet);
    Comparator<Item> sortByCodes = Comparator.comparing(Item::getCodesString);

    public void setCodes(ArrayList<Item> codes) {
        this.items = new MutableLiveData<>(codes);
    }

    public MutableLiveData<ArrayList<Item>> getCodes() {
        System.out.println("getCodes");
        System.out.println(items.getValue() == null ? "Null" : "Mot null");
        if (this.items.getValue() == null) refreshCodes();
        return this.items;
    }

    public void sortListByNumber(boolean searchReverse) {
        ArrayList<Item> itemList = items.getValue();
        Objects.requireNonNull(itemList).sort(searchReverse ? sortByNumber.reversed() : sortByNumber);
        items.setValue(itemList);
    }

    public void sortListByStreet(boolean searchReverse) {
        ArrayList<Item> itemList = items.getValue();
        Objects.requireNonNull(itemList).sort(searchReverse ? sortByStreet.reversed() : sortByStreet);
        items.setValue(itemList);
    }

    private void refreshCodes() {
        Network.getCodes(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(final int statusCode, final Header[] headers, final String responseString) {
                System.out.println(responseString);
                Type type = new TypeToken<ArrayList<Item>>() {}.getType();
                CodezViewModel.this.items.setValue(new Gson().fromJson(responseString, type));
            }

            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String responseString, final Throwable throwable) {
                System.out.println("Failed!");
            }
        });
    }

    public void addCode(Item i, AestheticDialog.Builder dialog) {
        System.out.println("vm - addcode");
        Network.addCode(i, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("NOT Added!");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println("Added!");
                System.out.println(responseString);
                Type type = new TypeToken<ArrayList<Item>>() {}.getType();
                CodezViewModel.this.items.setValue(new Gson().fromJson(responseString, type));
                dialog.dismiss();
            }
        });
    }

    public void editCode(Item i, AestheticDialog.Builder dialog) {
        System.out.println("vm - editCode");
        Network.editCode(dialog.getItem(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("NOT Deleted!");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println("Deleted!");
                Type type = new TypeToken<ArrayList<Item>>() {}.getType();
                CodezViewModel.this.items.setValue(new Gson().fromJson(responseString, type));
                dialog.dismiss();
            }
        });
    }

    public void deleteCode(AestheticDialog.Builder dialog) {
        System.out.println("vm - deleteCode");
        Network.deleteCode(dialog.getItem()._id, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("NOT Deleted!");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println("Deleted!");
                Type type = new TypeToken<ArrayList<Item>>() {}.getType();
                CodezViewModel.this.items.setValue(new Gson().fromJson(responseString, type));
                dialog.dismiss();
            }
        });
    }
}
