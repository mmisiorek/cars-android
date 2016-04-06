package android.cars.misiorek.net.cars90;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by marcin on 06.04.16.
 */
public class CarPhotoTask extends AsyncTask<String[], Integer, Bitmap> {

    private final static String urlString = "http://10.0.2.2:3000/document/%d/%s/%d/%d";

    private ImageView imageView;
    private Context context;

    public CarPhotoTask(ImageView _imageView, Context _context) {
        super();
        imageView = _imageView;
        context = _context;
    }

    @Override
    protected Bitmap doInBackground(String[]... params) {
        try {
            Integer id = Integer.parseInt(params[0][0]);
            String token = params[0][1];
            int biggerDimension = getBiggerDimension();

            ImageLoader loader = ImageLoader.getInstance();
            loader.init(ImageLoaderConfiguration.createDefault(context));

            Log.e("urlString", urlString);

            return loader.loadImageSync(String.format(urlString, id.intValue(), token, biggerDimension, biggerDimension));

        } catch(Exception e) {
            Log.e("exception", Log.getStackTraceString(e));
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
    }

    private int getBiggerDimension() {
        Point size = getDisplaySize();
        return Math.max(size.x, size.y);
    }

    private Point getDisplaySize() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        return size;
    }
}
