package com.istrides.ranjeetfeedback.Retrofit;

import com.google.gson.JsonObject;
import com.istrides.ranjeetfeedback.EmployeeResponse;
import com.istrides.ranjeetfeedback.FloorResponse;
import com.istrides.ranjeetfeedback.QuestionAdapter;
import com.istrides.ranjeetfeedback.QuestionResponse;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api
{

	@POST("floor-list")
	Call<FloorResponse> floor_list(@Body HashMap<String, Object> body);

	@POST("employee-list")
	Call<EmployeeResponse> employee_list(@Body HashMap<String, Object> body);

	@POST("password-validation")
	Call<EmployeeResponse> password_validation(@Body HashMap<String, Object> body);

	@POST("question-list")
	Call<QuestionResponse> question_list(@Body HashMap<String, Object> body);

	@POST("customer-rating")
	Call<QuestionResponse> customer_rating(@Body JsonObject body);

}
