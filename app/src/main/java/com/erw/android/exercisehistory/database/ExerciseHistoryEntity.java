package com.erw.android.exercisehistory.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.erw.android.exercisehistory.DateUtils;

import java.util.Date;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise_history", foreignKeys = @ForeignKey(entity = ExerciseName.class,
                                                                    parentColumns = "exercise_name_id",
                                                                    childColumns =  "eh_exercise_name_id",
                                                                    onDelete = CASCADE))
public class ExerciseHistoryEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_history_id")
    private int id;

    @ColumnInfo(name = "eh_exercise_name_id")
    private int exerciseNameId;
    private String sets;
    private String reps;

    @ColumnInfo(name = "did_pass")
    private boolean didPass;

    @ColumnInfo(name = "exercise_date")
    private Date exerciseDate;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public ExerciseHistoryEntity(int exerciseNameId, String sets, String reps, boolean didPass, Date exerciseDate, Date updatedAt){
        this.exerciseNameId = exerciseNameId;
       this.sets = sets;
       this.reps = reps;
       this.didPass = didPass;
       this.exerciseDate = updatedAt;
    }

    @Ignore
    public ExerciseHistoryEntity(int id, int exerciseNameId, String sets, String reps, boolean didPass, Date exerciseDate, Date updatedAt) {
        this.id = id;
        this.exerciseNameId = exerciseNameId;
        this.sets = sets;
        this.reps = reps;
        this.didPass = didPass;
        this.exerciseDate = updatedAt;
    }

    public ExerciseHistoryEntity(){

    }

    public int getExerciseNameId() {
        return exerciseNameId;
    }

    public void setExerciseNameId(int exerciseNameId) {
        this.exerciseNameId = exerciseNameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(exerciseNameId);
        parcel.writeString(sets);
        parcel.writeString(reps);
        parcel.writeInt(didPass? 1: 0);
        parcel.writeString(DateUtils.convertDateToString(exerciseDate));
    }

    public static final Parcelable.Creator<ExerciseHistoryEntity> CREATOR
            = new Parcelable.Creator<ExerciseHistoryEntity>() {
        public ExerciseHistoryEntity createFromParcel(Parcel in) {
            return new ExerciseHistoryEntity(in);
        }

        public ExerciseHistoryEntity[] newArray(int size) {
            return new ExerciseHistoryEntity[size];
        }
    };

    private ExerciseHistoryEntity(Parcel in) {
        id = in.readInt();
        exerciseNameId = in.readInt();
        sets = in.readString();
        reps = in.readString();
        didPass = in.readInt() == 1 ? true: false;
        exerciseDate = DateUtils.convertStringToDate(in.readString());
    }
}
