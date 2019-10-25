package aim.helmi.bootcamp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private Context context;
    private SharedPreferences sp;

    public Preference(Context context){
        this.context = context;
        sp= context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setIsLogin(Boolean login){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isLogin", login);
        edit.apply();
    }
    public boolean getIsLogin(){
        return sp.getBoolean("islogin", false);
    }

    public void setUsername(String username){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username", username);
        edit.apply();
    }

    public String getUsername() {
        return sp.getString("username", "Anonymous");
    }
}
