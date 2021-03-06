package top.andnux.flutter.flutter_wallets

import androidx.collection.ArrayMap
import bch.BchWallet
import com.google.gson.Gson

class BchWalletAdapter : IWalletAdapter {

    override fun support(name: String): Boolean {
        return "BCH".equals(name, true)
    }

    override fun walletFromRandom(): String {
        val wallet = BchWallet()
        wallet.fromGenerate()
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        map ["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromPrivateKey(privateKey: String): String {
        val wallet = BchWallet()
        wallet.fromPrivateKey(privateKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map ["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromPublicKey(publicKey: String): String {
        val wallet = BchWallet()
        wallet.fromPublicKey(publicKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map ["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromKeyStore(mnemonic: String, path: String): String {
        val wallet = BchWallet()
        wallet.fromMnemonicAndPath(mnemonic, path)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        map ["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromMnemonic(walletFromKeyStore: String, password: String): String {
        return ""
    }
}