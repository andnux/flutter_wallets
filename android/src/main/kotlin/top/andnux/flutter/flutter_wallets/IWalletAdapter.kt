package top.andnux.flutter.flutter_wallets

interface IWalletAdapter {

    fun support(name: String): Boolean
    
    fun walletFromRandom(): String

    fun walletFromPrivateKey(privateKey: String): String

    fun walletFromPublicKey(publicKey: String): String

    fun walletFromKeyStore(mnemonic: String, path: String): String

    fun walletFromMnemonic(walletFromKeyStore: String, password: String): String

}