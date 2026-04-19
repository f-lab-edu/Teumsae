package com.hyc.teumsae.design_system.token

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Immutable
data class AppTypography(
    // Heading
    val headline1: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 36.sp,
    ),
    val headline2: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
    ),
    val headline3: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
    ),
    val headline4: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
    ),

    // Body
    val body1: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    val body2: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
    ),
    val body3: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),

    // Action (Button / Link)
    val action1: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    val action2: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),
    val action3: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),

    // Field
    val field1: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    val field2: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),

    // Caption
    val caption1: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),
    val caption2: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp,
    ),
    val caption3: TextStyle = TextStyle(
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 12.sp,
    ),
)