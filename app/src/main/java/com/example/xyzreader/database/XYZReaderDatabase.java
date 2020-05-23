package com.example.xyzreader.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ArticleEntity.class, version = 1, exportSchema = false)
public abstract class XYZReaderDatabase extends RoomDatabase {

    private static final String LOG = XYZReaderDatabase.class.getSimpleName();

    public static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "xyz_reader_database";

    public static volatile XYZReaderDatabase INSTANCE;

    public static XYZReaderDatabase getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                Log.d(LOG, "Creating a new XYZ Reader Database");

                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        XYZReaderDatabase.class,
                        XYZReaderDatabase.DATABASE_NAME
                ).build();
            }
        }

        Log.d(LOG, "Getting the database instance");
        return INSTANCE;
    }

    public abstract XYZReaderDao xyzReaderDao();
}