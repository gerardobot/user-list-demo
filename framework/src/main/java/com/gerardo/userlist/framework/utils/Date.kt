import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String.toDateFromPattern(pattern: DatePattern = DatePattern.DEFAULT): LocalDate? = runCatching {
    LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern.value))
}.getOrNull()

@JvmInline
internal value class DatePattern(val value: String) {
    companion object {
        val DEFAULT = DatePattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }
}
