package edu.unicauca.moovster.db;
import android.content.ContentValues;
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
        dbMoovster.execSQL("CREATE TABLE Rol ( rolName String PRIMARY KEY,  rolDescription String,  RolTitle String);");
        dbMoovster.execSQL("CREATE TABLE Movies(movie_id INTEGER, stars Int DEFAULT 0, views Int DEFAULT 0, fav Boolean DEFAULT false, user_email String,  PRIMARY KEY (movie_id, user_email),FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION);");
        dbMoovster.execSQL("CREATE TABLE User_Rol( user_email String,  rol String,  FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (rol) REFERENCES Rol (rolName) ON DELETE CASCADE ON UPDATE NO ACTION);");
        dbMoovster.execSQL("CREATE TABLE UserLogged (user_email String PRIMARY KEY, isLogged Boolean DEFAULT false, FOREIGN KEY (user_email) REFERENCES User (email) ON DELETE CASCADE ON UPDATE NO ACTION);");
        FillRolData(dbMoovster);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void FillRolData(SQLiteDatabase Db){

        ContentValues registroRol2 = new ContentValues();
        registroRol2.put("rolName", "Adventure");
        registroRol2.put("rolDescription", "Hay demasiadas aventuuras ahi en la pantalla, esperando a ser vistas...");
        registroRol2.put("RolTitle", "Un aventurero, no por eleccion, sino por destino");
        Db.insert("Rol", null, registroRol2);

        ContentValues registroRol = new ContentValues();
        registroRol.put("rolName", "Sustos");
        registroRol.put("rolDescription", "Una lista de peliculas enfocada para los amantes del terror, si esta noche quieres conciliar el sueño, este no es un genero para ti. ");
        registroRol.put("RolTitle", "Para el miedo no hay limite");
        Db.insert("Rol", null, registroRol);

        ContentValues registroRol3 = new ContentValues();
        registroRol3.put("rolName", "Western");
        registroRol3.put("rolDescription", "Una lista de peliculas enfocada para ti, que te sientes como en el viejo oeste");
        registroRol3.put("RolTitle", "El sherif del lugar");
        Db.insert("Rol", null, registroRol3);

    }
}
