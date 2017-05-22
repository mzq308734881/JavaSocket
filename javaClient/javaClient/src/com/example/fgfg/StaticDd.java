package com.example.fgfg;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;

public class StaticDd extends ActionBarActivity{
	public boolean isNetworkConnected() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo ni = cm.getActiveNetworkInfo();
	    return ni != null && ni.isConnectedOrConnecting();
	}
}
