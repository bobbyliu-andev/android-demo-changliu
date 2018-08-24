package faith.changliu.androiddemo.data

import faith.changliu.androiddemo.AppContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object GameDataProvider {

	private fun getGamesJsonString(): String {
		var result = ""
		try {
			AppContext.assets.open("fakeData.json").bufferedReader().use { bufferedReader ->
				result = bufferedReader.readText()
			}
		} catch (ex: IOException) {
			ex.printStackTrace()
		} catch (ex: Exception) {
			ex.printStackTrace()
		}

		return result
	}

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

	fun getGames(): ArrayList<Game> {
		val jsonString = getGamesJsonString()
		return convertStringToGames(jsonString)
	}
}