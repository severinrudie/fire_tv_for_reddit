package seveida.firetvforreddit

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import io.reactivex.Observable

object ColorAnimator {

    // TODO can i make a version of this that works with getCurrent: () -> ColorStateList?
    fun get(getCurrent: () -> Int?, to: Int): Observable<Int> {
        val from = getCurrent() ?: to
        var done = false
        return Observable.create { emitter ->
            ValueAnimator.ofObject(ArgbEvaluator(), from, to).apply {
                duration = 1000
                addUpdateListener {
                    emitter.onNext(it.animatedValue as Int)
                }
                doOnCancel {
                    if (!done) emitter.onComplete()
                    done = true
                }
                doOnEnd {
                    if (!done) emitter.onComplete()
                    done = true
                }
                start()
            }
        }
    }
}
