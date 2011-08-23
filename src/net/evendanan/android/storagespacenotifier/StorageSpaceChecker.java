package net.evendanan.android.storagespacenotifier;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class StorageSpaceChecker {

	private static final String TAG = "StorageSpaceChecker";

	public static void check(Context cntxt) {
		try
		{
			File path = Environment.getDataDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			
			Log.d(TAG, "Storage related event occour. Free storage: "+(blockSize*availableBlocks)+" bytes.");
		}
		catch(Throwable e)
		{
			Log.e(TAG, "Caught an unexpected exception!! "+e.toString());
			e.printStackTrace();
		}
	}

}
