package faith.changliu.androiddemo.data;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import faith.changliu.androiddemo.data.entities.Game;
import faith.changliu.androiddemo.helpers.CommonUtils;

public class GameDataProvider {

	/**
	 * Get games from fake data from fakeData.json
	 */
	public static ArrayList<Game> getGamesFromFakeData(Context ctx) {
		String jsonString = CommonUtils.readStringFromAssets(ctx, "fakeData.json");
		return convertStringToGames(jsonString);
	}

	/**
	 * Try convert JSON string to games list
	 *
	 * @param jsonString String input
	 *
	 * @return Returns a new ArrayList<Game>
	 */
	private static ArrayList<Game> convertStringToGames(String jsonString) {
		ArrayList<Game> result = new ArrayList<>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				JSONObject newGameJSON = jsonObject.getJSONObject(keys.next());
				result.add(new Game(newGameJSON));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}
}
