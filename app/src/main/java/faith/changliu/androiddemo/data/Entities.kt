package faith.changliu.androiddemo.data

import com.google.gson.annotations.SerializedName
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

data class Test(
		val id: Int = 0,
		@SerializedName("class_name")
		val className: String = "N/A",
		val teacher_id: Int = 0,
		val price: Double = 0.0,
		val start_time: String = "N/A",
		val end_time: String = "N/A",
		val class_date: String = "N/A",
		val description: String = "N/A",
		// added fields
		val elementary: Int = 0,
		val start_date: String = "N/A",
		val end_date: String = "N/A",
		val week_day: Int = 0
)

class TestDataProvider(
		val code: Int = 0,
		var data: List<Test> = listOf(),
		val message: String = "",
		val total: Int = 0
)