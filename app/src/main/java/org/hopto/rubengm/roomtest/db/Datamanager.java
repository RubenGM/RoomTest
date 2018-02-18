package org.hopto.rubengm.roomtest.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import org.hopto.rubengm.roomtest.db.objects.Item;

import java.util.List;

/**
 * Creado por ruben el 18/02/2018.
 */
public class Datamanager extends BaseDatamanager {

	public interface ItemListener {
		void onReceived(@NonNull List<Item> items);
	}
	public interface LiveItemListener {
		void onReceived(@NonNull LiveData<List<Item>> liveItems);
	}

	public static void getItems(@NonNull Context context, @NonNull ItemListener listener) {
		thread(() -> {
			List<Item> items = TestDB.getInstance(context).getItemDao().selectAll();
			run(() -> listener.onReceived(items));
		});
	}

	public static void getLiveItems(@NonNull Context context, @NonNull LiveItemListener listener) {
		thread(() -> {
			LiveData<List<Item>> liveItems = TestDB.getInstance(context).getItemDao().selectAllLive();
			run(() -> listener.onReceived(liveItems));
		});
	}

	public static void empty(@NonNull Context context) {
		thread(() -> TestDB.getInstance(context).getItemDao().empty());
	}

	public static void insertItems(@NonNull Context context, Item... items) {
		thread(() -> TestDB.getInstance(context).getItemDao().insert(items));
	}

	public static void deleteItems(@NonNull Context context, Item... items) {
		thread(() -> TestDB.getInstance(context).getItemDao().delete(items));
	}

	public static void updateItem(@NonNull Context context, @NonNull Item item) {
		thread(() -> TestDB.getInstance(context).getItemDao().update(item));
	}

	public static void markAllAsRead(@NonNull Context context) {
		thread(() -> TestDB.getInstance(context).getItemDao().markAsRead());
	}

	public static void setRead(@NonNull Context context, int itemId) {
		thread(() -> TestDB.getInstance(context).getItemDao().read(itemId));
	}
}