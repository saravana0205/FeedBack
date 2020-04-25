package com.istrides.ranjeetfeedback.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

public class QuestionRating
{
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;

	public QuestionRating(Context context)
	{
		editor = context.getSharedPreferences("MyPref", MODE_PRIVATE).edit();
		prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
	}

	public void clear()
	{
		prefs.edit().clear().apply();
	}

	public void setRating(String key, int value)
	{
		editor.putInt(key, value).apply();
	}

	public int getRating(String key)
	{
		return prefs.getInt(key, -1);
	}

}
