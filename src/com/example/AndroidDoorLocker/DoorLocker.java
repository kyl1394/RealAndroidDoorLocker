package com.example.AndroidDoorLocker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DoorLocker extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * called when the user presses the lock/unlock buttons
	 */
	public void sendSignal(View view) throws InterruptedException
	{
		//send signal to lock.austinsalz.com/lock or /unlock depending upon the button that called this method
		switch (view.getId())
		{
			case R.id.lockButton:
				new SendHttpRequestTask(view).execute("lock");
				break;
			case R.id.unlockButton:
				new SendHttpRequestTask(view).execute("unlock");
				break;
		}
	}
}
