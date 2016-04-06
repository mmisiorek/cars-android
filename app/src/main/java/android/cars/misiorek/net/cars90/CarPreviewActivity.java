package android.cars.misiorek.net.cars90;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Date;

public class CarPreviewActivity extends AppCompatActivity {

    private JSONObject car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            long start = (new Date()).getTime();
            car = new JSONObject(getIntent().getStringExtra("car"));
            long end = (new Date()).getTime();

            Log.e("mm", "convert back "+Long.valueOf(end-start).toString());

            start = (new Date()).getTime();
            setTitle(car.getString("brand")+" "+car.getString("model"));

            TextView brandText = (TextView) findViewById(R.id.brandValue);
            TextView modelText = (TextView) findViewById(R.id.modelValue);
            TextView dateOfManufacturedText = (TextView) findViewById(R.id.dateOfManufactureValue);
            TextView registrationNumberText = (TextView) findViewById(R.id.registrationNumberValue);
            TextView isAvailableText = (TextView) findViewById(R.id.isAvailableValue);

            brandText.setText(car.getString("brand"));
            modelText.setText(car.getString("model"));
            dateOfManufacturedText.setText(car.getString("manufactured_date"));
            registrationNumberText.setText(car.getString("registration_number"));
            isAvailableText.setText(car.getBoolean("is_available") ? getResources().getString(R.string.is_available) :
                                        getResources().getString(R.string.is_unavailable));
            end = (new Date()).getTime();

            Log.e("mm", "other actions "+Long.valueOf(end-start).toString());

            JSONObject obj = car.getJSONObject("photo");
            if(obj != null) {
                ImageView imageView = (ImageView) findViewById(R.id.photoCar);

                Log.e("ksk", obj.toString());

                CarPhotoTask task = new CarPhotoTask(imageView, imageView.getContext());
                task.execute(new String[]{ String.valueOf(obj.getInt("id")), obj.getString("token")});
            }
        } catch(Exception e) {
            Log.e("z", Log.getStackTraceString(e));
        }

    }

}
