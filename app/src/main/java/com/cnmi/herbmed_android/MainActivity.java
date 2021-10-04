package com.cnmi.herbmed_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private Button searchButton;
    private SearchableSpinner medSpinner;
    private SearchableSpinner herbSpinner;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> medlist = databaseAccess.getDrugName();
        List<String> herblist = databaseAccess.getHerbname();
        //ArrayList<MedModel> med = databaseAccess.getDruglist();
        databaseAccess.close();

        medSpinner = findViewById(R.id.medspinner);
        herbSpinner = findViewById(R.id.herbspinner);
        medSpinner.setTitle("เลือกยา");
        herbSpinner.setTitle("เลือกสมุนไพร");
        medSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, medlist));
        herbSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, herblist));

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        //initView();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void searchQuery(){
        String selectmed = String.valueOf(medSpinner.getSelectedItem());
        int selectherb = herbSpinner.getSelectedItemPosition();
        Log.d("Select", String.valueOf(selectherb));
        String replace;


        if(selectmed.equals("Busulfan ")){
            replace = "Busulfan";
        }else {
            replace = selectmed.replaceAll("\\s", "_");
        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<SummaryModel> querySum = databaseAccess.getQuery(replace,selectherb+1);

        databaseAccess.close();

        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapter = new CardAdapter(this, querySum);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment.isAdded())
            return;
        super.onAttachFragment(fragment);
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


}