package com.syahrani.spectaview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataCafe implements Parcelable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	protected DataCafe(Parcel in) {
		data = in.createTypedArrayList(DataItem.CREATOR);
		success = in.readInt();
		message = in.readString();
	}

	public static final Creator<DataCafe> CREATOR = new Creator<DataCafe>() {
		@Override
		public DataCafe createFromParcel(Parcel in) {
			return new DataCafe(in);
		}

		@Override
		public DataCafe[] newArray(int size) {
			return new DataCafe[size];
		}
	};

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeTypedList(data);
		dest.writeInt(success);
		dest.writeString(message);
	}
}