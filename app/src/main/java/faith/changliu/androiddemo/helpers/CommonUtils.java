package faith.changliu.androiddemo.helpers;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonUtils {

	/**
	 * Display snack bar
	 * @param view view that hosts the snack bar
	 * @param ctx Context used to access string resource
	 * @param resId resource id
	 */
	public static void snackbar(View view, Context ctx, @StringRes int resId) {
		Snackbar.make(view, ctx.getString(resId), Snackbar.LENGTH_SHORT).show();
	}

	/**
	 * Display snack bar
	 * @param view view that hosts the snack bar
	 * @param msg display string
	 */
	public static void snackbar(View view, String msg) {
		Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
	}

	/**
	 * Download file from URL and output as string. Handles exceptions
	 *
	 * @return Content downloaded from URL. Return an empty string if exceptions caught
	 */
	public static String getContentFromStringUrl(String urlString) {
		String result = "";

		HttpURLConnection connection = null;
		URL url;
		InputStream is = null;

		// get http url connection
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			// get data
			is = connection.getInputStream();
			result = IOUtils.toString(url, "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * Get string from file in assets folder
	 */
	public static String readStringFromAssets(Context ctx, String fileName) {
		String result = "";
		InputStream is = null;

		try {
			is = ctx.getAssets().open(fileName);
			result = IOUtils.toString(is, "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
}
