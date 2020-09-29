# react-native-broadcast-manager

A bridge for sending broadcast events from react-native to native code

**Author:** Applicaster LTD

**Platform Support:** iOS, tvOS, Android, AndroidTV

## Usage (RN Side)

Call sendBroadcastEvent with an event_key (String) and even_properties (type JSON) that later can be caught in the native code and handled upon receiving.

1. Add the dependency to your `package.json` file:

    ```js
    "react-native-broadcast-manager":0.2.3
    ```

2. Send a broadcast message

    ```js
    import { sendBroadcastEvent } from 'react-native-broadcast-manager';

    // ...

    sendBroadcastEvent("someEventName", {
      "key_1" : "value_1",
      "key_2" : "value_2",
      //...
      "key_n" : "value_n"
    });
    ```

## Usage (iOS)

Catch the event in your native project by adding the following code:

```swift
  private let SomeEventName = Notification.Name("someEventName")

  //..

  //Listen to react native broadcase events,
  //Don't forget to release it on deinit!
  NotificationCenter.default.addObserver(self,
                selector: #selector(didReceiveEvent(_:)),
                name: SomeEventName,
                object: nil)

  //..

  @objc func didReceiveEvent(_ notification:Notification) {
    if let userInfo = notification.userInfo as [String: Any] {
      /*
      The userInfo will contain a dictionary with the JSONObject you sent in the React-Native side.
      */

      //Add more logic here.
    }
  }
```

## Usage (Android)

Catch the event in your native project by adding the following code:

```java
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_PROPERTIES = "event_properties";
    //..

    public static final String SEND_BROADCAST_ACTION = "send_broadcast_from_rn";

    // Catch the send_broadcast_from_rn action sent from the RN Broadcast Manager
    LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                try {
                  //Filter for the intent you sent in the r.n side
                  if ("someEventName".equals(intent.getStringExtra(EVENT_NAME))) {
                    //Get the event properties
                    val event_properties = new JSONObject(intent.getStringExtra(EVENT_PROPERTIES));
                    //Add more logic here
                  }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }, new IntentFilter(SEND_BROADCAST_ACTION));
```

## Manifest file setup (for Applicaster plugins only)

If you a working a Applicaster plugin then add the following configuration to your manifest file.

```js
//For all platforms
"api": {
  //..
  "react_packages": [
    //..
    //Add the following to the bottom of the list.
    "com.applicaster.react.BroadcastManagerAPIPackage"
  ]
}

//...

//Only for iOS/tvOS
"extra_dependencies": [
  {
    "react-native-broadcast-manager": ":path => './node_modules/react-native-broadcast-manager'"
  }
],
//For all platforms
"npm_dependencies": [
  "react-native-broadcast-manager@^0.2.3"
],
//Only for Android/AndroidTV plugins
"project_dependencies": [
  {
    "react-native-broadcast-manager": "./node_modules/react-native-broadcast-manager/Android"
  }
]
```

## License

MIT
