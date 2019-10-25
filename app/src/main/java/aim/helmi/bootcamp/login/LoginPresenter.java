package aim.helmi.bootcamp.login;

import android.content.Context;

import aim.helmi.bootcamp.room.BcDatabase;
import aim.helmi.bootcamp.room.table.User;

public class LoginPresenter {

    private LoginView view;
    private BcDatabase dbRoom;
//    private SQLiteDB db;

    public LoginPresenter(Context context, LoginView view) {
        this.view = view;
        dbRoom = BcDatabase.getDatabase(context);
//        db = new SQLiteDB(context);
    }

    public void doLogin(String username, String password) {
        User user = dbRoom.userDao().userLogin(username, password);
//        User user = db.login(username, password);
        if (user != null) {
            view.onLoginSuccess(user.username);
        } else {
            view.onError("Wrong username or password!");
        }
    }
}