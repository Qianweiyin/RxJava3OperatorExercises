package com.qwy.rxjava

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.view.KeyEventDispatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class SupportActivity : Activity(), LifecycleOwner {


    private lateinit var lifecycleRegistry: LifecycleRegistry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

    }


    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }


}