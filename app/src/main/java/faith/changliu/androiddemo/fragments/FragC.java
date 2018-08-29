package faith.changliu.androiddemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import faith.changliu.androiddemo.R;
import faith.changliu.androiddemo.adapters.TestsAdapter;
import faith.changliu.androiddemo.data.TestDataProvider;
import faith.changliu.androiddemo.data.entities.Test;
import faith.changliu.androiddemo.helpers.CommonUtils;
import faith.changliu.androiddemo.helpers.ConstantsKt;
import faith.changliu.androiddemo.helpers.DownloadUtils;

public final class FragC extends Fragment implements View.OnClickListener {

	// member vars
	private TestsAdapter mAdapter;

	public void setAdapter(TestsAdapter mAdapter) {
		this.mAdapter = mAdapter;
	}

	// views
	private RecyclerView mRcvTests;
	private TextView mTvEmpty;
	private ProgressBar mLoadingTest;
	private Button mBtnGetData;

	// factory
	public static FragC newInstance() {
		// set initial args, member, etc.
		FragC fragC = new FragC();
		fragC.setAdapter(new TestsAdapter(new ArrayList<Test>()));
		return fragC;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_c, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// init views
		mTvEmpty = view.findViewById(R.id.mTvEmpty);
		mRcvTests = view.findViewById(R.id.mRcvTests);
		mLoadingTest = view.findViewById(R.id.mLoadingTest);
		mBtnGetData = view.findViewById(R.id.mBtnGetData);
		mBtnGetData.setOnClickListener(this);

		// setup recycler view
		mRcvTests.setLayoutManager(new LinearLayoutManager(getContext()));
		mRcvTests.setAdapter(mAdapter);
		updateEmptyView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mBtnGetData:
				if (DownloadUtils.isConnected()) {
					loadTest();
				} else {
					showSnackBar(R.string.no_internet);
				}
				break;
			default:
		}
	}

	private void loadTest() {
		ExecutorService executor = Executors.newCachedThreadPool();
		mLoadingTest.setVisibility(View.VISIBLE);
		mBtnGetData.setEnabled(false);
		executor.execute((new Runnable() {
			public final void run() {
				final String dataString = CommonUtils.getContentFromStringUrl(ConstantsKt.GET_URL);
				if (getActivity() == null) return;
				getActivity().runOnUiThread((new Runnable() {
					public final void run() {

						if (!dataString.isEmpty()) {
							TestDataProvider tests = (new Gson()).fromJson(dataString, TestDataProvider.class);

							// check status code
							if (tests.getCode() >= 200 && tests.getCode() < 300) {
								mAdapter.updateAllData(tests.getData());
								CommonUtils.snackbar(mBtnGetData, tests.getMessage() + ", total: " + tests.getTotal());
								updateEmptyView();
							} else {
								showSnackBar(R.string.no_data);
							}
						} else {
							showSnackBar(R.string.no_data);
						}

						mLoadingTest.setVisibility(View.GONE);
						mBtnGetData.setEnabled(true);
					}
				}));
			}
		}));
	}

	public final void updateEmptyView() {
		if (mAdapter.getItemCount() == 0) {
			mTvEmpty.setVisibility(View.VISIBLE);
		} else {
			mTvEmpty.setVisibility(View.GONE);
		}
	}

	private void showSnackBar(@StringRes int stringResId) {
		if (getContext() == null) {
			return;
		}
		CommonUtils.snackbar(mBtnGetData, getContext(), stringResId);
	}
}
