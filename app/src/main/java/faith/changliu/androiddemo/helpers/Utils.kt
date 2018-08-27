package faith.changliu.androiddemo.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import faith.changliu.androiddemo.AppContext
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

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
 * Display snackbar with string res id
 */
fun View.snackbar(@StringRes resId: Int) {
	Snackbar.make(this, AppContext.getString(resId), Snackbar.LENGTH_SHORT).show()
}

/**
 * Display snackbar with string
 * @param msg Display message
 */
fun View.snackbar(msg: String) {
	Snackbar.make(this, msg, Snackbar.LENGTH_LONG).show()
}

/**
 * Download file from URL and output as string. Handles exceptions
 *
 * @return Content downloaded from URL. Return an empty string if exceptions caught
 */
fun getContentFromStringUrl(urlString: String): String {
	var result = ""
	try {
		val url = URL(urlString)
		// readText handles input stream closing
		result = url.readText()
	} catch (ex: MalformedURLException) {
		ex.printStackTrace()
	} catch (ex: IOException) {
		ex.printStackTrace()
	}
	return result
}

/**
 * Get string from file in assets folder
 */
fun readStringFromAssets(fileName: String): String {
	var result = ""
	try {
		AppContext.assets.open(fileName).bufferedReader().use { bufferedReader ->
			result = bufferedReader.readText()
		}
	} catch (ex: IOException) {
		ex.printStackTrace()
	} catch (ex: Exception) {
		ex.printStackTrace()
	}

	return result
}

/**
 * Debug logging, to be deleted before release
 */
fun logwtf(msg: String, tag: String = "changsdebug") {
	Log.wtf(tag, msg)
}