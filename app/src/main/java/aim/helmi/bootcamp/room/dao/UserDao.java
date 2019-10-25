package aim.helmi.bootcamp.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import aim.helmi.bootcamp.room.table.User;

@Dao
public interface UserDao {
    @Insert
    long register(User user);

    @Query("SELECT * from user where username=:username AND password=:password LIMIT 1")
    User userLogin(String username, String password);


}
