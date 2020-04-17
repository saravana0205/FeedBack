package com.istrides.ranjeetfeedback.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreference
{
	private SharedPreferences prefs;

	public SharedPreference(Context context)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void clear()
	{
		prefs.edit().clear().apply();
	}

	public void setBasePath(String path)
	{
		prefs.edit().putString("base_path", path).apply();
	}

	public String getBasePath()
	{
		return prefs.getString("base_path", "");
	}

	public void setFloor(int floor)
	{
		prefs.edit().putInt("floor", floor).apply();
	}

	public int getFloor()
	{
		return prefs.getInt("floor", 0);
	}

}
