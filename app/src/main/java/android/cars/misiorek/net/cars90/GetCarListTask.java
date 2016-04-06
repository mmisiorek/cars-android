package android.cars.misiorek.net.cars90;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 06.04.16.
 */
public class GetCarListTask extends AsyncTask<ListView, Integer, List<JSONObject>> {

    final private static String urlString = "http://10.0.2.2:3000/cars.json";

    private ArrayAdapter<String> adapter;
    private LinkedList<JSONObject> carsList;

    @Override
    protected List<JSONObject> doInBackground(ListView... params) {
        adapter = (ArrayAdapter<String>) params[0].getAdapter();

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream inputStream = urlConnection.getInputStream();
                carsList = (LinkedList<JSONObject>) getCarsFromInputStream(inputStream);

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

    private List<JSONObject> getCarsFromInputStream(InputStream inputStream)
    {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String json = scanner.hasNext() ? scanner.next() : "";

        try {
            JSONObject main = new JSONObject(json);
            JSONArray cars = main.getJSONArray("cars");

            LinkedList<JSONObject> carsList = new LinkedList<>();
            for(int i = 0; i < cars.length(); i++) {
                JSONObject car = (JSONObject) cars.get(i);

                carsList.add(i, car);
            }

            return carsList;

        } catch(Exception e) {
            String s = Log.getStackTraceString(e);
            Log.e("f", s);
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<JSONObject> jsonArray) {
        super.onPostExecute(jsonArray);

        try {
            LinkedList<String> names = new LinkedList<>();
            for(JSONObject obj : jsonArray) {
                names.add(obj.getString("brand")+" "+obj.getString("model"));
            }

            adapter.clear();
            adapter.addAll(names);
            adapter.notifyDataSetChanged();
        }
        catch(JSONException e) {

        }
    }

    public List<JSONObject> getCarsList() {
        return carsList;
    }
}
