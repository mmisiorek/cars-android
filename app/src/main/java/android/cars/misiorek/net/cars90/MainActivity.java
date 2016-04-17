package android.cars.misiorek.net.cars90;

import android.app.Activity;
import android.app.AlertDialog;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Car;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Gson;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;

import org.json.JSONObject;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView carsList = (ListView) findViewById(R.id.cars_list);
        createCarListsAdapter();
        carsList.setAdapter(adapter);

        final GetCarListTask task = new GetCarListTask();
        task.execute(carsList);

        final Activity activity = this;
        carsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity.getBaseContext(), CarPreviewActivity.class);
                List<Car> carsList = task.getCarsList();

                if(carsList.size() > position) {
                    long start = (new Date()).getTime();
                    intent.putExtra("car", Gson.getGson().toJson(carsList.get(position)));
                    long end = (new Date()).getTime();

                    Log.e("mm", Long.valueOf(end - start).toString());
                    startActivity(intent);
                }
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void createCarListsAdapter() {

        LinkedList<String> list = new LinkedList<>();

        adapter = new ArrayAdapter<>(this, R.layout.row_main, list);
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
            Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_update_cars) {
            ListView carsList = (ListView) findViewById(R.id.cars_list);
            GetCarListTask task = new GetCarListTask();
            task.execute(carsList);

        }

        return super.onOptionsItemSelected(item);
    }
}
