package com.erw.android.exercisehistory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewExerciseActivity extends AppCompatActivity {
    public static final String EXTRA_EXERCISE_HISTORY = "com.erw.android.exercisehistory.EXERCISEHISTORY";

    private EditText mEditExerciseName;
    private EditText mEditSets;
    private EditText mEditReps;
    private CheckBox mCheckDidPass;
    private static TextView mTextExerciseDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        mEditExerciseName = findViewById(R.id.edit_exercise_name);
        mEditSets = findViewById(R.id.edit_sets);
        mEditReps = findViewById(R.id.edit_reps);
        mCheckDidPass = findViewById(R.id.check_did_pass);
        mTextExerciseDate = findViewById(R.id.value_exercise_date);

        Date currentTime = Calendar.getInstance().getTime();
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String now = dateFormat.format(new Date());
        mTextExerciseDate.setText(now);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditExerciseName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String exerciseName = mEditExerciseName.getText().toString();
                    String sets = mEditSets.getText().toString();
                    String reps = mEditReps.getText().toString();
                    boolean didPass = mCheckDidPass.isChecked();
                    String exerciseDate = mTextExerciseDate.getText().toString();

                    ExerciseHistory exerciseHistory = new ExerciseHistory();
                    exerciseHistory.setExerciseName(exerciseName);
                    exerciseHistory.setSets(sets);
                    exerciseHistory.setReps(reps);
                    exerciseHistory.setDidPass(didPass);
                    exerciseHistory.setExerciseDate(exerciseDate);

                    replyIntent.putExtra(EXTRA_EXERCISE_HISTORY, exerciseHistory);
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
