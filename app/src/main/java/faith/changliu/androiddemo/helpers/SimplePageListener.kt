package faith.changliu.androiddemo.helpers

import android.support.v4.view.ViewPager

class SimplePageListener(private val onSelectPage: (Int) -> Unit) : ViewPager.OnPageChangeListener {
	override fun onPageScrollStateChanged(state: Int) {}

	override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

	override fun onPageSelected(position: Int) {
		onSelectPage(position)
	}
}