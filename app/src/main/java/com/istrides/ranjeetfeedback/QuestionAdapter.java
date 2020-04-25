package com.istrides.ranjeetfeedback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.istrides.ranjeetfeedback.common.QuestionRating;
import com.istrides.ranjeetfeedback.common.SharedPreference;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>
{
	Context context;
	QuestionResponse floorResponses;
	int currentPos;
	TextView a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
	QuestionRating questionRating;
	int value;

	public QuestionAdapter(Context context, QuestionResponse floorResponses)
	{
		this.context = context;
		this.floorResponses = floorResponses;
		questionRating = new QuestionRating(context);
	}


	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_r_layout, parent, false);
		return new MyViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
	{
		currentPos = position;
		QuestionResponse.Output.QuestionList floorList = floorResponses.getOutput().get(0).getQuestionList().get(position);
		holder.question.setText(floorList.getQuestions());
		holder.a0.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a1.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a2.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a3.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a4.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a5.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a6.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a7.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a8.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a9.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});
		holder.a10.setOnClickListener(v -> {
			onClikItem(holder,v,position);
		});

	}

	@Override
	public int getItemCount()
	{
		return floorResponses.getOutput().get(0).getQuestionList().size();
	}

	void onClikItem(MyViewHolder holder,View v,int position)
	{
		holder.a0.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a1.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a2.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a3.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a4.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a5.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a6.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a7.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a8.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a9.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a10.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
		holder.a0.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a1.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a2.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a3.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a4.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a5.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a6.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a7.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a8.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a9.setTextColor(ContextCompat.getColor(context,R.color.themeColor));
		holder.a10.setTextColor(ContextCompat.getColor(context,R.color.themeColor));

		switch(v.getId())
		{
			case R.id.a0:
				value = 0;
				holder.a0.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a0.setTextColor(Color.WHITE);
				break;
			case R.id.a1:
				value = 1;
				holder.a1.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a1.setTextColor(Color.WHITE);
				break;
			case R.id.a2:
				value = 2;
				holder.a2.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a2.setTextColor(Color.WHITE);
				break;
			case R.id.a3:
				value = 3;
				holder.a3.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a3.setTextColor(Color.WHITE);
				break;
			case R.id.a4:
				value = 4;
				holder.a4.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a4.setTextColor(Color.WHITE);
				break;
			case R.id.a5:
				value = 5;
				holder.a5.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a5.setTextColor(Color.WHITE);
				break;
			case R.id.a6:
				value = 6;
				holder.a6.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a6.setTextColor(Color.WHITE);
				break;
			case R.id.a7:
				value = 7;
				holder.a7.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a7.setTextColor(Color.WHITE);
				break;
			case R.id.a8:
				value = 8;
				holder.a8.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a8.setTextColor(Color.WHITE);
				break;
			case R.id.a9:
				value = 9;
				holder.a9.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a9.setTextColor(Color.WHITE);
				break;
			case R.id.a10:
				value = 10;
				holder.a10.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_selected_border));
				holder.a10.setTextColor(Color.WHITE);
				break;
		}
		questionRating.setRating(""+(position+1),value);
	}


	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		TextView question;
		MaterialCardView questionCard;
		TextView a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
		public MyViewHolder(View itemView)
		{
			super(itemView);
			questionCard = itemView.findViewById(R.id.questionCard);
			question = itemView.findViewById(R.id.question);
			a0 = itemView.findViewById(R.id.a0);
			a1 = itemView.findViewById(R.id.a1);
			a2 = itemView.findViewById(R.id.a2);
			a3 = itemView.findViewById(R.id.a3);
			a4 = itemView.findViewById(R.id.a4);
			a5 = itemView.findViewById(R.id.a5);
			a6 = itemView.findViewById(R.id.a6);
			a7 = itemView.findViewById(R.id.a7);
			a8 = itemView.findViewById(R.id.a8);
			a9 = itemView.findViewById(R.id.a9);
			a10 = itemView.findViewById(R.id.a10);
		}
	}
}