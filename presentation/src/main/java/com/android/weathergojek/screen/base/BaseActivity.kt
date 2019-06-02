package com.android.weathergojek.screen.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.weathergojek.App
import com.android.weathergojek.BR
import com.android.weathergojek.di.component.ActivityComponent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    @Inject
    lateinit var eventBus: EventBus
    @Inject
    lateinit var viewModel: VM
    protected var binding: VB? = null
    protected var appComponent: ActivityComponent? = null
        get() {
            if (field == null) field = App.getActivityComponent()
            return field
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = App.getActivityComponent()
    }

    override fun onStart() {
        super.onStart()
        registerEventBus()
        viewModel.onViewModelStart()
    }

    override fun onResume() {
        super.onResume()
        registerEventBus()
    }

    override fun onStop() {
        unregisterEventBus()
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        unregisterEventBus()
    }

    private fun registerEventBus() {
        if (!eventBus.isRegistered(this))
            eventBus.register(this)
    }

    private fun unregisterEventBus() {
        if (eventBus.isRegistered(this))
            eventBus.unregister(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        registerEventBus()
        supportFragmentManager.fragments.forEach {
            it.onActivityResult(
                requestCode,
                resultCode,
                data
            )
        }
    }

    override fun onDestroy() {
        viewModel.onViewModelDestroy()
        unregisterEventBus()
        binding = null
        super.onDestroy()
    }

    protected fun bindContentView(@LayoutRes layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding?.setVariable(BR.viewModel, viewModel)
        binding?.executePendingBindings()
    }

    @Subscribe
    fun toastEvent(event: BaseUIEvent.OnShowToast) {
        val text = if (event.messageResource != 0) string(event.messageResource) else event.message
        if (text.isNotEmpty()) Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected fun string(@StringRes resId: Int): String = resources.getString(resId)
}
