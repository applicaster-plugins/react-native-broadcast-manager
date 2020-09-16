# quick-brick-broadcast-manager

*Built by Applicaster*

**Supports:** *iOS and Android*
*Current Version: 0.2.0 (Android) and 0.1.0 (iOS)*

## About

A bridge for sending a broadcast from RN code to native code

### When to use?

Use this plugin when you need to send general data between rn and native modules

## Process description
After registering to the ```send_broadcast_from_rn``` intent action in the Native side, we can use the
```sendBroadcastEvent``` ReactMethod in our RN code to send any event we want (including event properties
as a JSON Object).

## Configuration

None


## Usage (RN Side):

1. Add as a dependency in your rn package:
```
"@applicaster/react-native-broadcast-manager":x.x.x
```

2. Add the following lines in its' manifest:
```
"project_dependencies": [
    {
      "react-native-broadcast-manager": "./quick_brick/node_modules/@applicaster/react-native-broadcast-manager/Android"
    }
  ]
```
and in the manifest's api section:
```
"class_name": "******", (if don't have class to initiate the plugin use "com.applicaster.reactnative.plugins.APReactNativeAdapter")
"react_packages": [
      "com.applicaster.react.BroadcastManagerAPIPackage"
    ]
```
3. Add the following import in the relevant js file:
```
import { sendBroadcastEvent } from '@applicaster/react-native-broadcast-manager';
```

After that you can call sendBroadcastEvent with an event_key (String) and even_properties (type JSON) that later can be caught in
the native code and handled upon receiving.

## Usage (Android):

In the native module we add the following lines:
```
    public static final String SEND_BROADCAST_ACTION = "send_broadcast_from_rn";

    // Catch the send_broadcast_from_rn action sent from the RN Broadcast Manager
    LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            <Enter desired code here>
        }
    }, new IntentFilter(SEND_BROADCAST_ACTION));
```

## Usage (iOS):



# Deployment

1. Update version for desired module to deploy:
```
$MODULE/package.json
```
2. Deploy from the shell to npm:
```
NPM_TOKEN=$NPM_TOKEN npm publish
```
