#import "FlutterWalletsPlugin.h"
#if __has_include(<flutter_wallets/flutter_wallets-Swift.h>)
#import <flutter_wallets/flutter_wallets-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_wallets-Swift.h"
#endif

@implementation FlutterWalletsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterWalletsPlugin registerWithRegistrar:registrar];
}
@end
