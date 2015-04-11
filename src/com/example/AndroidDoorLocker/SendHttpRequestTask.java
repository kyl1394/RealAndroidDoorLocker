package com.example.AndroidDoorLocker;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kyle on 11/4/2014.
 */
public class SendHttpRequestTask extends AsyncTask<String, Void, Boolean>
{
	private View rootView;

	public SendHttpRequestTask(View mainView)
	{
		this.rootView = mainView;
	}
	protected Boolean doInBackground(String... methods)
	{
		String inputLine;
		StringBuilder response = new StringBuilder();

		try
		{
			URL url = new URL("http://lock.austinsalz.com/" + methods[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return !response.toString().equals("");
	}


	protected void onPostExecute(Boolean result)
	{
		ImageView lockIndicator = (ImageView) ((View) rootView.getParent()).findViewById(R.id.lockIndicator);

		//set indicator to show success of signal
		if (result)
		{
			lockIndicator.setBackgroundColor(Color.GREEN);
		}
		else
		{
			lockIndicator.setBackgroundColor(Color.RED);
		}
	}
}
