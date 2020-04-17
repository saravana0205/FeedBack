package com.istrides.ranjeetfeedback;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.istrides.ranjeetfeedback.common.SharedPreference;

public class BaseActivity extends AppCompatActivity
{
	boolean doubleBackToExitPressedOnce = false;
	ConstraintLayout rootLayout;
	SharedPreference sharedPreference;
	TextInputEditText baseURL;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		if(getSupportActionBar() != null) getSupportActionBar().hide();
		rootLayout = findViewById(R.id.rootLayout);
		MaterialButton next = findViewById(R.id.next);
		baseURL = findViewById(R.id.baseURL);
		sharedPreference = new SharedPreference(this);
		next.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String base_url =  "" ;
				if(baseURL.getText() != null)
				{
					base_url = baseURL.getText().toString();
				}
				if(base_url.trim().length() > 4 )
				{
					sharedPreference.setBasePath(base_url);
					startActivity(new Intent(BaseActivity.this, FloorSelectionActivity.class));
					finish();
				}
				else
				{
					Snackbar.make(v, "Enter a Valid URL for setup", Snackbar.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		if(doubleBackToExitPressedOnce)
		{
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Snackbar.make(rootLayout, "Please click BACK again to exit", Snackbar.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}
}
