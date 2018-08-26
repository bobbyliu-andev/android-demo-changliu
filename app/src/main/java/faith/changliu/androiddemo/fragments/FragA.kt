package faith.changliu.androiddemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.adapters.GamesAdapter
import faith.changliu.androiddemo.data.GameDataProvider
import kotlinx.android.synthetic.main.frag_a.view.*

class FragA : Fragment() {

	companion object {
		val instance: FragA by lazy {
			FragA()
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.frag_a, container, false)
		view.mRcvGames.apply {
			layoutManager = LinearLayoutManager(context)
			val games = GameDataProvider.getGamesFromFakeData()
			adapter = GamesAdapter(games)
		}

		return view
	}
}