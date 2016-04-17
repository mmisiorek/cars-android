package android.cars.misiorek.net.cars90;

import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Car;
import android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model.Gson;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class CarPreviewActivity extends AppCompatActivity {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            dateOfManufacturedText.setText(getDefaultDateFormat().format(car.getManufacturedDate()));
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

        FloatingActionButton editCarBtn = (FloatingActionButton) findViewById(R.id.car_update_floating_btn);
        editCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CarEditActivity.class);

                startActivity(intent);
            }
        });

    }

    protected SimpleDateFormat getDefaultDateFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format;
    }

}
