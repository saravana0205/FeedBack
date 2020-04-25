package com.istrides.ranjeetfeedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResponse
{
	@SerializedName("Output")
	@Expose
	private List<Output> output = null;

	public List<Output> getOutput() {
		return output;
	}

	public void setOutput(List<Output> output) {
		this.output = output;
	}

	public class Output {

		@SerializedName("status")
		@Expose
		private String status;
		@SerializedName("message")
		@Expose
		private String message;
		@SerializedName("question_list")
		@Expose
		private List<QuestionList> questionList = null;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<QuestionList> getQuestionList() {
			return questionList;
		}

		public void setQuestionList(List<QuestionList> questionList) {
			this.questionList = questionList;
		}

		public class QuestionList {

			@SerializedName("Questions_id")
			@Expose
			private String questionsId;
			@SerializedName("Questions")
			@Expose
			private String questions;
			@SerializedName("Status")
			@Expose
			private String status;

			public String getQuestionsId() {
				return questionsId;
			}

			public void setQuestionsId(String questionsId) {
				this.questionsId = questionsId;
			}

			public String getQuestions() {
				return questions;
			}

			public void setQuestions(String questions) {
				this.questions = questions;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

		}

	}
}
