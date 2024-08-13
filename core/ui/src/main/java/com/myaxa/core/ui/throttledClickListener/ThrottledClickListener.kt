package com.myaxa.core.ui.throttledClickListener

/**
 * Designed to prevent multiple clicks on a button
 *
 * @see <a href="https://al-e-shevelev.medium.com/how-to-prevent-multiple-clicks-in-android-jetpack-compose-8e62224c9c5e">
 *          How to prevent multiple clicks in Android Jetpack Compose
 *      </a>
 */
internal class ThrottledClickListener(private val duration: Long = STANDARD_THROTTLE_DURATION_MS) {

    companion object {
        const val STANDARD_THROTTLE_DURATION_MS = 700L
    }

    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= duration) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}
