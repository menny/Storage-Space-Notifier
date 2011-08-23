package net.evendanan.android.storagespacenotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	private static final String TAG = "AlarmReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Timer poped. Checking internal storage...");
		StorageSpaceChecker.check(context);
	}

}
