package com.myaxa.core.ui.throttledClickListener

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

fun Modifier.throttledClickable(
    throttleDurationMs: Long = ThrottledClickListener.STANDARD_THROTTLE_DURATION_MS,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "throttledClickable"
        properties["throttleDurationMs"] = throttleDurationMs
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    this.throttledClickable(
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() },
        throttleDurationMs = throttleDurationMs,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}

fun Modifier.throttledClickable(
    indication: Indication?,
    interactionSource: MutableInteractionSource,
    throttleDurationMs: Long = ThrottledClickListener.STANDARD_THROTTLE_DURATION_MS,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "throttledClickable"
        properties["indication"] = indication
        properties["interactionSource"] = interactionSource
        properties["throttleDurationMs"] = throttleDurationMs
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val multipleEventsCutter = remember { ThrottledClickListener(throttleDurationMs) }
    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { multipleEventsCutter.processEvent { onClick() } },
        role = role,
        indication = indication,
        interactionSource = interactionSource,
    )
}
