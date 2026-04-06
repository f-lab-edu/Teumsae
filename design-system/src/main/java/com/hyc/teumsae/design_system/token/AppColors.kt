package com.hyc.teumsae.design_system.token

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val highlight500: Color = ColorPallet.blue600,
    val highlight400: Color = ColorPallet.blue500,
    val highlight300: Color = ColorPallet.blue400,
    val highlight200: Color = ColorPallet.blue300,
    val highlight100: Color = ColorPallet.blue100,

    val neutralLight500: Color = ColorPallet.white500,
    val neutralLight400: Color = ColorPallet.white400,
    val neutralLight300: Color = ColorPallet.white300,
    val neutralLight200: Color = ColorPallet.white200,
    val neutralLight100: Color = ColorPallet.white100,

    val neutralDark500: Color = ColorPallet.black500,
    val neutralDark400: Color = ColorPallet.black400,
    val neutralDark300: Color = ColorPallet.black300,
    val neutralDark200: Color = ColorPallet.black200,
    val neutralDark100: Color = ColorPallet.black100,

    val supportSuccess300: Color = ColorPallet.green300,
    val supportSuccess200: Color = ColorPallet.green200,
    val supportSuccess100: Color = ColorPallet.green100,

    val supportWarning300: Color = ColorPallet.orange300,
    val supportWarning200: Color = ColorPallet.orange200,
    val supportWarning100: Color = ColorPallet.orange100,

    val supportError300: Color = ColorPallet.red300,
    val supportError200: Color = ColorPallet.red200,
    val supportError100: Color = ColorPallet.red100
)