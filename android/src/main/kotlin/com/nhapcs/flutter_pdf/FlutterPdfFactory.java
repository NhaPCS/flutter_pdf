package com.nhapcs.flutter_pdf;

import android.content.Context;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class FlutterPdfFactory extends PlatformViewFactory {
    BinaryMessenger binaryMessenger;

    public FlutterPdfFactory(BinaryMessenger createArgsCodec) {
        super(StandardMessageCodec.INSTANCE);
        this.binaryMessenger = createArgsCodec;
    }

    @Override
    public PlatformView create(Context context, int i, Object o) {
        return new MyPdfView(context, binaryMessenger, i);
    }
}
