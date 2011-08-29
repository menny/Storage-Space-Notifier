/* The following code was written by Menny Even Danan
 * and is released under the APACHE 2.0 license
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
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
