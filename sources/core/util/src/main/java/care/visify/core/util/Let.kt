package care.visify.core.util

inline fun<T, R, D> multiLet(first: T?, second: R?, block: (T, R) -> D): D? {
    if (first == null || second == null) return null
    return block(first, second)
}

inline fun<T, R, A, B, D> multiLet(first: T?, second: R?, third: A?, fourth: B?,  block: (T, R, A, B) -> D): D? {
    if (first == null || second == null || third == null || fourth == null) return null
    return block(first, second, third, fourth)
}

fun<T> List<Pair<T, T>>.unpack(): List<T> {
    return map { pair -> pair.first } + last().second
}