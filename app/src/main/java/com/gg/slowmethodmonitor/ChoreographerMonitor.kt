package com.gg.slowmethodmonitor

import android.util.Log
import android.view.Choreographer

/**
 * @description:
 * @author: GG
 * @createDate: 2023 2.20 0020 19:10
 * @updateUser:
 * @updateDate: 2023 2.20 0020 19:10
 */
class ChoreographerMonitor {
    fun start() {
        Choreographer.getInstance().postFrameCallback(
            object : Choreographer.FrameCallback {
                override fun doFrame(frameTimeNanos: Long) {
                    Choreographer.getInstance().postFrameCallback(this)
                    plusSM()
                }

            }
        )

    }

    private var nowTime = 1L
    private var sm = 1
    private var smResult = 1
    private fun plusSM() {
        //没超过1s就不断++
        val t = System.currentTimeMillis()
        if (nowTime == 1L) {
            nowTime = t
        }
        if (nowTime / 1000 == t / 1000) {
            sm++
        } else if (t / 1000 - nowTime / 1000 >= 1) {
            smResult = sm
            Log.e("TAG", "->$smResult")
            sm = 1
            nowTime = t
        }
    }
}