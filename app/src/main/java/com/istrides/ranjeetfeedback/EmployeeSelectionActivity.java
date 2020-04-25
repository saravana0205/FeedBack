package com.istrides.ranjeetfeedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.istrides.ranjeetfeedback.Retrofit.Api;
import com.istrides.ranjeetfeedback.Retrofit.RetrofitService;
import com.istrides.ranjeetfeedback.common.BaseURL;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeSelectionActivity extends AppCompatActivity implements Loader
{
	ConstraintLayout rootLayout;
	TextView floorName, noMatchFound;
	AppCompatEditText search_bar;
	RecyclerView employeeItem;
	AlertDialog alertDialog;
	AppCompatImageView refresh;
	SharedPreference sharedPreference;
	MaterialAlertDialogBuilder builder;
	boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_selection);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#403B82")));
		rootLayout = findViewById(R.id.rootLayout);
		sharedPreference = new SharedPreference(this);
		floorName = findViewById(R.id.floorName);
		refresh = findViewById(R.id.refresh);
		noMatchFound = findViewById(R.id.noMatchFound);
		search_bar = findViewById(R.id.search_bar);
		builder = new MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme);
		builder.setTitle("Alert!");
		builder.setMessage("Do you want to go to floor selection?");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						startActivity(new Intent(EmployeeSelectionActivity.this, FloorSelectionActivity.class).putExtra("key", "1"));
						finish();
					}
				});
		builder.setNegativeButton( "CLOSE", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick( DialogInterface dialog, int which )
			{
			}
		} );
				ViewGroup viewGroup = findViewById(android.R.id.content);
		View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_loader, viewGroup, false);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(dialogView);
		alertDialog = builder.create();
		search_bar.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
			}

			@Override
			public void afterTextChanged(Editable s)
			{
				if(s.length() > 2)
				{
					alertDialog.show();
					HashMap<String, Object> loginObject = new HashMap<>();
					loginObject.put("apk_key", "employee_list");
					loginObject.put("floor_name", "" + sharedPreference.getFloorId());
					loginObject.put("search_key", s.toString());
					getEmployeeList(loginObject);
				}
				if(s.length() == 0)
				{
					alertDialog.show();
					HashMap<String, Object> loginObject = new HashMap<>();
					loginObject.put("apk_key", "employee_list");
					loginObject.put("floor_name", "" + sharedPreference.getFloorId());
					loginObject.put("search_key", "");
					getEmployeeList(loginObject);
				}
			}
		});
		refresh.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				alertDialog.show();
				search_bar.getText().clear();
				HashMap<String, Object> loginObject = new HashMap<>();
				loginObject.put("apk_key", "employee_list");
				loginObject.put("floor_name", "" + sharedPreference.getFloorId());
				loginObject.put("search_key", "");
				getEmployeeList(loginObject);
			}
		});
		employeeItem = findViewById(R.id.employeeItem);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
		employeeItem.setLayoutManager(linearLayoutManager);

		BaseURL.url = sharedPreference.getBasePath();
		floorName.setText(sharedPreference.getFloorName());
		alertDialog.show();
		HashMap<String, Object> loginObject = new HashMap<>();
		loginObject.put("apk_key", "employee_list");
		loginObject.put("floor_name", "" + sharedPreference.getFloorId());
		loginObject.put("search_key", "");
		getEmployeeList(loginObject);

	}

	void getEmployeeList(HashMap<String, Object> loginObject)
	{
		Api api = RetrofitService.createService(Api.class);
		api.employee_list(loginObject).enqueue(new Callback<EmployeeResponse>()
		{
			@Override
			public void onResponse(@NonNull Call<EmployeeResponse> call, @NonNull Response<EmployeeResponse> response)
			{
				alertDialog.dismiss();
				if(response.isSuccessful())
				{
					if(response.body().getOutput().get(0).getEmployeeList().size() == 0)
					{
						noMatchFound.setVisibility(View.VISIBLE);
						employeeItem.setVisibility(View.GONE);
					}
					else
					{
						noMatchFound.setVisibility(View.GONE);
						employeeItem.setVisibility(View.VISIBLE);
						EmployeeAdapter customAdapter = new EmployeeAdapter(EmployeeSelectionActivity.this, response.body());
						employeeItem.setAdapter(customAdapter);
					}

				}
			}

			@Override
			public void onFailure(@NonNull Call<EmployeeResponse> call, @NonNull Throwable t)
			{
				alertDialog.dismiss();
				t.printStackTrace();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.change_floor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		if(item.getItemId() == R.id.changeFloor)
		{
			builder.show();
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

	@Override
	public void loader(boolean isStart)
	{
		if(isStart) alertDialog.show();
		else alertDialog.dismiss();
	}
}
