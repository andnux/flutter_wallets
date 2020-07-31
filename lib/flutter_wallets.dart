import 'dart:async';
import 'package:flutter/services.dart';

enum WalletType {
  EOS,
  ETH,
  ATOM,
  BCH,
  BTC,
  CKB,
  FILECOIN,
  LTC,
  OMNI,
  TRON,
  VSYS
}

class FlutterWallets {
  static const MethodChannel _channel = const MethodChannel('flutter_wallets');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> walletFromRandom(WalletType type) async {
    final String t =
    type.toString().substring(type.toString().indexOf(".") + 1);
    return await _channel.invokeMethod('walletFromRandom', {"type": t});
  }

  static Future<String> walletFromPrivateKey(
      WalletType type, String privateKey) async {
    final String t = type.toString().substring(
        type.toString().indexOf(".") + 1);
    return await _channel.invokeMethod('walletFromPrivateKey',
        {"type": t, "privateKey": privateKey});
  }

  static Future<String> walletFromPublicKey(
      WalletType type, String publicKey) async {
    final String t = type.toString().substring(
        type.toString().indexOf(".") + 1);
    return await _channel.invokeMethod('walletFromPublicKey',
        {"type": t, "publicKey": publicKey});
  }

  static Future<String> walletFromMnemonic(
      WalletType type, String mnemonic, String path) async {
    final String t = type.toString().substring(
        type.toString().indexOf(".") + 1);
    return await _channel.invokeMethod('walletFromMnemonic',
        {"type": t, "mnemonic": mnemonic, "path": path});
  }

  static Future<String> walletFromKeyStore(
      WalletType type, String keystore, String password) async {
    final String t = type.toString().substring(
        type.toString().indexOf(".") + 1);
    return await _channel.invokeMethod('walletFromKeyStore',
        {"type": t, "keystore": keystore, "password": password});
  }
}
