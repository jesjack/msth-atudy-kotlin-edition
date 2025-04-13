package com.example.msthatudykotlinedition.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import com.shirishkoirala.fontawesome.IconDrawable
import androidx.core.graphics.createBitmap

// Convierte un Drawable en un Bitmap
fun drawableToBitmap(drawable: Drawable): Bitmap {
    val defaultWidth = 100
    val defaultHeight = 100

    val intrinsicWidth = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else defaultWidth
    val intrinsicHeight = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else defaultHeight

    // Aumentar el tamaño del lienzo para evitar recortes
    val extraPadding = 40 // Ajusta este valor según sea necesario
    val width = intrinsicWidth + extraPadding
    val height = intrinsicHeight + extraPadding

    val bitmap = createBitmap(width, height)
    val canvas = Canvas(bitmap)

    // Centrar el Drawable en el lienzo
    val left = extraPadding / 2
    val top = extraPadding / 2
    val right = left + intrinsicWidth
    val bottom = top + intrinsicHeight
    drawable.setBounds(left, top, right, bottom)
    drawable.draw(canvas)

    return bitmap
}

// Genera un Bitmap a partir de un ícono
@Composable
fun bitmap(icon: String): Bitmap {
    val context = LocalContext.current
    val drawable = remember { IconDrawable(context, icon) }
    return drawableToBitmap(drawable)
}

// Genera un BitmapPainter a partir de un ícono
@Composable
fun icon(icon: String): BitmapPainter {
    return BitmapPainter(bitmap(icon).asImageBitmap())
}
