package com.hyc.teumsae.core.domain.model

data class Station(
    val name: String,
    val line: Set<StationLine>
) {
    val isTransferStation: Boolean get() = line.size > 1

    fun isOnLine(stationLine: StationLine): Boolean = stationLine in line
}

enum class StationLine(
    val displayName: String
) {
    LINE_1("1"),
    LINE_2("2"),
    LINE_3("3"),
    LINE_4("4"),
    LINE_5("5"),
    LINE_6("6"),
    LINE_7("7"),
    LINE_8("8"),
    LINE_9("9"),
    AIRPORT("공항철도"),
    BUNDANG("분당"),
    SINBUNDANG("신분당"),
    GYEONGUI_JUNGANG("경의중앙"),
    GYEONGCHUN("경춘"),
}

fun List<Station>.filterByLine(stationLine: StationLine): List<Station> =
    filter { it.isOnLine(stationLine) }
