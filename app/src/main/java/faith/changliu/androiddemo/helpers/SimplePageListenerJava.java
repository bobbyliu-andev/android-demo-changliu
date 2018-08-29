package faith.changliu.androiddemo.helpers;

import android.support.v4.view.ViewPager;

public class SimplePageListenerJava implements ViewPager.OnPageChangeListener {

	public SimplePageListenerJava(PageChangeCallback mListener) {
		this.mListener = mListener;
	}

	private PageChangeCallback mListener;

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mListener.onSelectPage(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	public interface PageChangeCallback {
		void onSelectPage(int position);
	}
}
