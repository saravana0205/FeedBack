package com.istrides.ranjeetfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceRatingActivity extends AppCompatActivity implements View.OnClickListener
{
	ConstraintLayout sl1, sl2, sl3, sl4, sl5,worstLayout,badLayout,averageLayout,goodLayout,awesomeLayout;
	AppCompatImageView worstTick, badTick, averageTick, goodTick, awesomeTick;
	TextView worst,bad,average,good, awesome, a0, a1,a2,a3,a4,a5,a6,a7,a8,a9,a10 ;
	List<HashMap<String, String>> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_rating);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#403B82")));
		MaterialButton submit = findViewById(R.id.submit);
		a0 = findViewById(R.id.a0);
		a1 = findViewById(R.id.a1);
		a2 = findViewById(R.id.a2);
		a3 = findViewById(R.id.a3);
		a4 = findViewById(R.id.a4);
		a5 = findViewById(R.id.a5);
		a6 = findViewById(R.id.a6);
		a7 = findViewById(R.id.a7);
		a8 = findViewById(R.id.a8);
		a9 = findViewById(R.id.a9);
		a10 = findViewById(R.id.a10);
		a0.setOnClickListener(this);
		a1.setOnClickListener(this);
		a2.setOnClickListener(this);
		a3.setOnClickListener(this);
		a4.setOnClickListener(this);
		a5.setOnClickListener(this);
		a6.setOnClickListener(this);
		a7.setOnClickListener(this);
		a8.setOnClickListener(this);
		a9.setOnClickListener(this);
		a10.setOnClickListener(this);
		worst = findViewById(R.id.worst);
		bad = findViewById(R.id.bad);
		average = findViewById(R.id.average);
		good = findViewById(R.id.good);
		awesome = findViewById(R.id.awesome);
		worstTick = findViewById(R.id.worstTick);
		badTick = findViewById(R.id.badTick);
		averageTick = findViewById(R.id.averageTick);
		goodTick = findViewById(R.id.goodTick);
		awesomeTick = findViewById(R.id.awesomeTick);
		worstLayout = findViewById(R.id.worstLayout);
		badLayout = findViewById(R.id.badLayout);
		averageLayout = findViewById(R.id.averageLayout);
		goodLayout = findViewById(R.id.goodLayout);
		awesomeLayout = findViewById(R.id.awesomeLayout);
		/*RecyclerView recyclerView = findViewById(R.id.recyclerView);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
		linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		CustomAdapter customAdapter = new CustomAdapter(this, getValue());
		recyclerView.setAdapter(customAdapter);*/
		sl1 = findViewById(R.id.sl1);
		sl2 = findViewById(R.id.sl2);
		sl3 = findViewById(R.id.sl3);
		sl4 = findViewById(R.id.sl4);
		sl5 = findViewById(R.id.sl5);
		sl1.setOnClickListener(this);
		sl2.setOnClickListener(this);
		sl3.setOnClickListener(this);
		sl4.setOnClickListener(this);
		sl5.setOnClickListener(this);
		submit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(ServiceRatingActivity.this, ThankYouActivity.class));
			}
		});
	}

	private List getValue()
	{
//		ArrayList numbers = new ArrayList<>(Arrays.asList("0","1", "2", "3", "4", "5", "6", "7","8","9","10"));
		for(int i=0;i<=10;i++)
		{
			HashMap<String,String> numbers = new HashMap<>();
			numbers.put("value", "" + i);
			numbers.put("active", "0");
			arrayList.add(numbers);
		}
		Log.d("s2s", "prob " + arrayList.toString());
		return arrayList;
	}

	@Override
	public void onClick(View v)
	{
		a0.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a1.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a2.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a3.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a4.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a5.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a6.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a7.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a8.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a9.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a10.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_background));
		a0.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a1.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a2.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a3.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a4.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a5.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a6.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a7.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a8.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a9.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		a10.setTextColor(ContextCompat.getColor(this,R.color.themeColor));
		switch(v.getId())
		{
			case R.id.a0:
				a0.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a0.setTextColor(Color.WHITE);
				break;
			case R.id.a1:
				a1.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a1.setTextColor(Color.WHITE);
				break;
			case R.id.a2:
				a2.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a2.setTextColor(Color.WHITE);
				break;
			case R.id.a3:
				a3.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a3.setTextColor(Color.WHITE);
				break;
			case R.id.a4:
				a4.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a4.setTextColor(Color.WHITE);
				break;
			case R.id.a5:
				a5.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a5.setTextColor(Color.WHITE);
				break;
			case R.id.a6:
				a6.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a6.setTextColor(Color.WHITE);
				break;
			case R.id.a7:
				a7.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a7.setTextColor(Color.WHITE);
				break;
			case R.id.a8:
				a8.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a8.setTextColor(Color.WHITE);
				break;
			case R.id.a9:
				a9.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a9.setTextColor(Color.WHITE);
				break;
			case R.id.a10:
				a10.setBackground(ContextCompat.getDrawable(this,R.drawable.rating_selected_border));
				a10.setTextColor(Color.WHITE);
				break;
		}
		worstLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.unselected_border));
		badLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.unselected_border));
		averageLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.unselected_border));
		goodLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.unselected_border));
		awesomeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.unselected_border));
		worst.setTextColor(Color.BLACK);
		bad.setTextColor(Color.BLACK);
		average.setTextColor(Color.BLACK);
		good.setTextColor(Color.BLACK);
		awesome.setTextColor(Color.BLACK);
		switch(v.getId())
		{
			case R.id.sl1:
				worstLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_border));
				worst.setTextColor(Color.WHITE);
				break;
			case R.id.sl2:
				badLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_border));
				bad.setTextColor(Color.WHITE);
				break;
			case R.id.sl3:
				averageLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_border));
				average.setTextColor(Color.WHITE);
				break;
			case R.id.sl4:
				goodLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_border));
				good.setTextColor(Color.WHITE);
				break;
			case R.id.sl5:
				awesomeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_border));
				awesome.setTextColor(Color.WHITE);
				break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		if(item.getItemId() == android.R.id.home) finish();
		return super.onOptionsItemSelected(item);
	}
}
