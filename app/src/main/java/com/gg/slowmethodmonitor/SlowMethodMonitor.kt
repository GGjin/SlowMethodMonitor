package com.gg.slowmethodmonitor

import android.os.Looper
import android.util.Log
import android.util.Printer

/**
 * @description:
 * @author: GG
 * @createDate: 2023 2.20 0020 18:43
 * @updateUser:
 * @updateDate: 2023 2.20 0020 18:43
 */
class SlowMethodMonitor {

    companion object {
        private val PRINTER: Printer = object : Printer {
            var startTime = 0L
            override fun println(s: String) {
                if (s.startsWith(">>>>>")) {
                    startTime = System.currentTimeMillis()
                    //埋炸弹
                    LogMonitor.startMonitor()
                } else {
                    val executeTime = System.currentTimeMillis() - startTime
                    if (executeTime > 100) {
                        Log.e("TAG", "有耗时函数")
                    }
                    // 直接拿堆栈 ,不行，因为方法执行完了
                    // getStackInfo(Thread.currentThread());
                    // 直接从启动的线程里面去获取堆栈信息，这种应该是可以的
                    //拆炸弹
                    LogMonitor.removeMonitor()
                }
            }
        }


        fun getStackInfo(currentThread: Thread): String {
            val stackTraceElement = currentThread.stackTrace
            stackTraceElement.forEach {
                Log.e("TAG", it.toString())
            }
            return ""
        }
    }

    fun start() {
        Looper.getMainLooper().setMessageLogging(PRINTER)
    }

}