package com.erw.android.exercisehistory.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "exercise_names")
public class ExerciseName {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "exercise_name")
    private String exerciseName;

    public ExerciseName (String exerciseName){
        this.exerciseName = exerciseName;
    }

    @NonNull
    public String getExerciseName() {
        return this.exerciseName;
    }
}
