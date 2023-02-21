package com.gg.slowmethodmonitor

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slowMethodMonitor = SlowMethodMonitor()
        slowMethodMonitor.start()

        val choreographerMonitor = ChoreographerMonitor()
        choreographerMonitor.start()
        findViewById<Button>(R.id.button).setOnClickListener {
            try {
                Thread.sleep(200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}