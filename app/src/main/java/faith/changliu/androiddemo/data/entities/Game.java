package faith.changliu.androiddemo.data.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class Game {

	private String game;
	private int payoff;
	private String status;

	private final String KEY_GAME = "game";
	private final String KEY_PAYOFF = "payoff";
	private final String KEY_STATUS = "status";

	public Game() {
		game = "";
		payoff = 0;
		status = "";
	}

	public Game(JSONObject jsonObject) throws JSONException {
		game = jsonObject.has(KEY_GAME) ? jsonObject.getString(KEY_GAME) : "N/A";
		payoff = jsonObject.has(KEY_PAYOFF) ? jsonObject.getInt(KEY_PAYOFF) : 0;
		status = jsonObject.has(KEY_STATUS) ? jsonObject.getString(KEY_STATUS) : "N/A";
	}


	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getPayoff() {
		return payoff;
	}

	public void setPayoff(int payoff) {
		this.payoff = payoff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
