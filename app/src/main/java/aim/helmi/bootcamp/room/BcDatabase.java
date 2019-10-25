package aim.helmi.bootcamp.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import aim.helmi.bootcamp.room.dao.UserDao;
import aim.helmi.bootcamp.room.table.User;

@Database(entities = {User.class}, version = 1)
public abstract class BcDatabase extends RoomDatabase {
    private static BcDatabase INSTANCE;

    public abstract UserDao userDao();


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static BcDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, BcDatabase.class, "bc-room")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}
