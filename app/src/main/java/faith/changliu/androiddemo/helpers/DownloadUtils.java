package faith.changliu.androiddemo.helpers;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DownloadUtils {

	public static boolean isConnected(ConnectivityManager cm) {
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnected();
	}
}
