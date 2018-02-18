package org.hopto.rubengm.roomtest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import org.hopto.rubengm.roomtest.db.dao.ItemDao;
import org.hopto.rubengm.roomtest.db.objects.Item;

/**
 * Creado por ruben el 18/02/2018.
 */
@Database(entities = Item.class, version = 1)
public abstract class TestDB extends RoomDatabase {
	private static TestDB mInstance;

	public abstract ItemDao getItemDao();

	public static TestDB getInstance(@NonNull Context context) {
		if(null == mInstance) {
			mInstance = Room.databaseBuilder(context.getApplicationContext(), TestDB.class, "roomtest.db").build();
		}
		return mInstance;
	}

	public static void closeDb() {
		mInstance = null;
	}
}