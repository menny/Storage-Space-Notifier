/* The following code was written by Menny Even Danan
 * and is released under the APACHE 2.0 license
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package net.evendanan.android.storagespacenotifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	private static final String TAG = "BootReceiver";

	@Override
	public void onReceive(Context cntxt, Intent data) {
		Log.i(TAG,"Welcome to net.evendanan.android.storagespacenotifier (c) Menny Even Danan 2011, under the Apache2 license. This application is licensed to Beres, LTD Israel to use on Sony-Ericsson X8 devices. See https://github.com/menny/Storage-Space-Notifier for source code.");
		Log.i(TAG, "Device booted. Checking internal storage...");
		StorageSpaceChecker.check(cntxt);
		//starting timer
		Intent intent = new Intent(cntxt, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(cntxt, 100233, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmer = (AlarmManager) cntxt.getSystemService(Context.ALARM_SERVICE);
		long interval = cntxt.getResources().getInteger(R.integer.storage_space_check_interval_milliseconds);
		alarmer.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, interval, interval, sender);
	}
}
