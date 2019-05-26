package com.erw.android.exercisehistory;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.erw.android.exercisehistory.database.ExerciseName;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExerciseNameViewModel mExerciseNameViewModel;

    public static final int NEW_EXERCISE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewExerciseActivity.class);
                startActivityForResult(intent, NEW_EXERCISE_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ExerciseNamesAdapter adapter = new ExerciseNamesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExerciseNameViewModel = ViewModelProviders.of(this).get(ExerciseNameViewModel.class);

        mExerciseNameViewModel.getExerciseNames().observe(this, new Observer<List<ExerciseName>>() {
            @Override
            public void onChanged(@Nullable final List<ExerciseName> exerciseNames) {
                // Update the cached copy of the words in the adapter.
                adapter.setExerciseNames(exerciseNames);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_EXERCISE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ExerciseName exerciseName = new ExerciseName(data.getStringExtra(NewExerciseActivity.EXTRA_REPLY));
            mExerciseNameViewModel.insert(exerciseName);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
