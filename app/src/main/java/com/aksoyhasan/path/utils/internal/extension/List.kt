import androidx.lifecycle.LiveData

fun List<LiveData<*>?>.filterFalseOrNullOrBlank(): List<LiveData<*>?> {
    return this.filter {
        it?.value?.let {
            when {
                it is String -> it.isBlank()
                it is Boolean -> it.not()
                else -> true
            }
        } ?: true
    }
}

inline fun <T, R : Comparable<R>> List<T>.castSafeSortedBy(crossinline selector: (T) -> R?): List<T> {
    return if (this.isNullOrEmpty().not()) {
        sortedWith(compareBy(selector))
    } else {
        this
    }
}