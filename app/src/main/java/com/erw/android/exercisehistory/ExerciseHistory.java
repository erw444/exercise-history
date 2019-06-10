package com.erw.android.exercisehistory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ExerciseHistory implements Parcelable {
    private String exerciseName;
    private String sets;
    private String reps;
    private boolean didPass;
    private String exerciseDate;

    public ExerciseHistory(){

    }

    public ExerciseHistory(String exerciseName, String sets, String reps, boolean didPass, String exerciseDate) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.didPass = didPass;
        this.exerciseDate = exerciseDate;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public boolean isDidPass() {
        return didPass;
    }

    public void setDidPass(boolean didPass) {
        this.didPass = didPass;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(exerciseName);
        parcel.writeString(sets);
        parcel.writeString(reps);
        parcel.writeInt(didPass? 1: 0);
        parcel.writeString(exerciseDate);
    }

    public static final Parcelable.Creator<ExerciseHistory> CREATOR
            = new Parcelable.Creator<ExerciseHistory>() {
        public ExerciseHistory createFromParcel(Parcel in) {
            return new ExerciseHistory(in);
        }

        public ExerciseHistory[] newArray(int size) {
            return new ExerciseHistory[size];
        }
    };

    private ExerciseHistory(Parcel in) {
        exerciseName = in.readString();
        sets = in.readString();
        reps = in.readString();
        didPass = in.readInt() == 1 ? true: false;
        exerciseDate = in.readString();
    }
}
