package aim.helmi.bootcamp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
	private String image;
	private String categoryName;
	private String timeStamp;
	private int releaseYear;
	private String edition;
	private String discFormatName;
	private String ratingName;
	private String synopsis;
	private int movieId;
	private String title;
	private int numberDiscs;
	private int runningTime;
	private String aspectRatioName;
	private String viewingFormatName;
	private int status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setReleaseYear(int releaseYear){
		this.releaseYear = releaseYear;
	}

	public int getReleaseYear(){
		return releaseYear;
	}

	public void setEdition(String edition){
		this.edition = edition;
	}

	public String getEdition(){
		return edition;
	}

	public void setDiscFormatName(String discFormatName){
		this.discFormatName = discFormatName;
	}

	public String getDiscFormatName(){
		return discFormatName;
	}

	public void setRatingName(String ratingName){
		this.ratingName = ratingName;
	}

	public String getRatingName(){
		return ratingName;
	}

	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}

	public String getSynopsis(){
		return synopsis;
	}

	public void setMovieId(int movieId){
		this.movieId = movieId;
	}

	public int getMovieId(){
		return movieId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setNumberDiscs(int numberDiscs){
		this.numberDiscs = numberDiscs;
	}

	public int getNumberDiscs(){
		return numberDiscs;
	}

	public void setRunningTime(int runningTime){
		this.runningTime = runningTime;
	}

	public int getRunningTime(){
		return runningTime;
	}

	public void setAspectRatioName(String aspectRatioName){
		this.aspectRatioName = aspectRatioName;
	}

	public String getAspectRatioName(){
		return aspectRatioName;
	}

	public void setViewingFormatName(String viewingFormatName){
		this.viewingFormatName = viewingFormatName;
	}

	public String getViewingFormatName(){
		return viewingFormatName;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Movie{" +
			"image = '" + image + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",time_stamp = '" + timeStamp + '\'' + 
			",release_year = '" + releaseYear + '\'' + 
			",edition = '" + edition + '\'' + 
			",disc_format_name = '" + discFormatName + '\'' + 
			",rating_name = '" + ratingName + '\'' + 
			",synopsis = '" + synopsis + '\'' + 
			",movie_id = '" + movieId + '\'' + 
			",title = '" + title + '\'' + 
			",number_discs = '" + numberDiscs + '\'' + 
			",running_time = '" + runningTime + '\'' + 
			",aspect_ratio_name = '" + aspectRatioName + '\'' + 
			",viewing_format_name = '" + viewingFormatName + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeString(this.categoryName);
		dest.writeString(this.timeStamp);
		dest.writeInt(this.releaseYear);
		dest.writeString(this.edition);
		dest.writeString(this.discFormatName);
		dest.writeString(this.ratingName);
		dest.writeString(this.synopsis);
		dest.writeInt(this.movieId);
		dest.writeString(this.title);
		dest.writeInt(this.numberDiscs);
		dest.writeInt(this.runningTime);
		dest.writeString(this.aspectRatioName);
		dest.writeString(this.viewingFormatName);
		dest.writeInt(this.status);
	}

	public Movie() {
	}

	protected Movie(Parcel in) {
		this.image = in.readString();
		this.categoryName = in.readString();
		this.timeStamp = in.readString();
		this.releaseYear = in.readInt();
		this.edition = in.readString();
		this.discFormatName = in.readString();
		this.ratingName = in.readString();
		this.synopsis = in.readString();
		this.movieId = in.readInt();
		this.title = in.readString();
		this.numberDiscs = in.readInt();
		this.runningTime = in.readInt();
		this.aspectRatioName = in.readString();
		this.viewingFormatName = in.readString();
		this.status = in.readInt();
	}

	public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
		@Override
		public Movie createFromParcel(Parcel source) {
			return new Movie(source);
		}

		@Override
		public Movie[] newArray(int size) {
			return new Movie[size];
		}
	};
}
