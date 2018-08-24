package faith.changliu.androiddemo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import faith.changliu.androiddemo.fragments.FragA
import faith.changliu.androiddemo.fragments.FragB
import faith.changliu.androiddemo.fragments.FragC
import faith.changliu.androiddemo.helpers.SimplePageListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

	private lateinit var frags: List<Fragment>
	private val mSectionsPagerAdapter by lazy { SectionsPagerAdapter(supportFragmentManager) }

	private val onPageChangeListener by lazy {
		SimplePageListener { position ->
			when (position) {
				0 -> mNavBottom.selectedItemId =  R.id.nav_1
				1 -> mNavBottom.selectedItemId =  R.id.nav_2
				2 -> mNavBottom.selectedItemId =  R.id.nav_3
			}
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		frags = listOf(
				FragA.instance,
				FragB.instance,
				FragC.instance
		)

		// init views
		mPager.apply {
			adapter = mSectionsPagerAdapter
			addOnPageChangeListener(onPageChangeListener)
		}


		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()

		mNavDrawer.setNavigationItemSelectedListener(this)
		mNavBottom.setOnNavigationItemSelectedListener(this)
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.nav_1 -> {
				mPager.currentItem = 0
			}
			R.id.nav_2 -> {
				mPager.currentItem = 1
			}

			R.id.nav_3 -> {
				mPager.currentItem = 2
			}
		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}

	inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

		override fun getItem(position: Int): Fragment {
			return frags[position]
		}

		override fun getCount(): Int {
			return frags.size
		}
	}
}
