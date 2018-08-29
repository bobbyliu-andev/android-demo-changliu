package faith.changliu.androiddemo.data

import faith.changliu.androiddemo.AppContext
import faith.changliu.androiddemo.helpers.CommonUtils
import faith.changliu.androiddemo.helpers.FILE_FAKE_DATA
import org.json.JSONException
import org.json.JSONObject

object GameDataProvider {

	/**
	 * Get games from fake data
	 *
	 * @param fileName Name of the file to be read inside assets, default to fakeData.json
	 */
	fun getGamesFromFakeData(fileName: String = FILE_FAKE_DATA): ArrayList<Game> {
		val jsonString = CommonUtils.readStringFromAssets(AppContext.instance, fileName)
		return convertStringToGames(jsonString)
	}

	/**
	 * Try convert JSON string to games list
	 *
	 * @param jsonString String input
	 *
	 * @return Returns a new ArrayList<Game>
	 */
	private fun convertStringToGames(jsonString: String): ArrayList<Game> {
		val result = arrayListOf<Game>()
		try {
			val jsonObject = JSONObject(jsonString)
			jsonObject.keys().forEach { key ->
				(jsonObject[key] as? JSONObject)?.let {
					result.add(Game(it))
				}
			}
		} catch (ex: JSONException) {
			ex.printStackTrace()
		} catch (ex: Exception) {
			ex.printStackTrace()
		}

		return result
	}
}