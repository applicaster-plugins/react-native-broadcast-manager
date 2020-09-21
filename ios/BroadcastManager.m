#import "BroadcastManager.h"

@implementation BroadcastManager

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sendBroadcastEvent:(nonnull NSString*)eventName
                 properties:(nonnull NSDictionary*)properties)
{
        [[NSNotificationCenter defaultCenter] postNotificationName:eventName
                                                            object:nil
                                                          userInfo:properties];
}

@end
