package faith.changliu.androiddemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.adapters.TestsAdapter
import faith.changliu.androiddemo.data.Test
import faith.changliu.androiddemo.data.TestDataProvider
import faith.changliu.androiddemo.helpers.*
import kotlinx.android.synthetic.main.frag_c.*
import java.util.concurrent.Executors

class FragC : Fragment() {
	
	// adapter for tests
	private val mAdapter = TestsAdapter(arrayListOf())
	
	// singleton
	companion object {
		val instance by lazy { FragC() }
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.frag_c, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		// init recycler view
		mRcvTests.layoutManager = LinearLayoutManager(context)
		mRcvTests.adapter = mAdapter
		
		mBtnGetData.setOnClickListener { v ->
			v.onConnected {
				loadTest()
			}
		}
	}
	
	/**
	 * Async loading for test
	 */
	private fun loadTest() {
		// get background thread
		val executor = Executors.newCachedThreadPool()
		
		// start loading
		mLoadingTest.visibility = View.VISIBLE
		mBtnGetData.isEnabled = false
		executor.execute {
			val dataString = getContentFromStringUrl(GET_URL)
			
			activity?.runOnUiThread {
				
				dataString.onNotEmptyString { dataStringChecked ->
					// convert json to Test instance
					val tests = Gson().fromJson(dataStringChecked, TestDataProvider::class.java)
					
					// check if http exception exists
					onNoHttpError(tests.code) {
						mAdapter.updateAllData(tests.data)
					}
				}
				
				// reset views
				mLoadingTest.visibility = View.GONE
				mBtnGetData.isEnabled = true
			}
		}
	}
	
	/**
	 * Bind CardView with data from Test object
	 * @param test Data to be bound
	 */
	private fun bindTestDataWithCardView(test: Test) {
		with(test) {
//			mTvName.text = className
//			mTvId.text = id.toString()
//			mTvClassDate.text = class_date
//			mTvTeacherId.text = teacher_id.toString()
//			mTvPrice.text = "$$price"
//			mTvStartTime.text = start_time
//			mTvEndTime.text = end_time
//			mTvDescription.text = description
		}
	}
}