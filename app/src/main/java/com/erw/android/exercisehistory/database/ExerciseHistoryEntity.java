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
    private int sets;
    private int reps;

    @ColumnInfo(name = "did_pass")
    private boolean didPass;

    @ColumnInfo(name = "exercise_date")
    private Date exerciseDate;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public ExerciseHistoryEntity(String exerciseName, int sets, int reps, boolean didPass, Date exerciseDate, Date updatedAt){
        this.exerciseName = exerciseName;
       this.sets = sets;
       this.reps = reps;
       this.didPass = didPass;
       this.exerciseDate = updatedAt;
    }

    public ExerciseHistoryEntity(int id, String exerciseName, int sets, int reps, boolean didPass, Date exerciseDate, Date updatedAt) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.didPass = didPass;
        this.exerciseDate = updatedAt;
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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
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
