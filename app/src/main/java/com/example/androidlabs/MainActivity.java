package com.example.androidlabs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean undoInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main_linear);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top,
                    bars.right, bars.bottom);
            return insets;
        });

        TextView label = findViewById(R.id.lab2_textview);
        EditText input = findViewById(R.id.lab2_input);
        Button button = findViewById(R.id.lab2_button);
        CheckBox checkbox = findViewById(R.id.lab2_checkbox);

        button.setOnClickListener(v -> {
            label.setText(input.getText().toString());

            Toast.makeText(this,
                    getString(R.string.toast_message),
                    Toast.LENGTH_SHORT).show();
        });

        checkbox.setOnCheckedChangeListener((view, checked) -> {
            if (undoInProgress) {
                return;
            }

            String state = getString(checked
                    ? R.string.checkbox_on
                    : R.string.checkbox_off);

            String message = getString(
                    R.string.checkbox_status, state);

            Snackbar.make(findViewById(R.id.main),
                    message, Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo, v -> {
                        undoInProgress = true;
                        checkbox.setChecked(!checked);
                        undoInProgress = false;
                    })
                    .show();
        });
    }
}
