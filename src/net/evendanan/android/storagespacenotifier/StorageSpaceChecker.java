/* The following code was written by Menny Even Danan
 * and is released under the APACHE 2.0 license
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package net.evendanan.android.storagespacenotifier;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;

public class StorageSpaceChecker {

	private static final String TAG = "StorageSpaceChecker";

	private static final int NOTIFICATION_ID = 98123;
	
	public static void check(Context cntxt) {
		try
		{
			File path = Environment.getDataDirectory();
			StatFs stat = new StatFs(path.getPath());
			final long deviceInternalSpaceFreeSpace = (stat.getBlockSize() * stat.getAvailableBlocks());
			Resources res = cntxt.getResources();
			final int warningLimit = res.getInteger(R.integer.storage_space_check_warning_bytes_limit);
			final int criticalLimit = res.getInteger(R.integer.storage_space_check_critical_bytes_limit);
			
			Log.d(TAG, "Storage related event occour. Free storage: "+deviceInternalSpaceFreeSpace+" bytes. Warning limit: "+warningLimit+" bytes, critical limits : "+criticalLimit+" bytes.");
			
			NotificationManager nm = (NotificationManager)cntxt.getSystemService(Context.NOTIFICATION_SERVICE);
			
			if (deviceInternalSpaceFreeSpace <= criticalLimit)
			{
				Notification notification = new Notification(R.drawable.icon, res.getText(R.string.low_storage_critical_title), System.currentTimeMillis());

				Intent notificationIntent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
				PendingIntent contentIntent = PendingIntent.getActivity(cntxt, 0, notificationIntent, 0);

				notification.setLatestEventInfo(cntxt,
						res.getText(R.string.low_storage_critical_title), 
						res.getText(R.string.low_storage_critical_message),
						contentIntent);
				notification.flags |= Notification.FLAG_ONGOING_EVENT;
				notification.flags |= Notification.FLAG_NO_CLEAR;
				notification.defaults |= Notification.DEFAULT_ALL;
				// notifying
				nm.notify(NOTIFICATION_ID, notification);
			}
			else if (deviceInternalSpaceFreeSpace <= warningLimit)
			{
				Notification notification = new Notification(R.drawable.icon, res.getText(R.string.low_storage_warning_title), System.currentTimeMillis());

				Intent notificationIntent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
				PendingIntent contentIntent = PendingIntent.getActivity(cntxt, 0, notificationIntent, 0);

				notification.setLatestEventInfo(cntxt,
						res.getText(R.string.low_storage_warning_title), 
						res.getText(R.string.low_storage_warning_message),
						contentIntent);
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
				// notifying
				nm.notify(NOTIFICATION_ID, notification);
			}
			else
			{
				nm.cancel(NOTIFICATION_ID);
			}
			
		}
		catch(Throwable e)
		{
			Log.e(TAG, "Caught an unexpected exception!! "+e.toString());
			e.printStackTrace();
		}
	}

}
