package com.nhapcs.flutter_pdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.IOException;
import java.net.URL;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class MyPdfView extends PDFView implements PlatformView, MethodChannel.MethodCallHandler {
    private final MethodChannel methodChannel;

    public MyPdfView(Context context, BinaryMessenger messenger, int id) {
        super(context, null);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        methodChannel = new MethodChannel(messenger, "plugins.com.nhapcs.flutter_pdf/flutter_pdf_" + id);
        methodChannel.setMethodCallHandler(this);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onMethodCall(final MethodCall methodCall, MethodChannel.Result result) {
        Log.e("METHOD ", methodCall.method + "  " + methodCall.arguments);
        if ("setUrl".equals(methodCall.method)) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        fromStream(new URL((String) methodCall.arguments).openStream()).load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
            fromUri(Uri.parse((String) methodCall.arguments)).load();
        } else {
            result.notImplemented();
        }
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void dispose() {

    }
}
