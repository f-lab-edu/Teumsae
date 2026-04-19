package com.hyc.teumsae.core.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hyc.teumsae.core.domain.model.StationLine
import com.hyc.teumsae.design_system.theme.AppTheme

@Composable
fun StationLine.toColor(): Color = with(AppTheme.colors) {
    when (this@toColor) {
        StationLine.LINE_1 -> stationLine1
        StationLine.LINE_2 -> stationLine2
        StationLine.LINE_3 -> stationLine3
        StationLine.LINE_4 -> stationLine4
        StationLine.LINE_5 -> stationLine5
        StationLine.LINE_6 -> stationLine6
        StationLine.LINE_7 -> stationLine7
        StationLine.LINE_8 -> stationLine8
        StationLine.LINE_9 -> stationLine9
        StationLine.AIRPORT -> stationAirport
        StationLine.BUNDANG -> stationBundang
        StationLine.SINBUNDANG -> stationSinbundang
        StationLine.GYEONGUI_JUNGANG -> stationGyeonguiJungang
        StationLine.GYEONGCHUN -> stationGyeongchun
    }
}
