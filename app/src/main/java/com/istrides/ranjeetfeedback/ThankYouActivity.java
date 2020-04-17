package com.istrides.ranjeetfeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ThankYouActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thank_you);
		getSupportActionBar().hide();
		MaterialButton complete = findViewById(R.id.complete);
		complete.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(ThankYouActivity.this,EmployeeSelectionActivity.class));
				finish();
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		startActivity(new Intent(ThankYouActivity.this,EmployeeSelectionActivity.class));
		finish();
	}
}
