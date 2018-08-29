package faith.changliu.androiddemo.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.helpers.CommonUtils
import faith.changliu.androiddemo.helpers.DownloadUtils
import kotlinx.android.synthetic.main.frag_b.*

class FragB : Fragment() {

	companion object {
		val instance: FragB by lazy { FragB() }

	}

	private val cm: ConnectivityManager by lazy {
		context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.frag_b, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// allow webView to load websites using javascript
		webView.settings.javaScriptEnabled = true

		// keep track of the loading process
		webView.webChromeClient = object : WebChromeClient() {
			override fun onProgressChanged(view: WebView?, newProgress: Int) {
				super.onProgressChanged(view, newProgress)

				if (newProgress in 1..99) {
					progressBar.progress = newProgress
					progressBar.visibility = View.VISIBLE
				} else {
					progressBar.visibility = View.GONE
				}

			}
		}

		// keep website loading in app
		webView.webViewClient = object : WebViewClient() {}

		// load input url
		mBtnLoadUrl.setOnClickListener {v ->
			mEtUrlInputLayout.clearFocus()
			val url = mEtUrlInput.text.toString()
			// load website if connected
			if (DownloadUtils.isConnected(cm)) {
				webView.loadUrl(url)
			} else {
				CommonUtils.snackbar(v, "No internet")
			}
		}
	}
}