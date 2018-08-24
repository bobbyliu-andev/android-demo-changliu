package faith.changliu.androiddemo.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import faith.changliu.androiddemo.AppContext

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

fun View.snackbar(@StringRes resId: Int) {
	Snackbar.make(this, AppContext.getString(resId), Snackbar.LENGTH_SHORT).show()
}