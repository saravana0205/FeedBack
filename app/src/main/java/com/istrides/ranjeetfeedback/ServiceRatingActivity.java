package com.istrides.ranjeetfeedback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.istrides.ranjeetfeedback.Retrofit.Api;
import com.istrides.ranjeetfeedback.Retrofit.RetrofitService;
import com.istrides.ranjeetfeedback.common.QuestionRating;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRatingActivity extends AppCompatActivity
{
	JsonArray arrayList = new JsonArray();
	TextView floorName, employeeName;
	TextInputEditText phoneNumber, email, name;
	RecyclerView questionItem;
	QuestionRating questionRating;
	AlertDialog alertDialog;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_rating);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#403B82")));
		MaterialButton submit = findViewById(R.id.submit);
		SharedPreference sharedPreference = new SharedPreference(this);
		questionRating = new QuestionRating(this);
		phoneNumber = findViewById(R.id.phoneNumber);
		email = findViewById(R.id.email);
		name = findViewById(R.id.name);
		floorName = findViewById(R.id.floorName);
		questionItem = findViewById(R.id.questionItem);
		employeeName = findViewById(R.id.employeeName);
		floorName.setText(sharedPreference.getFloorName());
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		questionItem.setLayoutManager(linearLayoutManager);
		ViewGroup viewGroup = findViewById(android.R.id.content);
		View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_loader, viewGroup, false);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(dialogView);
		alertDialog = builder.create();
		alertDialog.show();
		getQuestionList();
		employeeName.setText(sharedPreference.getEmployeeName());
		submit.setOnClickListener(v -> {
			if(phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().length() != 10)
			{
				phoneNumber.setError("Mobile number mandatory");
				phoneNumber.requestFocus();
				InputMethodManager inputMethodManager = (InputMethodManager) ServiceRatingActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
			}
			else if(name.getText().toString().isEmpty())
			{
				name.setError("Name is mandatory");
				name.requestFocus();
				InputMethodManager inputMethodManager = (InputMethodManager) ServiceRatingActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
			}
			else
			{
				for(i = 1; i < 6; i++)
				{
					JsonObject ratingMap = new JsonObject();
					ratingMap.addProperty("question_id", "" + i);
					if(questionRating.getRating("" + i) == -1)
					{
						Snackbar.make(v, "Rate All the Questions ", Snackbar.LENGTH_LONG).show();
						arrayList = new JsonArray();
						break;
					}
					ratingMap.addProperty("question_rating", questionRating.getRating("" + i));
					arrayList.add(ratingMap);
				}
				if(i == 6)
				{
					alertDialog.show();
					JsonObject map = new JsonObject();
					map.addProperty("FloorId", "" + sharedPreference.getFloorId());
					map.addProperty("EmployeeId", "" + sharedPreference.getEmployeeId());
					map.addProperty("customer_name", "" + name.getText().toString());
					map.addProperty("customer_mobile", "" + phoneNumber.getText().toString());
					map.addProperty("customer_email", "" + email.getText().toString());
					map.addProperty("question_details", "" + arrayList);
					sendRating(map);
				}
			}

		});
	}

	void sendRating(JsonObject ratingMap)
	{
		Api api = RetrofitService.createService(Api.class);
		api.customer_rating(ratingMap).enqueue(new Callback<QuestionResponse>()
		{
			@Override
			public void onResponse(@NonNull Call<QuestionResponse> call, @NonNull Response<QuestionResponse> response)
			{
				alertDialog.dismiss();
				if(response.isSuccessful())
				{
					if(response.body().getOutput().get(0).getStatus().equals("success"))
					{
						startActivity(new Intent(ServiceRatingActivity.this, ThankYouActivity.class));
					}
				}
			}

			@Override
			public void onFailure(@NonNull Call<QuestionResponse> call, @NonNull Throwable t)
			{
				alertDialog.dismiss();
				t.printStackTrace();
			}
		});
	}

	void getQuestionList()
	{
		HashMap<String, Object> loginObject = new HashMap<>();
		loginObject.put("apk_key", "question_list");
		Api api = RetrofitService.createService(Api.class);
		api.question_list(loginObject).enqueue(new Callback<QuestionResponse>()
		{
			@Override
			public void onResponse(@NonNull Call<QuestionResponse> call, @NonNull Response<QuestionResponse> response)
			{
				alertDialog.dismiss();
				if(response.isSuccessful())
				{
					QuestionAdapter customAdapter = new QuestionAdapter(ServiceRatingActivity.this, response.body());
					questionItem.setAdapter(customAdapter);
				}
			}

			@Override
			public void onFailure(@NonNull Call<QuestionResponse> call, @NonNull Throwable t)
			{
				alertDialog.dismiss();
				t.printStackTrace();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		if(item.getItemId() == android.R.id.home) finish();
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		questionRating.clear();
	}
}
