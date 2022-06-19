package edu.unicauca.moovster.db;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdminsSQLHelper extends SQLiteOpenHelper {


    public AdminsSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AdminsSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase dbMoovster) {
        dbMoovster.execSQL("CREATE TABLE User ( name String, email String PRIMARY KEY,  password String NOT NULL);");
        dbMoovster.execSQL("CREATE TABLE Role ( roleName String PRIMARY KEY,  roleDescription String,  RoleTitle String,  roleImage String);");
        dbMoovster.execSQL("CREATE TABLE Movies(movie_id INTEGER, stars Int DEFAULT 0, views Int DEFAULT 0, fav Boolean DEFAULT false, user_email String,  PRIMARY KEY (movie_id, user_email),FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION);");
        dbMoovster.execSQL("CREATE TABLE User_Role( user_email String,  role String,  FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (role) REFERENCES Rol (email) ON DELETE CASCADE ON UPDATE NO ACTION);");
        dbMoovster.execSQL("CREATE TABLE UserLogged (user_email String PRIMARY KEY, isLogged Boolean DEFAULT false, FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
