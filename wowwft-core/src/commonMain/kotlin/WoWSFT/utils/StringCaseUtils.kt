package WoWSFT.utils

private val camelCaseBoundary = "(?<=[a-zA-Z])[A-Z]".toRegex()

fun camelCaseToSnakeCaseLower(value: String): String {
    return camelCaseBoundary.replace(value) { "_${it.value}" }.lowercase()
}
