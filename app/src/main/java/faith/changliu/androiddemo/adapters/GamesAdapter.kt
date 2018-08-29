package faith.changliu.androiddemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import faith.changliu.androiddemo.R
import faith.changliu.androiddemo.data.entities.Game
import kotlinx.android.synthetic.main.cell_game.view.*

class GamesAdapter(private val games: ArrayList<Game>) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_game, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int = games.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(games[position])
	}

	inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
		fun bind(game: Game) {
			itemView.apply {
				mTvGame.text = game.game
				mTvPayoff.text = game.payoff.toString()
				mTvStatus.text = game.status
			}
		}
	}
}