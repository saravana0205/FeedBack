package com.istrides.ranjeetfeedback;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
	List<HashMap<String, String>> personNames = new ArrayList<>();
	Context context;
	int perivousSelect = -1 ;

	public CustomAdapter(Context context, List personNames)
	{
		this.context = context;
		this.personNames = personNames;
	}


	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating_number, parent, false);
		MyViewHolder vh = new MyViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		holder.name.setText(personNames.get(position).get("value"));
		holder.itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if(perivousSelect >= 0)
				{
					personNames.get(perivousSelect).put("active", "0");
				}
				personNames.get(position).put("active", "1");
				notifyDataSetChanged();
				perivousSelect = position;
			}
		});
		if(personNames.get(position).get("active").equals("0"))
		{
			holder.rootLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rating_background));
			holder.name.setTextColor(Color.BLACK);
		}
		else if(personNames.get(position).get("active").equals("1"))
		{
			holder.rootLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.themeColor));
			holder.name.setTextColor(Color.WHITE);
		}
	}

	@Override
	public int getItemCount()
	{
		return personNames.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		TextView name;
		ConstraintLayout rootLayout;

		public MyViewHolder(View itemView)
		{
			super(itemView);
			name = itemView.findViewById(R.id.ratingNumber);
			rootLayout = itemView.findViewById(R.id.rootLayout);
		}
	}
}
