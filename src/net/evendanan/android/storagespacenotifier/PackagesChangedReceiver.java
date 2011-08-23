package net.evendanan.android.storagespacenotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PackagesChangedReceiver extends BroadcastReceiver {

	private static final String TAG = "PackagesChangedReceiver";

	@Override
	public void onReceive(Context cntxt, Intent data) {
		Log.i(TAG, "Packages changed. Checking internal storage...");
		StorageSpaceChecker.check(cntxt);
	}

}
