import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPaykun {
  static const MethodChannel _channel = MethodChannel('flutter_paykun');

  static Future<bool> get configure async {
    // final String version = await _channel.invokeMethod('getPlatformVersion');
    // return version;
    return false;
  }

  static Future startPayment() async {
    final String result = await _channel.invokeMethod('startPayment');
    return result;
  }

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
