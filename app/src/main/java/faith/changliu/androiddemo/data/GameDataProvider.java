package faith.changliu.androiddemo.data;

import android.content.Context;

import java.util.ArrayList;

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
//		try {
//			JSONObject jsonObject = new JSONObject(jsonString);
//			while (jsonObject.keys().hasNext()) {
//				String key = jsonObject.keys().next();
//				JSONObject newGameJSON = jsonObject.getJSONObject(key);
//				result.add(new Game(newGameJSON));
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}

		return result;
	}
}
