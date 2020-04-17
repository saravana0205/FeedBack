package com.istrides.ranjeetfeedback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EmployeeSelectionActivity extends AppCompatActivity
{
	MaterialCardView firstLayout;
	AlertDialog alertDialog;
//	AppCompatImageView floorSelection;
	ConstraintLayout rootLayout;
	boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_selection);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#403B82")));
		rootLayout = findViewById(R.id.rootLayout);
		firstLayout = findViewById(R.id.firstLayout);
		firstLayout.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ViewGroup viewGroup = findViewById(android.R.id.content);
				View dialogView = LayoutInflater.from(EmployeeSelectionActivity.this).inflate(R.layout.dialog_enter_password, viewGroup, false);
				MaterialButton submit = dialogView.findViewById(R.id.submit);
				TextInputEditText password = dialogView.findViewById(R.id.password);
				password.requestFocus();
				AppCompatImageView close = dialogView.findViewById(R.id.close);
				AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeSelectionActivity.this);
				builder.setView(dialogView);
				alertDialog = builder.create();
				alertDialog.show();
				InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.toggleSoftInputFromWindow(firstLayout.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
				submit.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if(alertDialog != null &&alertDialog.isShowing())
							alertDialog.dismiss();
						startActivity(new Intent(EmployeeSelectionActivity.this, ServiceRatingActivity.class));
					}
				});
				close.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						alertDialog.dismiss();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					}
				});

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.change_floor, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( @NonNull MenuItem item )
	{
		if( item.getItemId() == R.id.changeFloor )
		{
			if(alertDialog != null &&alertDialog.isShowing())
				alertDialog.dismiss();
			startActivity(new Intent(EmployeeSelectionActivity.this,FloorSelectionActivity.class).putExtra("key","1"));
		}
		return true;
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
