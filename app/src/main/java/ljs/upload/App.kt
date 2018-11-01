package ljs.upload

import android.app.Application
import com.squareup.picasso.Picasso
import com.taobao.weex.InitConfig
import com.taobao.weex.WXSDKEngine


open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = InitConfig.Builder()
            .setImgAdapter { url, view, _, _ -> Picasso.get().load(url).into(view) }
            .build()
        WXSDKEngine.initialize(this, config)
    }
}