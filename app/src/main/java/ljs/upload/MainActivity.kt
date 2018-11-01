package ljs.upload

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatEditText
import android.view.View
import com.taobao.weex.IWXRenderListener
import com.taobao.weex.WXSDKInstance
import com.taobao.weex.common.WXRenderStrategy
import ljs.android.activity.BaseActivity
import ljs.android.preferences
import ljs.android.start
import ljs.android.toast
import java.util.*


class MainActivity : BaseActivity(), IWXRenderListener {
    private lateinit var mWXSDKInstance: WXSDKInstance

    override fun onRefreshSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
        toast("$width:$height")
    }

    override fun onException(instance: WXSDKInstance?, errCode: String?, msg: String?) {
        showOption("$errCode:$msg")
    }

    private fun showOption() = showOption(null)

    val defaultUrl = "http://192.168.1.88:8080/dist/index.js"

    var url = defaultUrl

    val preference_url = "url"

    fun reboot() {

        finish()

        start(MainActivity::class.java)
    }

    fun exit() = System.exit(0)

    private fun showOption(message: String?) {
        val items = arrayOf("刷新", "重启", "退出", "设置URL")

        val events = arrayOf(
            DialogInterface.OnClickListener { _, _ -> mWXSDKInstance.reloadPage(true) },
            DialogInterface.OnClickListener { _, _ -> reboot() },
            DialogInterface.OnClickListener { _, _ -> exit() },
            DialogInterface.OnClickListener { _, _ ->
                val urlInput = AppCompatEditText(this)

                urlInput.hint = "请输入服务器URL"

                urlInput.setText(url)

                AlertDialog.Builder(this).setView(urlInput)
                    .setTitle(urlInput.hint)
                    .setPositiveButton("确定") { _, _ ->
                        url = urlInput.text.toString()

                        preferences.edit().putString(preference_url, url).apply()

                        reboot()
                    }.show()
            }
        )

        AlertDialog.Builder(this)
            .setItems(items)
            { dialog, which -> events[which].onClick(dialog, which) }
            .setMessage(message)
            .show()
    }

    override fun onViewCreated(instance: WXSDKInstance?, view: View) {
        setContentView(view)
        view.setOnLongClickListener {
            showOption()
            true
        }
    }

    override fun onRenderSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = preferences.getString(preference_url, defaultUrl)

        mWXSDKInstance = WXSDKInstance(this)
        mWXSDKInstance.registerRenderListener(this)
        mWXSDKInstance.renderByUrl(BuildConfig.APPLICATION_ID, url, HashMap(), null, WXRenderStrategy.APPEND_ONCE)
    }

    override fun onBackPressed() = showOption()

    override fun onResume() {
        super.onResume()
        mWXSDKInstance.onActivityResume()
    }

    override fun onPause() {
        super.onPause()
        mWXSDKInstance.onActivityPause()
    }

    override fun onStop() {
        super.onStop()
        mWXSDKInstance.onActivityStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWXSDKInstance.onActivityDestroy()
    }
}
