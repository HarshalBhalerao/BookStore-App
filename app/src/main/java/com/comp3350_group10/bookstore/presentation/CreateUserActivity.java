package com.comp3350_group10.bookstore.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.objects.IUser;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;


public class CreateUserActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    private Switch isManager;
    private IButtonFunctions uiButtonFunctions;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        uiButtonFunctions = new ButtonFunctions();

        name = findViewById(R.id.new_name_text);
        email = findViewById(R.id.new_email_text);
        password = findViewById(R.id.new_password_text);
        isManager = findViewById(R.id.new_manager_switch);
    }

    //Onclick method for the create user button
    public void createUserOnClick(View v) {
        IUser createdUser;
        try {
            //try creating the user
            createdUser = uiButtonFunctions.CreateUserButtonPressed(name.getText().toString(), email.getText().toString(), password.getText().toString(), isManager.isChecked());

            //successful, proceed default behaviour
            if(createdUser!=null) {
                //sends heartwarming welcome message to new employee so they don't complain about low wage
                Messages.viewPopUp("Account for " + createdUser.getRealName() + " has been created.\nWelcome to the team.", this);
                SwitchActivity.SwitchTo(MainActivity.class, this);
            }
        }

        //failed, exception is thrown with error message
        catch(CreateUserErrorException | PersistenceException e){
            //popup showing error
            Messages.viewPopUp(e.getMessage(), this);
        }
    }
}