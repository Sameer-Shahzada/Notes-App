package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for entering a word.
 */

public class NewNoteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.notes.notelistsql.REPLY";

    private EditText mEditNoteView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditNoteView = findViewById(R.id.input);

        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditNoteView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditNoteView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}

