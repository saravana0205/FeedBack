package com.istrides.ranjeetfeedback;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.istrides.ranjeetfeedback.Retrofit.Api;
import com.istrides.ranjeetfeedback.Retrofit.RetrofitService;
import com.istrides.ranjeetfeedback.common.BaseURL;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FloorSelectionActivity extends AppCompatActivity
{
	boolean doubleBackToExitPressedOnce = false;
	ConstraintLayout rootLayout;
	RecyclerView floorItem;
	AlertDialog alertDialog;
	int count = 0;
	AppCompatImageView refresh,logo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_selection);
		BaseURL.url = new SharedPreference(this).getBasePath();
		if(getSupportActionBar() != null) getSupportActionBar().hide();
		rootLayout = findViewById(R.id.rootLayout);
		logo = findViewById(R.id.logo);
		/*logo.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(count > 5)
				{
					count = 0;
					startActivity(new Intent(FloorSelectionActivity.this,BaseActivity.class));
				}
				count ++;
				new Handler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						count = 0;
					}
				}, 5000);

			}
		});*/
		refresh = findViewById(R.id.refresh);
		floorItem = findViewById(R.id.floorItem);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
		floorItem.setLayoutManager(linearLayoutManager);
		ViewGroup viewGroup = findViewById(android.R.id.content);
		View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_loader, viewGroup, false);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(dialogView);
		alertDialog = builder.create();
		alertDialog.show();
		refresh.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				alertDialog.show();
				getFloorList();
			}
		});
		getFloorList();
	}

	void getFloorList()
	{
		HashMap<String, Object> loginObject = new HashMap<>();
		loginObject.put( "apk_key", "floor_list" );
		Api api = RetrofitService.createService(Api.class);
		api.floor_list(loginObject ).enqueue(new Callback<FloorResponse>()
		{
			@Override
			public void onResponse(@NonNull Call<FloorResponse> call, @NonNull Response<FloorResponse> response )
			{
				alertDialog.dismiss();
				if( response.isSuccessful() )
				{
					FloorAdapter customAdapter = new FloorAdapter(FloorSelectionActivity.this, response.body());
					floorItem.setAdapter(customAdapter);
				}
			}

			@Override
			public void onFailure( @NonNull Call<FloorResponse> call, @NonNull Throwable t )
			{
				alertDialog.dismiss();
				t.printStackTrace();
			}
		} );
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
