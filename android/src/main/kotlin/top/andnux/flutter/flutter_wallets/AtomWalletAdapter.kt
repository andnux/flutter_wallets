package top.andnux.flutter.flutter_wallets

import androidx.collection.ArrayMap
import atom.AtomWallet
import com.google.gson.Gson

class AtomWalletAdapter : IWalletAdapter {

    override fun support(name: String): Boolean {
        return "ATOM".equals(name, true)
    }

    override fun walletFromRandom(): String {
        val wallet = AtomWallet()
        wallet.fromGenerate()
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        map["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromPrivateKey(privateKey: String): String {
        val wallet = AtomWallet()
        wallet.fromPrivateKey(privateKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromPublicKey(publicKey: String): String {
        val wallet = AtomWallet()
        wallet.fromPublicKey(publicKey)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromKeyStore(mnemonic: String, path: String): String {
        val wallet = AtomWallet()
        wallet.fromMnemonicAndPath(mnemonic, path)
        val map = ArrayMap<String, String>()
        map["privateKey"] = wallet.privateKey
        map["publicKey"] = wallet.publicKey
        map["mnemonic"] = wallet.mnemonic
        map["address"] = wallet.address
        return Gson().toJson(map)
    }

    override fun walletFromMnemonic(walletFromKeyStore: String, password: String): String {
        return ""
    }
}