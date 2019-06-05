package com.nhapcs.flutter_pdf

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPdfPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      registrar.platformViewRegistry().registerViewFactory("plugins.com.nhapcs.flutter_pdf/flutter_pdf", FlutterPdfFactory(registrar.messenger()))
      val channel = MethodChannel(registrar.messenger(), "flutter_pdf")
      channel.setMethodCallHandler(FlutterPdfPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }
}
