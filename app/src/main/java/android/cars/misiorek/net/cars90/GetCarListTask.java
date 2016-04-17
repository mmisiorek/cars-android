package android.cars.misiorek.net.cars90;

import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Car;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Gson;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.utils.IoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 06.04.16.
 */
public class GetCarListTask extends AsyncTask<ListView, Integer, List<Car>> {

    final private static String urlString = "http://192.168.0.105:3000/cars.json";

    private ArrayAdapter<String> adapter;
    private List<Car> carsList;

    @Override
    protected List<Car> doInBackground(ListView... params) {
        adapter = (ArrayAdapter<String>) params[0].getAdapter();

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream inputStream = urlConnection.getInputStream();
                carsList = Arrays.asList(getCarsFromInputStream(inputStream));

                return carsList;

            } catch(Exception e) {
                String s = Log.getStackTraceString(e);
                Log.e("d", s);

            } finally {
                urlConnection.disconnect();
            }

        } catch(Exception e) {
            Log.e("e", e.getMessage());
        }

        return null;
    }

    private Car[] getCarsFromInputStream(InputStream inputStream)
    {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String json = scanner.hasNext() ? scanner.next() : "";

        Log.e("ee", json);


        try {
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();

            Car[] cars = Gson.getGson().fromJson(obj.get("cars"), Car[].class);
            Log.e("qw", Boolean.valueOf(cars == null).toString());

            return cars;
        } catch(Exception e) {
            Log.e("q", Log.getStackTraceString(e));
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Car> carList) {
        super.onPostExecute(carList);

        try {
            LinkedList<String> names = new LinkedList<>();
            for(Car car : carList) {
                names.add(car.getBrand()+" "+car.getModel());
            }

            adapter.clear();
            adapter.addAll(names);
            adapter.notifyDataSetChanged();
        }
        catch(Exception e) {

        }
    }

    public List<Car> getCarsList() {
        return carsList;
    }
}
