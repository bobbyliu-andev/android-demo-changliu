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
		val id: Int,
		@SerializedName("class_name")
		val className: String,
		val teacher_id: Int,
		val price: Double,
		val start_time: String,
		val end_time: String,
		val class_date: String,
		val description: String
)

class TestDataProvider(val data: List<Test>)