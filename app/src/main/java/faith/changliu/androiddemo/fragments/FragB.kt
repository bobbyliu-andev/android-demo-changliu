package faith.changliu.androiddemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import faith.changliu.androiddemo.R
import kotlinx.android.synthetic.main.frag_b.*

class FragB : Fragment() {


	companion object {
		val instance: FragB by lazy { FragB() }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.frag_b, container, false)

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		webView.settings.javaScriptEnabled = true
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
		webView.webViewClient = object : WebViewClient() {}

		mBtnLoadUrl.setOnClickListener {
			mEtUrlInput.clearFocus()
			val url = mEtUrlInput.text.toString()
			webView.loadUrl(url)
		}
	}
}