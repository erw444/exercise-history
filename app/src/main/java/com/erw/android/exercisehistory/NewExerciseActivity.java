package com.erw.android.exercisehistory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.erw.android.exercisehistory.database.ExerciseHistoryEntity;
import com.erw.android.exercisehistory.database.ExerciseHistoryEntityWithExerciseName;
import com.erw.android.exercisehistory.database.ExerciseName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewExerciseActivity extends AppCompatActivity {
    public static final String EXTRA_EXERCISE_HISTORY = "com.erw.android.exercisehistory.EXERCISEHISTORY";

    private AutoCompleteTextView mExerciseName;
    private ExerciseName mSelectedExerciseName;
    private EditText mEditSets;
    private EditText mEditReps;
    private CheckBox mCheckDidPass;
    private static TextView mTextExerciseDate;
    private ExerciseNameViewModel mExerciseNameViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        mExerciseName = findViewById(R.id.edit_exercise_name);
        mSelectedExerciseName = null;
        mEditSets = findViewById(R.id.edit_sets);
        mEditReps = findViewById(R.id.edit_reps);
        mCheckDidPass = findViewById(R.id.check_did_pass);
        mTextExerciseDate = findViewById(R.id.value_exercise_date);
        mTextExerciseDate.setText(DateUtils.convertDateToString(new Date()));

        mExerciseNameViewModel = ViewModelProviders.of(this).get(ExerciseNameViewModel.class);
        final ArrayAdapter<ExerciseName> adapter = new ArrayAdapter<ExerciseName>(this, android.R.layout.simple_list_item_1);
        mExerciseNameViewModel.getExerciseNames().observe(this, new Observer<List<ExerciseName>>() {
            @Override
            public void onChanged(@Nullable final List<ExerciseName> exerciseNames) {
                adapter.addAll(exerciseNames);
            }
        });
        mExerciseName.setAdapter(adapter);
        mExerciseName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArrayAdapter<ExerciseName> adapter = ((ArrayAdapter<ExerciseName>)
                        mExerciseName.getAdapter());
                mSelectedExerciseName = adapter.getItem(position);
            }
        });

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mExerciseName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    ExerciseName exerciseName;
                    if(mSelectedExerciseName != null){
                        exerciseName = mSelectedExerciseName;
                    } else {
                        exerciseName = new ExerciseName(mExerciseName.getText().toString());
                    }
                    String sets = mEditSets.getText().toString();
                    String reps = mEditReps.getText().toString();
                    boolean didPass = mCheckDidPass.isChecked();
                    String exerciseDate = mTextExerciseDate.getText().toString();

                    ExerciseHistoryEntity exerciseHistory = new ExerciseHistoryEntity();
                    exerciseHistory.setExerciseNameId(exerciseName.getId());
                    exerciseHistory.setSets(sets);
                    exerciseHistory.setReps(reps);
                    exerciseHistory.setDidPass(didPass);
                    exerciseHistory.setExerciseDate(DateUtils.convertStringToDate(exerciseDate));

                    ExerciseHistoryEntityWithExerciseName exerciseHistoryWithName =
                            new ExerciseHistoryEntityWithExerciseName(exerciseName, exerciseHistory);

                    replyIntent.putExtra(EXTRA_EXERCISE_HISTORY, exerciseHistoryWithName);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month++;

            mTextExerciseDate.setText("" + month + "/" + day + "/" + year);
        }
    }
}
