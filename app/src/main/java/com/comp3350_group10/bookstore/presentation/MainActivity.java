package com.comp3350_group10.bookstore.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.business.IUserDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;
import com.comp3350_group10.bookstore.presentation.UI_Handler.TrendingPageFunctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{
    private IButtonFunctions uIButtonFunctions;
    private TableLayout bookListTable;
    private EditText searchBar;
    private Spinner dropdown;
    private Button sortButton;
    private LinearLayout sortingLayout;
    private IUserDataHandler userDataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();

        //link references
        uIButtonFunctions = new ButtonFunctions();
        searchBar = findViewById(R.id.searchBar);
        bookListTable = findViewById(R.id.bookListTable);
        dropdown = findViewById(R.id.sortingDropdown);
        sortButton = findViewById(R.id.sortingButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        sortingLayout = findViewById(R.id.sortingLayout);
        sortingLayout.setVisibility(LinearLayout.GONE);
        setSupportActionBar(toolbar);
        userDataHandler = new UserDataHandler();

        //Performs search each time a character is entered
        SetSearchListener(getBaseContext(), this);
        SetDropdownListener(getBaseContext(), this);

        //Populate the trending table with categories
        FillTrendingTable();
        FillDropdownList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    private void copyDatabaseToDevice(){
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try{
            assetNames = assetManager.list(DB_PATH);
            for(int i = 0; i < assetNames.length; i++){
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPath(dataDirectory.toString()+ "/" + Main.getDBPath());
        }
        catch(final IOException e){
            Messages.warning(this, "Unable to access application data: "+ e.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
    AssetManager assetManager = getAssets();

    for (String asset : assets) {
        String[] components = asset.split("/");
        String copyPath = directory.toString() + "/" + components[components.length - 1];

        char[] buffer = new char[1024];
        int count;

        File outFile = new File(copyPath);

        if (!outFile.exists()) {
            InputStreamReader in = new InputStreamReader(assetManager.open(asset));
            FileWriter out = new FileWriter(outFile);

            count = in.read(buffer);
            while (count != -1) {
                out.write(buffer, 0, count);
                count = in.read(buffer);
            }
            out.close();
            in.close();
        }
    }
}

    private void FillDropdownList() {
        String[] items = new String[] {"By Title", "By Author", "By Genre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void FillTrendingTable() {
        TrendingPageFunctions.FillTrendingPage(bookListTable, this);
    }

    private void SetSearchListener(Context context, MainActivity main) {
        searchBar.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uIButtonFunctions.SearchButtonPressed(s.toString(),
                        bookListTable, context, main,
                        sortButton.getText().toString().contains("ASC"),
                        (String)dropdown.getSelectedItem());

                int visibility = s.length() == 0 ? LinearLayout.GONE : LinearLayout.VISIBLE;
                sortingLayout.setVisibility(visibility);
            }
        });
    }

    private void SetDropdownListener(Context context, MainActivity main) {
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                uIButtonFunctions.SearchButtonPressed(searchBar.getText().toString(),
                        bookListTable, context, main,
                        sortButton.getText().toString().contains("ASC"),
                        (String)dropdown.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //Hide all items
        for (int i = 0; i < menu.size(); i++)
            menu.getItem(i).setVisible(false);

        //Hide Logout if the user is not logged in
        menu.findItem(R.id.main_logout_button).setVisible(userDataHandler.getCurrentUser() != null);
        //Hide User Settings if the user is not logged in
        menu.findItem(R.id.switchTo_user_settings_button).setVisible(userDataHandler.getCurrentUser() != null);
        //Hide Login if the users is logged in
        menu.findItem(R.id.switchTo_login_button).setVisible(userDataHandler.getCurrentUser() == null);

        return true;
    }

    public void onLoginButtonClicked(MenuItem item) {
        SwitchActivity.SwitchTo(LoginActivity.class,this);
    }

    public void onLogoutButtonClicked(MenuItem item) {
        uIButtonFunctions.LogoutButtonPressed();
        Messages.viewPopUp("Logout successful", this);

        finish();
        startActivity(getIntent());
    }

    public void userSettingButtonClicked(MenuItem item) {
        SwitchActivity.SwitchTo(UserSettingActivity.class,this);
    }

    public void onSortButtonClicked(View v) {
        Button b = (Button)v;
        if (b.getText().toString().equals("DESC"))
            b.setText("ASC");
        else b.setText("DESC");

        uIButtonFunctions.SearchButtonPressed(searchBar.getText().toString(),
                bookListTable, getBaseContext(),
                this, sortButton.getText().toString().contains("ASC"),
                (String)dropdown.getSelectedItem());
    }
}