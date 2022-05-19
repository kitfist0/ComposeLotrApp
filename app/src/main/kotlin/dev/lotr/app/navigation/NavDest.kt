package dev.lotr.app.navigation

enum class NavDest {
    BOOKS,
    CHAPTERS,
}

fun NavDest.toRoute(): String {
    return name.lowercase()
}

fun NavDest.toRouteWithParam(param: String): String {
    return "${toRoute()}/$param"
}

fun NavDest.toRouteWithParamName(paramName: String): String {
    return "${toRoute()}/{$paramName}"
}
