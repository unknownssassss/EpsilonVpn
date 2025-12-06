package com.epsilonvpn.util

import android.content.Context
import java.io.File
import java.io.InputStream

object XrayHandler {

    fun startXray(context: Context, configPath: String) {
        // TODO: استخراج باینری Xray از assets و اجرای آن با ProcessBuilder
        val xrayFile = File(context.filesDir, "xray")
        if (!xrayFile.exists()) {
            context.assets.open("xray").use { input ->
                xrayFile.outputStream().use { output -> input.copyTo(output) }
            }
            xrayFile.setExecutable(true)
        }

        // مثال اجرای ProcessBuilder
        ProcessBuilder(xrayFile.absolutePath, "-c", configPath)
            .start()
    }
}
