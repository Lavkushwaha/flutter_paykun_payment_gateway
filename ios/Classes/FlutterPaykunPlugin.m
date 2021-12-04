#import "FlutterPaykunPlugin.h"
#if __has_include(<flutter_paykun/flutter_paykun-Swift.h>)
#import <flutter_paykun/flutter_paykun-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_paykun-Swift.h"
#endif

@implementation FlutterPaykunPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterPaykunPlugin registerWithRegistrar:registrar];
}
@end
