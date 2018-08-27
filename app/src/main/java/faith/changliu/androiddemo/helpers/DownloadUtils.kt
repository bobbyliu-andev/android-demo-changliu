package faith.changliu.androiddemo.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import faith.changliu.androiddemo.AppContext
import faith.changliu.androiddemo.R

/**
 * Check connectivity
 */
fun isConnected(): Boolean {
	val activeNetwork = connectivityManager.activeNetworkInfo
	activeNetwork?.let {
		networkInfo -> return networkInfo.isConnected
	}
	return false
}
private val connectivityManager by lazy { AppContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

/**
 * If internet connectivity is up, do the download task, else prompt user no internet.
 * @param onConnectedBlock block run when connectivity is positive
 */
inline fun View.onConnected(onConnectedBlock: () -> Unit) {
	if (isConnected()) onConnectedBlock()
	else snackbar(R.string.no_internet)
}

/**
 * Run block if no http error found, else prompt user
 * @param statusCode HTTP status code to be checked
 * @param onNoHttpException Block to be run if no http error found
 */
inline fun onNoHttpError(statusCode: Int, onNoHttpException: () -> Unit) {
	if (statusCode in 200..299) onNoHttpException()
	else toast(R.string.download_failed)
}