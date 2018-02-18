package org.hopto.rubengm.roomtest.db;

import android.os.Handler;
import android.support.annotation.NonNull;

import static android.os.Looper.getMainLooper;

/**
 * Creado por ruben el 18/02/2018.
 */
class BaseDatamanager {
	private static final Handler mHandler = new Handler(getMainLooper());

	static void thread(@NonNull Runnable r) {
		new Thread(r).start();
	}

	static void run(@NonNull Runnable r) {
		mHandler.post(r);
	}
}