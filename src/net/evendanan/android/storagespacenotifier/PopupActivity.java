package net.evendanan.android.storagespacenotifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class PopupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
	}
	
	public void onUserClickedManageApps(View v)
	{
		Intent manageIntent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
		startActivity(manageIntent);
		finish();
	}
	
	public void onUserClickedCancel(View v)
	{
		finish();
	}
}
