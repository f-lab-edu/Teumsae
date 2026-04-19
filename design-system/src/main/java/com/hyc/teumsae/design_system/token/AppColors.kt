package com.hyc.teumsae.design_system.token

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val primary600: Color = ColorPallet.blue600,
    val primary500: Color = ColorPallet.blue500,
    val primary400: Color = ColorPallet.blue400,
    val primary300: Color = ColorPallet.blue300,
    val primary200: Color = ColorPallet.blue200,
    val primary100: Color = ColorPallet.blue100,

    val neutral100: Color = ColorPallet.white100,
    val neutral200: Color = ColorPallet.white200,
    val neutral300: Color = ColorPallet.white300,
    val neutral400: Color = ColorPallet.white400,
    val neutral500: Color = ColorPallet.white500,
    val neutral600: Color = ColorPallet.black100,
    val neutral700: Color = ColorPallet.black200,
    val neutral800: Color = ColorPallet.black300,
    val neutral900: Color = ColorPallet.black400,
    val neutral1000: Color = ColorPallet.black500,

    val successStrong: Color = ColorPallet.green300,
    val successDefault: Color = ColorPallet.green200,
    val successLight: Color = ColorPallet.green100,

    val warningStrong: Color = ColorPallet.orange300,
    val warningDefault: Color = ColorPallet.orange200,
    val warningLight: Color = ColorPallet.orange100,

    val errorStrong: Color = ColorPallet.red300,
    val errorDefault: Color = ColorPallet.red200,
    val errorLight: Color = ColorPallet.red100,

    val stationLine1: Color = ColorPallet.stationLine1,
    val stationLine2: Color = ColorPallet.stationLine2,
    val stationLine3: Color = ColorPallet.stationLine3,
    val stationLine4: Color = ColorPallet.stationLine4,
    val stationLine5: Color = ColorPallet.stationLine5,
    val stationLine6: Color = ColorPallet.stationLine6,
    val stationLine7: Color = ColorPallet.stationLine7,
    val stationLine8: Color = ColorPallet.stationLine8,
    val stationLine9: Color = ColorPallet.stationLine9,
    val stationAirport: Color = ColorPallet.stationAirport,
    val stationBundang: Color = ColorPallet.stationBundang,
    val stationSinbundang: Color = ColorPallet.stationSinbundang,
    val stationGyeonguiJungang: Color = ColorPallet.stationGyeonguiJungang,
    val stationGyeongchun: Color = ColorPallet.stationGyeongchun,
)