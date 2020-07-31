package top.andnux.flutter.flutter_wallets

import top.andnux.flutter.flutter_wallets.IWalletAdapter
import java.util.*

object WalletFactory {

    private val mCache = ArrayList<IWalletAdapter>()

    public fun add(wallet: IWalletAdapter) {
        mCache.add(wallet)
    }

    public fun remove(wallet: IWalletAdapter) {
        mCache.remove(wallet)
    }

    public fun getAll(): ArrayList<IWalletAdapter> {
        return mCache
    }
}