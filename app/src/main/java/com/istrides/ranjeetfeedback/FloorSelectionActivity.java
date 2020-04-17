package com.istrides.ranjeetfeedback;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class FloorSelectionActivity extends AppCompatActivity implements View.OnClickListener
{
	TextView txt_floor_1, txt_floor_2, txt_floor_3;
	boolean doubleBackToExitPressedOnce = false;
	ConstraintLayout rootLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_selection);
		if(getSupportActionBar() != null) getSupportActionBar().hide();
		rootLayout = findViewById(R.id.rootLayout);
		MaterialCardView firstFloor = findViewById(R.id.firstFloor);
		MaterialCardView secondFloor = findViewById(R.id.secondFloor);
		MaterialCardView thirdFloor = findViewById(R.id.thirdFloor);
		txt_floor_1 = findViewById(R.id.txt_floor_1);
		txt_floor_2 = findViewById(R.id.txt_floor_2);
		txt_floor_3 = findViewById(R.id.txt_floor_3);
		firstFloor.setOnClickListener(this);
		thirdFloor.setOnClickListener(this);
		secondFloor.setOnClickListener(this);
	}

	@Override
	public void onClick(final View v)
	{
		v.setEnabled(false);
		if(v.getId() == R.id.firstFloor)
		{
			txt_floor_3.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
		}
		else if(v.getId() == R.id.secondFloor)
		{
			txt_floor_2.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
		}
		else if(v.getId() == R.id.thirdFloor)
		{
			txt_floor_1.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
		}
		startActivity(new Intent(FloorSelectionActivity.this, EmployeeSelectionActivity.class));
		finish();
	}

	@Override
	public void onBackPressed()
	{
		if(getIntent().getExtras() != null && getIntent().hasExtra("key") && getIntent().getExtras().get("key") != null && getIntent().getExtras().get("key").equals("1"))
		{
			super.onBackPressed();
		}
		else
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
}
