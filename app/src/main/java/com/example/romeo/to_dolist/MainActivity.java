package com.example.romeo.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<String> entries = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView entryList = (ListView) findViewById(R.id.entries);
        entryList.setOnItemClickListener(this);
        entries = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                R.layout.list_layout,   // custom layout for the list
                R.id.entry,             // ID of TextView item within the list layout
                entries
        );

        entryList.setAdapter(adapter);
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

    /*
     * This method is called when the user clicks on items in the ListView.
     * It checks whether the item clicked was the correct one and alerts the
     * user accordingly.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long iden) {
        TextView entryText = (TextView) findViewById(R.id.entry);
        String strEntry = entryText.getText().toString();
        entries.remove(strEntry);
        adapter.notifyDataSetChanged();
    }

    public void addEntry(View view) {
        EditText new_entry = (EditText) findViewById(R.id.new_entry);
        String strEntry = new_entry.getText().toString();
        if(strEntry.length() > 0){
            entries.add(strEntry);
            adapter.notifyDataSetChanged();
        }
    }

    /*
     * Activity lifecycle method. (See flowchart from slides.)
     * Called when the activity pauses, especially for saving
     * any private fields or other activity state.
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        // save the clicks count and current image ID state
        bundle.putStringArrayList("entries", entries);
    }

    /*
     * Activity lifecycle method. (See flowchart from slides.)
     * Called when the activity unpauses, especially for loading
     * any private fields or other activity state that was saved
     * in onSaveInstanceState.
     */
    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

        // load the clicks count and current image ID state
//        if(entries == NULL){
        Log.v("lifecycle", "onRestoreInstanceState was called");

        entries = bundle.getStringArrayList("entries");
        ListView entryList = (ListView) findViewById(R.id.entries);
        entryList.setOnItemClickListener(this);
        adapter = new ArrayAdapter<>(
                this,
                R.layout.list_layout,   // custom layout for the list
                R.id.entry,             // ID of TextView item within the list layout
                entries
        );

        entryList.setAdapter(adapter);

    }
}
