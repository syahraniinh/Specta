package com.syahrani.spectaview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {
	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	@SerializedName("link_foto")
	private String linkFoto;

	@SerializedName("nama_cafe")
	private String namaCafe;

	@SerializedName("jam_operasional")
	private String jamOperasional;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	@SerializedName("rating_cafe")
	private String ratingCafe;

	@SerializedName("lokasi_cafe")
	private String lokasiCafe;

	@SerializedName("tentang_cafe")
	private String tentangCafe;

	protected DataItem(Parcel in) {
		linkFoto = in.readString();
		namaCafe = in.readString();
		jamOperasional = in.readString();
		v = in.readInt();
		id = in.readString();
		ratingCafe = in.readString();
		lokasiCafe = in.readString();
		tentangCafe = in.readString();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public void setLinkFoto(String linkFoto){
		this.linkFoto = linkFoto;
	}

	public String getLinkFoto(){
		return linkFoto;
	}

	public void setNamaCafe(String namaCafe){
		this.namaCafe = namaCafe;
	}

	public String getNamaCafe(){
		return namaCafe;
	}

	public void setJamOperasional(String jamOperasional){
		this.jamOperasional = jamOperasional;
	}

	public String getJamOperasional(){
		return jamOperasional;
	}

	public void setV(int v){
		this.v = v;
	}

	public int getV(){
		return v;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRatingCafe(String ratingCafe){
		this.ratingCafe = ratingCafe;
	}

	public String getRatingCafe(){
		return ratingCafe;
	}

	public void setLokasiCafe(String lokasiCafe){
		this.lokasiCafe = lokasiCafe;
	}

	public String getLokasiCafe(){
		return lokasiCafe;
	}

	public void setTentangCafe(String tentangCafe){
		this.tentangCafe = tentangCafe;
	}

	public String getTentangCafe(){
		return tentangCafe;
	}
	public int getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(linkFoto);
		dest.writeString(namaCafe);
		dest.writeString(jamOperasional);
		dest.writeInt(v);
		dest.writeString(id);
		dest.writeString(ratingCafe);
		dest.writeString(lokasiCafe);
		dest.writeString(tentangCafe);
	}
}