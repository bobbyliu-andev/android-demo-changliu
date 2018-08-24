package faith.changliu.androiddemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.data.TestDataProvider
import faith.changliu.androiddemo.helpers.GET_URL
import kotlinx.android.synthetic.main.frag_c.*
import java.net.URL
import java.util.concurrent.Executors

class FragC : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.frag_c, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mBtnGetData.setOnClickListener {
			_ ->
			val executor = Executors.newCachedThreadPool()
			executor.execute {
				val dataString = URL(GET_URL).readText()
				val tests = Gson().fromJson(dataString, TestDataProvider::class.java)
				activity?.runOnUiThread {
					with(tests.data[0]) {
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
		}
	}
}