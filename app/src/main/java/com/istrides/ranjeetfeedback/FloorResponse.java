package com.istrides.ranjeetfeedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FloorResponse
{
	@SerializedName("Output")
	@Expose
	private List<Output> output = null;

	public List<Output> getOutput()
	{
		return output;
	}

	public void setOutput(List<Output> output)
	{
		this.output = output;
	}

	public class Output
	{

		@SerializedName("status")
		@Expose
		private String status;
		@SerializedName("message")
		@Expose
		private String message;
		@SerializedName("floor_list")
		@Expose
		private List<FloorList> floorList = null;

		public String getStatus()
		{
			return status;
		}

		public void setStatus(String status)
		{
			this.status = status;
		}

		public String getMessage()
		{
			return message;
		}

		public void setMessage(String message)
		{
			this.message = message;
		}

		public List<FloorList> getFloorList()
		{
			return floorList;
		}

		public void setFloorList(List<FloorList> floorList)
		{
			this.floorList = floorList;
		}

		public class FloorList
		{

			@SerializedName("FloorId")
			@Expose
			private String floorId;
			@SerializedName("FloorName")
			@Expose
			private String floorName;
			@SerializedName("Status")
			@Expose
			private String status;
			@SerializedName("CreatedTime")
			@Expose
			private String createdTime;

			public String getFloorId()
			{
				return floorId;
			}

			public void setFloorId(String floorId)
			{
				this.floorId = floorId;
			}

			public String getFloorName()
			{
				return floorName;
			}

			public void setFloorName(String floorName)
			{
				this.floorName = floorName;
			}

			public String getStatus()
			{
				return status;
			}

			public void setStatus(String status)
			{
				this.status = status;
			}

			public String getCreatedTime()
			{
				return createdTime;
			}

			public void setCreatedTime(String createdTime)
			{
				this.createdTime = createdTime;
			}

		}

	}

}

