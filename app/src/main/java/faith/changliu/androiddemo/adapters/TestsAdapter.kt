package faith.changliu.androiddemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.data.Test
import kotlinx.android.synthetic.main.cell_test.view.*

class TestsAdapter(private val mTests: ArrayList<Test>) : RecyclerView.Adapter<TestsAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_test, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int = mTests.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(mTests[position])
	}

	inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
		
		/**
		 * Bind CardView with data from Test object
		 * @param test Data to be bound
		 */
		fun bind(test: Test) {
			itemView.apply {
				mTvName.text = test.className
				mTvId.text = test.id.toString()
				mTvStartDate.text = test.start_date
				mTvEndDate.text = test.end_date
				mTvTeacherId.text = test.teacher_id.toString()
				mTvPrice.text = "$${test.price}"
				mTvStartTime.text = test.start_time
				mTvEndTime.text = test.end_time
				mTvDescription.text = test.description
				mTvElementary.text = test.elementary.toString()
				mTvWeekDay.text = test.week_day.toString()
			}
		}
	}
	
	fun updateAllData(tests: List<Test>) {
		mTests.apply {
			clear()
			addAll(tests)
		}
		notifyDataSetChanged()
	}
}