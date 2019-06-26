package com.erw.android.exercisehistory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.erw.android.exercisehistory.database.ExerciseHistoryEntityWithExerciseName;
import com.erw.android.exercisehistory.database.ExerciseHistoryRepository;
import com.erw.android.exercisehistory.database.ExerciseName;
import com.erw.android.exercisehistory.database.ExerciseNameRepository;

import java.util.List;

public class ExerciseHistoryViewModel extends AndroidViewModel {
    private ExerciseHistoryRepository mEHRepo;
    private ExerciseNameRepository mENRepo;
    private LiveData<List<ExerciseHistoryEntityWithExerciseName>> mExerciseHistoryEntities;
    private LiveData<List<ExerciseName>> mExerciseNames;

    public ExerciseHistoryViewModel(Application app){
        super(app);
        mEHRepo = new ExerciseHistoryRepository(app);
        mExerciseHistoryEntities = mEHRepo.getExerciseHistory();

        mENRepo = new ExerciseNameRepository(app);
        mExerciseNames = mENRepo.getExerciseNames();

    }

    public LiveData<List<ExerciseHistoryEntityWithExerciseName>> getExerciseHistory() {
        return mExerciseHistoryEntities;
    }

    public void insert(ExerciseHistoryEntityWithExerciseName exerciseEntity){
        mEHRepo.insert(exerciseEntity);
    }

    public LiveData<List<ExerciseName>> getExerciseNames() {
        return mExerciseNames;
    }

    public ExerciseName getExerciseName(int id) {
        ExerciseName exerciseName = mENRepo.getExerciseName(id);
        return exerciseName;
    }

}
