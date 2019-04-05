package com.example.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    NumberPicker priority;
    NoteViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        title = findViewById(R.id.title_item);
        description = findViewById(R.id.description_item);
        priority = findViewById(R.id.priority_item);
        priority.setMaxValue(10);
        priority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel_black_24dp);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title_value = title.getText().toString();
        String description_value = description.getText().toString();
        int priority_value = priority.getValue();

        if (title_value.trim().isEmpty() || description_value.trim().isEmpty()) {
            Toast.makeText(this, "Please enter the valid title and description..", Toast.LENGTH_SHORT).show();
            return;
        }
        mModel.insert(new Note(title_value, description_value, priority_value));
        finish();
    }
}
