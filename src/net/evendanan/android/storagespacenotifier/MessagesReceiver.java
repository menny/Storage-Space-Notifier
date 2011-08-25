package net.evendanan.android.storagespacenotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessagesReceiver extends BroadcastReceiver {

	private static final String TAG = "MessagesReceiver";

	@Override
	public void onReceive(Context cntxt, Intent data) {
		Log.i(TAG, "Message received. Checking internal storage...");
		StorageSpaceChecker.check(cntxt);
	}
}
