package pablo.iniesta.propertylistingchallenge.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun Date.toSimpleFormat(locale: Locale): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", locale)
        return dateFormat.format(this)
    }
}