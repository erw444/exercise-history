package com.erw.android.exercisehistory.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "exercise_names")
public class ExerciseName implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_name_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "exercise_name")
    private String exerciseName;

    public ExerciseName(){}

    @Ignore
    public ExerciseName(int id, String exerciseName){
        this.id = id;
        this.exerciseName = exerciseName;
    }

    @Ignore
    public ExerciseName (String exerciseName){
        this.exerciseName = exerciseName;
    }

    @Ignore
    private ExerciseName(Parcel in) {
        id = in.readInt();
        exerciseName = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getExerciseName() {
        return this.exerciseName;
    }

    public void setExerciseName(@NonNull String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String toString(){
        return getExerciseName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(exerciseName);
    }

    public static final Parcelable.Creator<ExerciseName> CREATOR
            = new Parcelable.Creator<ExerciseName>() {
        public ExerciseName createFromParcel(Parcel in) {
            return new ExerciseName(in);
        }

        public ExerciseName[] newArray(int size) {
            return new ExerciseName[size];
        }
    };


}
