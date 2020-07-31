package top.andnux.flutter.flutter_wallets

import androidx.collection.ArrayMap
import com.google.gson.Gson
import eos.EosWallet

class EosWalletAdapter : IWalletAdapter {

    override fun support(name: String): Boolean {
        return "EOS".equals(name, true)
    }

    override fun walletFromRandom(): String {
        val wallet = EosWallet()
        wallet.fromGenerate()
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        return Gson().toJson(map)
    }

    override fun walletFromPrivateKey(privateKey: String): String {
        val wallet = EosWallet()
        wallet.fromPrivateKey(privateKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        return Gson().toJson(map)
    }

    override fun walletFromPublicKey(publicKey: String): String {
        val wallet = EosWallet()
        wallet.fromPublicKey(publicKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        return Gson().toJson(map)
    }

    override fun walletFromKeyStore(mnemonic: String, path: String): String {
        val wallet = EosWallet()
        wallet.fromMnemonicAndPath(mnemonic, path)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        return Gson().toJson(map)
    }

    override fun walletFromMnemonic(walletFromKeyStore: String, password: String): String {
        return ""
    }
}