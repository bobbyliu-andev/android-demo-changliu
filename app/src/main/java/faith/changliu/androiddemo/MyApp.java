package faith.changliu.androiddemo;

import android.app.Application;

public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		AppContext.instance = new AppContext(this);
	}
}
