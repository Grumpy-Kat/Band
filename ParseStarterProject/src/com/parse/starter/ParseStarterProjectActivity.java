package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {


			Intent intent = new Intent(ParseStarterProjectActivity.this, LoginSignupActivity.class);
			startActivity(intent);
			finish();

		} else {

			ParseUser currentUser = ParseUser.getCurrentUser();

			if(currentUser != null) {

				Intent intent = new Intent(ParseStarterProjectActivity.this, HomePage.class);
				startActivity(intent);
				finish();

			} else {

				Intent intent = new Intent(ParseStarterProjectActivity.this, LoginSignupActivity.class);
				startActivity(intent);
				finish();

			}

		}



		ParseAnalytics.trackAppOpenedInBackground(getIntent());
	}
}
