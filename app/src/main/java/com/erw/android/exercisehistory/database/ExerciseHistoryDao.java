package com.erw.android.exercisehistory.database;



import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExerciseHistoryDao {

    @Query("SELECT * FROM exercise_history")
    LiveData<List<ExerciseHistoryEntity>> loadExerciseHistory();

    @Insert
    void insert(ExerciseHistoryEntity exerciseHistoryEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ExerciseHistoryEntity exerciseHistoryEntity);

    @Delete
    void delete(ExerciseHistoryEntity exerciseHistoryEntity);

    @Query("SELECT * FROM exercise_history WHERE id = :id")
    LiveData<ExerciseHistoryEntity> loadExerciseById(int id);
}
