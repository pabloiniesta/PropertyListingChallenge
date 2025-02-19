package pablo.iniesta.propertylistingchallenge.util

import org.junit.Test
import pablo.iniesta.propertylistingchallenge.util.DateUtils.toSimpleFormat
import java.util.Date
import com.google.common.truth.Truth.assertThat
import java.util.Locale

class DateUtilsTest {

    @Test
    fun `dateToSimpleFormat should return correct spanish format`() {
        val date = Date(1672531200000) // January 1, 2023, 00:00:00 UTC
        val formattedDate = date.toSimpleFormat(Locale("es", "ES"))
        assertThat(formattedDate).isEqualTo("01 ene 2023")
    }

    @Test
    fun `dateToSimpleFormat should return correct english format`() {
        val date = Date(1672531200000) // January 1, 2023, 00:00:00 UTC
        val formattedDate = date.toSimpleFormat(Locale.ENGLISH)
        assertThat(formattedDate).isEqualTo("01 Jan 2023")
    }
}