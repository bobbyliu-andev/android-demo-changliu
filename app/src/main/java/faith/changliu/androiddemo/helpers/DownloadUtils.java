package faith.changliu.androiddemo.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import faith.changliu.androiddemo.AppContext;

public class DownloadUtils {

	private static ConnectivityManager cm;

	static {
		cm = (ConnectivityManager) AppContext.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static boolean isConnected() {
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnected();
	}
}
