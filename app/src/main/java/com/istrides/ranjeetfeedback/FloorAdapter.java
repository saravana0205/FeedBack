package com.istrides.ranjeetfeedback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.istrides.ranjeetfeedback.common.SharedPreference;

import java.util.ArrayList;
import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.MyViewHolder>
{
	Context context;
	FloorResponse floorResponses;

	public FloorAdapter(Context context, FloorResponse floorResponses)
	{
		this.context = context;
		this.floorResponses = floorResponses;

	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_floor_selection, parent, false);
		return new MyViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
	{
		FloorResponse.Output.FloorList floorList = floorResponses.getOutput().get(0).getFloorList().get(position);
		holder.textView.setText(floorList.getFloorName());
		holder.floorCard.setOnClickListener(v -> {
			holder.itemView.setEnabled(false);
			SharedPreference sharedPreference = new SharedPreference(context);
			sharedPreference.setFloorName(floorList.getFloorName());
			sharedPreference.setFloorId(Integer.parseInt(floorList.getFloorId()));
			holder.textView.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
			context.startActivity(new Intent(context, EmployeeSelectionActivity.class));
			((Activity)context).finish();
		});
	}

	@Override
	public int getItemCount()
	{
		return floorResponses.getOutput().get(0).getFloorList().size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		TextView textView;
		MaterialCardView floorCard;

		public MyViewHolder(View itemView)
		{
			super(itemView);
			textView = itemView.findViewById(R.id.floorName);
			floorCard = itemView.findViewById(R.id.floorCard);
		}
	}
}