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

	public void setBasePath(String path)
	{
		prefs.edit().putString("base_path", path).apply();
	}

	public String getBasePath()
	{
		return prefs.getString("base_path", "");
	}

	public void setFloorId(int floor)
	{
		prefs.edit().putInt("floor_id", floor).apply();
	}

	public int getFloorId()
	{
		return prefs.getInt("floor_id", 0);
	}

	public void setFloorName(String floor)
	{
		prefs.edit().putString("floor_name", floor).apply();
	}

	public String getFloorName()
	{
		return prefs.getString("floor_name", "");
	}

	public void setEmployeeName(String floor)
	{
		prefs.edit().putString("employee_name", floor).apply();
	}

	public String getEmployeeName()
	{
		return prefs.getString("employee_name", "");
	}

	public void setEmployeeId(int floor)
	{
		prefs.edit().putInt("employee_id", floor).apply();
	}

	public int getEmployeeId()
	{
		return prefs.getInt("employee_id", 0);
	}


}
