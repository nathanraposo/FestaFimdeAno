package com.example.android.festafimdeano.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nathan on 14/09/17.
 */

public class SecurityPreferences {

    private final SharedPreferences sharedPreferences;

    public SecurityPreferences(Context context) {
        //primeiro parametro é o nome do arquivo que será salvo
        //o segundo parametro só o app consegue ler esse arquivo
        this.sharedPreferences = context.getSharedPreferences("FimDeAno",Context.MODE_PRIVATE);
    }

    public void storeString(String key,String value){
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoreString(String key){
       return this.sharedPreferences.getString(key,"");
    }
}
