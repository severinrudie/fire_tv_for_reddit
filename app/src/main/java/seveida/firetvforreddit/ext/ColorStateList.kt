package seveida.firetvforreddit.ext

import android.content.res.ColorStateList

/**
 * Allows use of named params for this confusing API
 */
// Not technically an ext fun, but it's not currently possible to create static
// ext funs for Java classes
fun createColorStateList(states: Array<IntArray>, colors: IntArray): ColorStateList =
        ColorStateList(states, colors)
