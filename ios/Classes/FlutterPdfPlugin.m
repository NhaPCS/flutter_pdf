#import "FlutterPdfPlugin.h"
#import <flutter_pdf/flutter_pdf-Swift.h>

@implementation FlutterPdfPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterPdfPlugin registerWithRegistrar:registrar];
}
@end
