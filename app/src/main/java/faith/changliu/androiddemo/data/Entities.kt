package faith.changliu.androiddemo.data

import faith.changliu.androiddemo.data.entities.Test
import faith.changliu.androiddemo.helpers.KEY_GAME
import faith.changliu.androiddemo.helpers.KEY_PAYOFF
import faith.changliu.androiddemo.helpers.KEY_STATUS
import org.json.JSONObject

data class Game(
		var game: String = "",
		var payoff: Int = 0,
		var status: String = ""
) {
	constructor(jsonObject: JSONObject) : this() {
		game = (jsonObject[KEY_GAME] as? String) ?: "N/A"
		payoff = (jsonObject[KEY_PAYOFF] as? Int) ?: 0
		status = (jsonObject[KEY_STATUS] as? String) ?: "N/A"
	}
}

class TestDataProvider(
		val code: Int = 0,
		var data: List<Test> = listOf(),
		val message: String = "",
		val total: Int = 0
)