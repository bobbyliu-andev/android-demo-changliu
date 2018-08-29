package faith.changliu.androiddemo;

import android.content.Context;
import android.content.ContextWrapper;

public class AppContext extends ContextWrapper {

	public static AppContext instance;

	public AppContext(Context base) {
		super(base);
	}
}
