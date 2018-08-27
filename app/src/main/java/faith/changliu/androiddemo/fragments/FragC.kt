package faith.changliu.androiddemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.data.Test
import faith.changliu.androiddemo.data.TestDataProvider
import faith.changliu.androiddemo.helpers.GET_URL
import faith.changliu.androiddemo.helpers.getContentFromStringUrl
import faith.changliu.androiddemo.helpers.isConnected
import faith.changliu.androiddemo.helpers.snackbar
import kotlinx.android.synthetic.main.frag_c.*
import java.util.concurrent.Executors

class FragC : Fragment() {

	// keep track if data is loaded
	private var mTest: Test? = null

	// singleton
	companion object {
		val instance by lazy { FragC() }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.frag_c, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// bind view if data exists
		mTest?.let {
			bindTestDataWithCardView(it)
		}

		mBtnGetData.setOnClickListener { _ ->
			if (isConnected()) loadTest()
			else view.snackbar(R.string.no_internet)
		}
	}

	/**
	 * Async loading for test
	 */
	private fun loadTest() {
		val executor = Executors.newCachedThreadPool()

		// start loading
		mLoadingTest.visibility = View.VISIBLE
		mBtnGetData.isEnabled = false
		executor.execute {
			val dataString = getContentFromStringUrl(GET_URL)

			activity?.runOnUiThread {

				if (dataString.isNotEmpty()) {
					// convert json to Test instance
					val tests = Gson().fromJson(dataString, TestDataProvider::class.java)

					// check if http exception exists
					if (tests.code in 200..299) {
						// check if Test instance exists
						if (tests.data.isNotEmpty()) {
							tests.data[0].let {
								mTest = it
								bindTestDataWithCardView(it)
								mCvDisplay.snackbar(tests.message + ", Total: ${tests.total}")
							}
						} else {
							// no data
							mCvDisplay.snackbar(R.string.no_data)
						}
					} else {
						mCvDisplay.snackbar(R.string.download_failed)
					}
				} else {
					mCvDisplay.snackbar(R.string.download_failed)
				}

				// reset views
				mLoadingTest.visibility = View.GONE
				mBtnGetData.isEnabled = true
			}
		}
	}

	/**
	 * Bind CardView with data from Test object
	 */
	private fun bindTestDataWithCardView(test: Test) {
		with(test) {
			mTvName.text = className
			mTvId.text = id.toString()
			mTvClassDate.text = class_date
			mTvTeacherId.text = teacher_id.toString()
			mTvPrice.text = "$$price"
			mTvStartTime.text = start_time
			mTvEndTime.text = end_time
			mTvDescription.text = description
		}
	}
}