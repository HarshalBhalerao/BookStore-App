package com.comp3350_group10.bookstore.presentation;

import androidx.appcompat.app.AppCompatActivity;

import com.comp3350_group10.bookstore.Exceptions.DeleteLoggedInUserException;
import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteUserActivity extends AppCompatActivity {
    private EditText deleteEmail;
    private Button deleteButton;
    IButtonFunctions buttonFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteEmail = findViewById(R.id.delete_user_id_text);
        deleteButton = findViewById(R.id.remove_user_button);
        buttonFunctions = new ButtonFunctions();

        AddTextChangedListeners();
    }

    private void AddTextChangedListeners() {
        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EnableDeleteButton();
            }
        };

        deleteEmail.addTextChangedListener(watcher);
    }
    private void EnableDeleteButton(){ deleteButton.setEnabled(!deleteEmail.getText().toString().equals("")); }

    public void RemoveButtonOnClick(View v){
        try {
            buttonFunctions.RemoveUserButtonPressed(deleteEmail.getText().toString());
            Messages.viewPopUp("User was successfully removed",this);
            SwitchActivity.SwitchTo(MainActivity.class, this);
        }
        catch(UserNotFoundException | PersistenceException| DeleteLoggedInUserException e){  //failed
            Messages.viewPopUp(e.getMessage(),this);
        }
    }
}