package org.hopto.rubengm.roomtest.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.hopto.rubengm.roomtest.db.objects.Item;

import java.util.List;

/**
 * Creado por ruben el 18/02/2018.
 */
@Dao
public interface ItemDao {
	@Query("SELECT * FROM Item ORDER BY id DESC")
	List<Item> selectAll();

	@Query("SELECT * FROM Item ORDER BY id DESC")
	LiveData<List<Item>> selectAllLive();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Item... items);

	@Update
	void update(Item item);

	@Delete
	void delete(Item... items);

	@Query("DELETE FROM Item")
	void empty();

	@Query("UPDATE Item SET isNew = 0")
	void markAsRead();

	@Query("UPDATE Item SET isNew = 0 WHERE id = :itemId")
	void read(int itemId);
}