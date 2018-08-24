package faith.changliu.androiddemo

import android.app.Application
import android.content.ContextWrapper

class MyApp : Application() {
	override fun onCreate() {
		super.onCreate()
		INSTANCE = this
	}
}
private lateinit var INSTANCE: MyApp
object AppContext : ContextWrapper(INSTANCE)