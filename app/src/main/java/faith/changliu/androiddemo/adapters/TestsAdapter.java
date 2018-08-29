package faith.changliu.androiddemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import faith.changliu.androiddemo.R;
import faith.changliu.androiddemo.data.entities.Test;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.VH> {

	private ArrayList<Test> mTests;

	public TestsAdapter(ArrayList<Test> mTests) {
		this.mTests = mTests;
	}

	@NonNull
	@Override
	public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_test, parent, false);
		return new VH(v);
	}

	@Override
	public void onBindViewHolder(@NonNull VH holder, int position) {
		holder.bind(mTests.get(position));
	}

	@Override
	public int getItemCount() {
		return mTests.size();
	}

	class VH extends RecyclerView.ViewHolder {

		// views
		private TextView mTvName;
		private TextView mTvId;
		private TextView mTvStartDate;
		private TextView mTvEndDate;
		private TextView mTvTeacherId;
		private TextView mTvPrice;
		private TextView mTvStartTime;
		private TextView mTvEndTime;
		private TextView mTvDescription;
		private TextView mTvElementary;
		private TextView mTvWeekDay;

		private VH(View itemView) {
			super(itemView);

			mTvName = itemView.findViewById(R.id.mTvName);
			mTvId = itemView.findViewById(R.id.mTvId);
			mTvStartDate = itemView.findViewById(R.id.mTvStartDate);
			mTvEndDate = itemView.findViewById(R.id.mTvEndDate);
			mTvTeacherId = itemView.findViewById(R.id.mTvTeacherId);
			mTvPrice = itemView.findViewById(R.id.mTvPrice);
			mTvStartTime = itemView.findViewById(R.id.mTvStartTime);
			mTvEndTime = itemView.findViewById(R.id.mTvEndTime);
			mTvDescription = itemView.findViewById(R.id.mTvDescription);
			mTvElementary = itemView.findViewById(R.id.mTvElementary);
			mTvWeekDay = itemView.findViewById(R.id.mTvWeekDay);
		}

		private void bind(Test test) {
			mTvName.setText(test.getClassName());
			mTvId.setText("ID: " + test.getId());
			mTvStartDate.setText(test.getStart_date());
			mTvEndDate.setText(test.getEnd_date());
			mTvTeacherId.setText("Teacher ID: " + test.getTeacher_id());
			mTvPrice.setText("$" + test.getPrice());
			mTvStartTime.setText(test.getStart_time());
			mTvEndTime.setText(test.getEnd_time());
			mTvDescription.setText(test.getDescription());
			mTvElementary.setText("Elementary: " + test.getElementary());
			mTvWeekDay.setText("Week Day: " + test.getWeek_day());
		}

	}

	public void updateAllData(List<Test> tests) {
		mTests.clear();
		mTests.addAll(tests);
		notifyDataSetChanged();
	}

}
