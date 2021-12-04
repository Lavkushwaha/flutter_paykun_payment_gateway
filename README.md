# Flutter Paykun Payment Gateway - flutter_paykun

[![pub package](https://img.shields.io/pub/v/flutter_paykun.svg)](https://pub.dev/packages/flutter_paykun_payment_gateway)

A Flutter plugin for Android Paykun Payment Gateway Integration.

*Note*: This plugin is still under development, and some APIs might not be available yet. We are working on a refactor which can be followed here: [issue]()

## Features


* Initialize the payment gateway with the merchant credentials
* Make a payment request
* Handle the payment response

## Installation

First, add `flutter_paykun_payment_gateway` as a [dependency in your pubspec.yaml file](https://flutter.dev/using-packages/).

<!-- ### iOS


``` -->

### Android

Change the minimum Android sdk version to 21 (or higher) in your `android/app/build.gradle` file.

```
minSdkVersion 16
```

```
WIP Gettting Error in Android
D/EventBus(20949): No subscribers registered for event class com.paykun.sdk.eventbus.Events$PaymentMessage
D/EventBus(20949): No subscribers registered for event class org.greenrobot.eventbus.NoSubscriberEvent

```


<!-- ### Web integration

For web integration details, see the -->

### Initialization


```dart

```

### Example

Here is a full example flutter app running flutter_paykun_payment_gateway client.

```dart



```

For a more elaborate usage example see [here](https://github.com/Lavkushwaha/flutter_paykun_payment_gateway/example).

*Note*: This plugin is still under development, and some APIs might not be available yet.
[Feedback welcome](https://github.com/flutter/flutter/issues) and
[Pull Requests](https://github.com/flutter/plugins/pulls) are most welcome!