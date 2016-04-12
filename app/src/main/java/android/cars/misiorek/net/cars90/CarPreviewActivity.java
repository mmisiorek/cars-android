package android.cars.misiorek.net.cars90;

import android.app.ActionBar;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Car;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Gson;
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

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            car = Gson.getGson().fromJson(getIntent().getExtras().getString("car"), Car.class);

            setTitle(car.getBrand()+" "+car.getModel());

            TextView brandText = (TextView) findViewById(R.id.brandValue);
            TextView modelText = (TextView) findViewById(R.id.modelValue);
            TextView dateOfManufacturedText = (TextView) findViewById(R.id.dateOfManufactureValue);
            TextView registrationNumberText = (TextView) findViewById(R.id.registrationNumberValue);
            TextView isAvailableText = (TextView) findViewById(R.id.isAvailableValue);

            brandText.setText(car.getBrand());
            modelText.setText(car.getModel());
            dateOfManufacturedText.setText(car.getManufacturedDate().toString());
            registrationNumberText.setText(car.getRegistrationNumber());
            isAvailableText.setText(car.getIsAvailable().booleanValue() ? getResources().getString(R.string.is_available) :
                                        getResources().getString(R.string.is_unavailable));

            Log.e("asJSON", Gson.getGson().toJson(car));
            if(car.getCarPhoto() != null) {
                ImageView imageView = (ImageView) findViewById(R.id.photoCar);

                CarPhotoTask task = new CarPhotoTask(imageView, imageView.getContext());
                task.execute(new String[]{ String.valueOf(car.getCarPhoto().getNetworkId()), car.getCarPhoto().getToken()});
            }
        } catch(Exception e) {
            Log.e("z", Log.getStackTraceString(e));
        }

    }

}
