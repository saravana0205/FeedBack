package com.istrides.ranjeetfeedback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.istrides.ranjeetfeedback.Retrofit.Api;
import com.istrides.ranjeetfeedback.Retrofit.RetrofitService;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>
{
	Context context;
	EmployeeResponse floorResponses;
	AlertDialog alertDialog;
	View viewPos;
	EmployeeResponse.Output.EmployeeList floorList;

	public EmployeeAdapter(Context context, EmployeeResponse floorResponses)
	{
		this.context = context;
		this.floorResponses = floorResponses;

	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_list, parent, false);
		return new MyViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
	{
		floorList = floorResponses.getOutput().get(0).getEmployeeList().get(position);

		holder.employeeName.setText(floorList.getEmployeeName());
		holder.employeeId.setText(floorList.getEmployeeCode());
		holder.employeeCard.setOnClickListener(v -> {
			View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_enter_password, holder.viewGroup, false);
			MaterialButton submit = dialogView.findViewById(R.id.submit);
			TextInputEditText password = dialogView.findViewById(R.id.password);
			viewPos = dialogView.findViewById(R.id.myCoordinatorLayout);
			TextView userName = dialogView.findViewById(R.id.userName);
			userName.setText(floorList.getEmployeeName());
			password.requestFocus();
			AppCompatImageView close = dialogView.findViewById(R.id.close);
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setView(dialogView);
			alertDialog = builder.create();
			alertDialog.show();
			InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
			submit.setOnClickListener(v12 -> {
				if(alertDialog != null && alertDialog.isShowing())
				{
					if(password.getText().length() > 3)
					{
						((Loader)context).loader(true);
						HashMap<String, Object> loginObject = new HashMap<>();
						loginObject.put("EmployeeCode", floorList.getEmployeeCode());
						loginObject.put("Password", password.getText().toString());
						getEmployeeList(loginObject);
					}
					else
					{
						Snackbar.make(viewPos, "Enter a Valid Password", Snackbar.LENGTH_LONG).setAction("Action", null).show();
					}
				}
			});
			close.setOnClickListener(v1 -> {
				alertDialog.dismiss();
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v1.getWindowToken(), 0);
			});
		});
	}

	@Override
	public int getItemCount()
	{
		return floorResponses.getOutput().get(0).getEmployeeList().size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		TextView employeeName, employeeId;
		MaterialCardView employeeCard;
		ViewGroup viewGroup;

		public MyViewHolder(View itemView)
		{
			super(itemView);
			viewGroup = itemView.findViewById(android.R.id.content);
			employeeName = itemView.findViewById(R.id.employeeName);
			employeeId = itemView.findViewById(R.id.employeeId);
			employeeCard = itemView.findViewById(R.id.employeeCard);
		}
	}

	void getEmployeeList(HashMap<String, Object> loginObject)
	{
		Api api = RetrofitService.createService(Api.class);
		api.password_validation(loginObject).enqueue(new Callback<EmployeeResponse>()
		{
			@Override
			public void onResponse(@NonNull Call<EmployeeResponse> call, @NonNull Response<EmployeeResponse> response)
			{
				if(response.isSuccessful())
				{
					((Loader)context).loader(false);
					Snackbar.make(viewPos, response.body().getOutput().get(0).getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
					if(response.body().getOutput().get(0).getStatus().equals("success"))
					{
						if(alertDialog != null && alertDialog.isShowing()) alertDialog.dismiss();
						SharedPreference sharedPreference = new SharedPreference(context);
						sharedPreference.setEmployeeName(floorList.getEmployeeName());
						sharedPreference.setEmployeeId(Integer.parseInt(floorList.getEmployeeId()));
						context.startActivity(new Intent(context, ServiceRatingActivity.class));
					}
				}
			}

			@Override
			public void onFailure(@NonNull Call<EmployeeResponse> call, @NonNull Throwable t)
			{
				((Loader)context).loader(false);
				t.printStackTrace();
			}
		});
	}
}