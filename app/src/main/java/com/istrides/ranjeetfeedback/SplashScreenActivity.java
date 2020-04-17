package com.istrides.ranjeetfeedback;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.istrides.ranjeetfeedback.common.SharedPreference;

public class SplashScreenActivity extends AppCompatActivity
{
	SharedPreference sharedPreference;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		if(getSupportActionBar() != null)
		getSupportActionBar().hide();
		ConstraintLayout layout = findViewById(R.id.layout);
		Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
		layout.startAnimation(aniSlide);
		Handler handler = new Handler();
		sharedPreference = new SharedPreference(this);
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				Intent NextActivity ;
				if(sharedPreference.getBasePath().isEmpty())
				{
					NextActivity = new Intent(SplashScreenActivity.this,BaseActivity.class);
				}
				else
				{
					if(sharedPreference.getFloor() > 0)
					{
						NextActivity = new Intent(SplashScreenActivity.this,EmployeeSelectionActivity.class);
					}
					else
					{
						NextActivity = new Intent(SplashScreenActivity.this,FloorSelectionActivity.class);
					}

				}
				startActivity(NextActivity);
				finish();
			}
		}, 2800);

	}
}
