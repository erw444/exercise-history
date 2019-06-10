package com.erw.android.exercisehistory.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "exercise_history")
public class ExerciseHistoryEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "exercise_name")
    private String exerciseName;
    private String sets;
    private String reps;

    @ColumnInfo(name = "did_pass")
    private boolean didPass;

    @ColumnInfo(name = "exercise_date")
    private Date exerciseDate;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public ExerciseHistoryEntity(String exerciseName, String sets, String reps, boolean didPass, Date exerciseDate, Date updatedAt){
        this.exerciseName = exerciseName;
       this.sets = sets;
       this.reps = reps;
       this.didPass = didPass;
       this.exerciseDate = updatedAt;
    }

    public ExerciseHistoryEntity(int id, String exerciseName, String sets, String reps, boolean didPass, Date exerciseDate, Date updatedAt) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.didPass = didPass;
        this.exerciseDate = updatedAt;
    }

    public ExerciseHistoryEntity(){

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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
}
