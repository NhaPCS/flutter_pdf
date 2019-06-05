import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_pdf/flutter_pdf.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_pdf');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
//    expect(await FlutterPdf.platformVersion, '42');
  });
}
