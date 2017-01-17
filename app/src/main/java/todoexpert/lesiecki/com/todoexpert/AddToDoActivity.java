package todoexpert.lesiecki.com.todoexpert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToDoActivity extends AppCompatActivity {

    @BindView(R.id.content_edit_text)
    EditText contentEditText;
    @BindView(R.id.done_checkbox)
    CheckBox doneCheckbox;
    @BindView(R.id.add_button)
    Button addButton;
    @BindView(R.id.activity_add_to_do)
    RelativeLayout activityAddToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(ToDoListActivity.TODO_EXTRA)) {
            Todo todo = (Todo) getIntent().getSerializableExtra(ToDoListActivity.TODO_EXTRA);
            contentEditText.setText(todo.getContent());
            doneCheckbox.setChecked(todo.isDone());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @OnClick(R.id.add_button)
    public void onClick() {
        Todo todo = new Todo(contentEditText.getText().toString(), doneCheckbox.isChecked());

        Intent intent = new Intent();
        intent.putExtra(ToDoListActivity.TODO_EXTRA, todo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
