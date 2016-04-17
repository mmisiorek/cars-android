package android.cars.misiorek.net.cars90;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by marcin on 14.04.16.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
