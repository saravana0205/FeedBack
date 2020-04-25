package com.istrides.ranjeetfeedback.Retrofit;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.istrides.ranjeetfeedback.FloorSelectionActivity;
import com.istrides.ranjeetfeedback.common.BaseURL;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService extends Application
{

	private static Retrofit retrofit = new Retrofit.Builder().client(getClient()).baseUrl(BaseURL.url).addConverterFactory(GsonConverterFactory.create()).build();

	public static <S> S createService(Class<S> serviceClass)
	{
		return retrofit.create(serviceClass);
	}

	private static OkHttpClient getClient()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder().addInterceptor(interceptor).build();
	}
}
