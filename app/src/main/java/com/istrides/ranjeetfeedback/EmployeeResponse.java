package com.istrides.ranjeetfeedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse
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
		@SerializedName("employee_list")
		@Expose
		private List<EmployeeList> employeeList = null;

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

		public List<EmployeeList> getEmployeeList()
		{
			return employeeList;
		}

		public void setEmployeeList(List<EmployeeList> employeeList)
		{
			this.employeeList = employeeList;
		}

		public class EmployeeList
		{

			@SerializedName("EmployeeId")
			@Expose
			private String employeeId;
			@SerializedName("EmployeeName")
			@Expose
			private String employeeName;
			@SerializedName("EmployeeCode")
			@Expose
			private String employeeCode;
			@SerializedName("ContactNo")
			@Expose
			private String contactNo;
			@SerializedName("EmailId")
			@Expose
			private Object emailId;
			@SerializedName("Address")
			@Expose
			private Object address;
			@SerializedName("FloorName")
			@Expose
			private String floorName;
			@SerializedName("DesignationName")
			@Expose
			private String designationName;
			@SerializedName("Status")
			@Expose
			private String status;
			@SerializedName("CreatedTime")
			@Expose
			private String createdTime;

			public String getEmployeeId()
			{
				return employeeId;
			}

			public void setEmployeeId(String employeeId)
			{
				this.employeeId = employeeId;
			}

			public String getEmployeeName()
			{
				return employeeName;
			}

			public void setEmployeeName(String employeeName)
			{
				this.employeeName = employeeName;
			}

			public String getEmployeeCode()
			{
				return employeeCode;
			}

			public void setEmployeeCode(String employeeCode)
			{
				this.employeeCode = employeeCode;
			}

			public String getContactNo()
			{
				return contactNo;
			}

			public void setContactNo(String contactNo)
			{
				this.contactNo = contactNo;
			}

			public Object getEmailId()
			{
				return emailId;
			}

			public void setEmailId(Object emailId)
			{
				this.emailId = emailId;
			}

			public Object getAddress()
			{
				return address;
			}

			public void setAddress(Object address)
			{
				this.address = address;
			}

			public String getFloorName()
			{
				return floorName;
			}

			public void setFloorName(String floorName)
			{
				this.floorName = floorName;
			}

			public String getDesignationName()
			{
				return designationName;
			}

			public void setDesignationName(String designationName)
			{
				this.designationName = designationName;
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
