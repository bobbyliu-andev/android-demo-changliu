package faith.changliu.androiddemo.helpers

import faith.changliu.androiddemo.R

/**
 * Run block if string is not empty, else prompt user
 * @param onNotEmpty lambda function taking the not empty string as input
 */
inline fun String.onNotEmptyString(onNotEmpty: (String) -> Unit) {
	if (isNotEmpty()) onNotEmpty(this)
	else toast(R.string.no_data)
}
