package nick.mirosh.androidsamples.ui.coroutines

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.PrintWriter
import java.io.StringWriter

suspend fun runUpdatesIn(
    flow: MutableStateFlow<Float>,
    delayMs: Long = 500L,
) {
    var counter = 0
    while (counter <= 100 ) {
        delay(delayMs)
        flow.value = counter / 100f
        counter += 10
    }
}

fun Throwable.logStackTrace(tag: String) {
    val sw = StringWriter()
    this.printStackTrace(PrintWriter(sw))
    val exceptionAsString = sw.toString()
    Log.e(tag, exceptionAsString)
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
) {

    val progressAnimDuration = 300
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = progressAnimDuration,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )
    LinearProgressIndicator(
        progress = progressAnimation,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}
