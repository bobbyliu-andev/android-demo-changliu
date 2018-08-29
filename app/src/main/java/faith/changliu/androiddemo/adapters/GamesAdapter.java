package faith.changliu.androiddemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import faith.changliu.androiddemo.R;
import faith.changliu.androiddemo.data.entities.Game;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.VH> {

	private ArrayList<Game> mGames;

	public GamesAdapter(ArrayList<Game> mGames) {
		this.mGames = mGames;
	}

	@NonNull
	@Override
	public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_game, parent, false);
		return new VH(v);
	}

	@Override
	public void onBindViewHolder(@NonNull VH holder, int position) {
		holder.bind(mGames.get(position));
	}

	@Override
	public int getItemCount() {
		return mGames.size();
	}

	class VH extends RecyclerView.ViewHolder {

		// views
		private TextView mTvGame;
		private TextView mTvPayoff;
		private TextView mTvStatus;

		private VH(View itemView) {
			super(itemView);

			mTvGame = itemView.findViewById(R.id.mTvGame);
			mTvPayoff = itemView.findViewById(R.id.mTvPayoff);
			mTvStatus = itemView.findViewById(R.id.mTvStatus);
		}

		private void bind(Game game) {
			mTvGame.setText(game.getGame());
			mTvPayoff.setText("" + game.getPayoff());
			mTvStatus.setText(game.getStatus());
		}
	}

}
