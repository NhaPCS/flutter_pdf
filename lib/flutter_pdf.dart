import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void FlutterPdfCreatedCallback(FlutterPdfController controller);


class FlutterPdf extends StatefulWidget {
  FlutterPdf({
    Key key,
    this.onMathjaxViewCreated,
  }) : super(key: key);

  final FlutterPdfCreatedCallback onMathjaxViewCreated;

  @override
  State<StatefulWidget> createState() => _FlutterPdfState();
}

class _FlutterPdfState extends State<FlutterPdf> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'plugins.com.nhapcs.flutter_pdf/flutter_pdf',
        onPlatformViewCreated: _onPlatformViewCreated,
        creationParams: <String, dynamic>{
        },
        creationParamsCodec: new StandardMessageCodec(),
      );
    }
//    if (defaultTargetPlatform == TargetPlatform.iOS) {
//      return UiKitView(
//        viewType: 'plugins.com.nhapcs.flutter_pdf/flutter_pdf',
//        onPlatformViewCreated: _onPlatformViewCreated,
//        creationParams: <String, dynamic>{
//          "fontSize": widget.fontSize,
//        },
//        creationParamsCodec: new StandardMessageCodec(),
//      );
//    }

    return Text(
        '$defaultTargetPlatform is not yet supported by the mathjax_view plugin');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onMathjaxViewCreated == null) {
      return;
    }

    widget.onMathjaxViewCreated(FlutterPdfController._(id));
  }
}

class FlutterPdfController {
  FlutterPdfController._(int id)
      : _channel = new MethodChannel('plugins.com.nhapcs.flutter_pdf/flutter_pdf_$id');

  final MethodChannel _channel;

  Future<void> setUrl(String text) async {
    assert(text != null);
    return _channel.invokeMethod('setUrl', text);
  }
}
