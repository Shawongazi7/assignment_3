package com.example.assingmnet_3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskList = new ArrayList<>();
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private EditText editTextTitle, editTextDescription, editTextDueDate, editTextPriority, editTextTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDueDate = findViewById(R.id.editTextDueDate);
        editTextPriority = findViewById(R.id.editTextPriority);
        editTextTags = findViewById(R.id.editTextTags);
        FloatingActionButton fab = findViewById(R.id.fab);

        // Setup RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        taskRecyclerView.setAdapter(taskAdapter);

        // Button to add new task
        fab.setOnClickListener(v -> submitForm());
    }

    // Method to handle form submission and validation
    private void submitForm() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String dueDate = editTextDueDate.getText().toString().trim();
        String priority = editTextPriority.getText().toString().trim();
        String tags = editTextTags.getText().toString().trim();

        if (!validateTitle(title)) {
            Toast.makeText(this, "Invalid Title: Only letters and spaces allowed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validateDueDate(dueDate)) {
            Toast.makeText(this, "Invalid Date: Format must be YYYY-MM-DD", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(priority) || Integer.parseInt(priority) < 1 || Integer.parseInt(priority) > 5) {
            Toast.makeText(this, "Priority must be between 1 and 5", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(title, description, dueDate, Integer.parseInt(priority), tags);
        taskList.add(task);
        taskAdapter.notifyDataSetChanged();
        clearForm();
    }

    // Regex validation for task title (only letters and spaces allowed)
    private boolean validateTitle(String title) {
        String titlePattern = "^[A-Za-z ]+$";
        Pattern pattern = Pattern.compile(titlePattern);
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }

    // Regex validation for due date (format: YYYY-MM-DD)
    private boolean validateDueDate(String dueDate) {
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(dueDate);
        return matcher.matches();
    }

    private void clearForm() {
        editTextTitle.setText("");
        editTextDescription.setText("");
        editTextDueDate.setText("");
        editTextPriority.setText("");
        editTextTags.setText("");
    }


}