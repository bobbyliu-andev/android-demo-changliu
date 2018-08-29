package faith.changliu.androiddemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import faith.changliu.androiddemo.R;
import faith.changliu.androiddemo.adapters.GamesAdapter;
import faith.changliu.androiddemo.data.GameDataProvider;

public class FragA extends Fragment {

    private RecyclerView mRcvGames;

    public static FragA newInstance() {
        return new FragA();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_a, container, false);
        mRcvGames = view.findViewById(R.id.mRcvGames);
        mRcvGames.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvGames.setAdapter(new GamesAdapter(GameDataProvider.getGamesFromFakeData(getContext())));

        return view;
    }
}
