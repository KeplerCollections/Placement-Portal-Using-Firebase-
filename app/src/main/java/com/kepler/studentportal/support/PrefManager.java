package com.kepler.studentportal.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.kepler.studentportal.api.ApiClient.USERNAME;

/**
 * Created by kepler on 2/4/18.
 */

public class PrefManager {

    private static final String PREF_NAME = "my_app_pref";
    private static final String REG_ID = "ah_app_reg_id";
    private static PrefManager prefManager;
    private static SharedPreferences shardPref;

    private void PreferenceManager(){

    }

    public static PrefManager getPrefrences(Context context){
        if(prefManager==null)
            prefManager=new PrefManager();
        if(shardPref==null && context!=null)
            shardPref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return prefManager;
    }

    public void loggedIn(String username){
        if(shardPref!=null){
            shardPref.edit().putString(USERNAME,username).apply();
        }
    }

    public boolean logout(){
        if(shardPref!=null){
            return shardPref.edit().putString(USERNAME,null).commit();
        }
        return false;
    }

    public boolean isInSession(){
        if(shardPref!=null){
            return (shardPref.getString(USERNAME,null)==null) ? false :true;
        }
        return false;
    }

    public String getUsername(){
        if(shardPref!=null){
            return shardPref.getString(USERNAME,null);
        }
        return null;
    }

    /*store */
    public void storeRegIdInPref(String token) {
        if (shardPref != null) {
            shardPref.edit().putString(REG_ID, token).apply();
        }
    }

    /*get reg is regestered*/
    public String getRegId() {
        return (shardPref == null) ? null : shardPref.getString(REG_ID, null);
    }
}
