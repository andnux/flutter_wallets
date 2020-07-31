package top.andnux.flutter.flutter_wallets

import android.text.TextUtils
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterWalletPlugin */
public class FlutterWalletsPlugin : FlutterPlugin, MethodCallHandler {

    private lateinit var channel: MethodChannel
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_wallets")
        channel.setMethodCallHandler(this)
        WalletFactory.add(EosWalletAdapter())
        WalletFactory.add(EthWalletAdapter())
        WalletFactory.add(BtcWalletAdapter())
        WalletFactory.add(VsysWalletAdapter())
        WalletFactory.add(TronWalletAdapter())
        WalletFactory.add(FileCoinWalletAdapter())
        WalletFactory.add(AtomWalletAdapter())
        WalletFactory.add(OmniWalletAdapter())
        WalletFactory.add(CkbWalletAdapter())
        WalletFactory.add(LtcWalletAdapter())
        WalletFactory.add(BchWalletAdapter())
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_wallets")
            channel.setMethodCallHandler(FlutterWalletsPlugin())
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "walletFromRandom"
                || call.method == "walletFromPrivateKey"
                || call.method == "walletFromPublicKey"
                || call.method == "walletFromMnemonic"
                || call.method == "walletFromKeyStore") {
            val type = call.argument<String>("type") ?: ""
            val all = WalletFactory.getAll()
            all.forEach {
                if (it.support(type)) {
                    var wallet = ""
                    when {
                        call.method.equals("walletFromRandom", true) -> {
                            wallet = it.walletFromRandom()
                        }
                        call.method.equals("walletFromPrivateKey", true) -> {
                            val privateKey = call.argument<String>("privateKey") ?: ""
                            wallet = it.walletFromPrivateKey(privateKey)
                        }
                        call.method.equals("walletFromPublicKey", true) -> {
                            val publicKey = call.argument<String>("publicKey") ?: ""
                            wallet = it.walletFromPublicKey(publicKey)
                        }
                        call.method.equals("walletFromMnemonic", true) -> {
                            val mnemonic = call.argument<String>("mnemonic") ?: ""
                            val path = call.argument<String>("path") ?: ""
                            wallet = it.walletFromMnemonic(mnemonic, path)
                        }
                        call.method.equals("walletFromKeyStore", true) -> {
                            val keystore = call.argument<String>("keystore") ?: ""
                            val password = call.argument<String>("password") ?: ""
                            wallet = it.walletFromKeyStore(keystore, password)
                        }
                    }
                    if (TextUtils.isEmpty(wallet)) {
                        result.notImplemented()
                    } else {
                        result.success(wallet)
                    }
                }
            }
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
