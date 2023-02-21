package com.gg.slowmethodmonitor

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * @description:
 * @author: GG
 * @createDate: 2023 2.20 0020 18:51
 * @updateUser:
 * @updateDate: 2023 2.20 0020 18:51
 */
object LogMonitor : Runnable {

    private const val TIME_BLOCK: Long = 200

    private val mHandlerThread = HandlerThread("log")

    private val mHandler: Handler by lazy { Handler(mHandlerThread.looper) }

    init {
        mHandlerThread.start()
    }

    fun startMonitor() {
        mHandler.postDelayed(this, TIME_BLOCK)
    }

    fun removeMonitor() {
        mHandler.removeCallbacks(this)
    }

    override fun run() {
        SlowMethodMonitor.getStackInfo(Looper.getMainLooper().thread)
    }
}