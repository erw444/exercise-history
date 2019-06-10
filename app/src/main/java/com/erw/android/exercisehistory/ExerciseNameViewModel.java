package com.erw.android.exercisehistory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.erw.android.exercisehistory.database.ExerciseName;
import com.erw.android.exercisehistory.database.ExerciseNameRepository;

import java.util.List;

public class ExerciseNameViewModel extends AndroidViewModel {
    private ExerciseNameRepository mRepo;
    private LiveData<List<ExerciseName>> mExerciseNames;

    public ExerciseNameViewModel(Application app){
        super(app);
        mRepo = new ExerciseNameRepository(app);
        mExerciseNames = mRepo.getExerciseNames();
    }

    public LiveData<List<ExerciseName>> getExerciseNames() {
        return mExerciseNames;
    }

    public void insert(ExerciseName exerciseName){
        mRepo.insert(exerciseName);
    }

    //TODO maybe do this at the REPO directly.
    public ExerciseName getExerciseName(String exerciseName){
        return mRepo.getExerciseName(exerciseName);
    }


}
