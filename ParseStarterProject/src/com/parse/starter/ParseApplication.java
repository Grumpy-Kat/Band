package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    //the commented initialize is for my account this one below is eswara ids
    Parse.initialize(this, "wO2LX0IkMddzyQ7YAkgxVP1zFVHx8mRsRTIqjKjV", "rBGnc5dc7b1zqR3mTwETQdmNiyNoTbvuctGjkUlm");
    //Parse.initialize(this, "RJk6pZKbzFQ87dbSeyhsdIjif2xCIWdDrAsy8oo4", "v6rP4WMfoZjgKKxMhynUMXg9Hz4cyaSOCrzty32d");
   // Parse.initialize(this);



    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
     defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
