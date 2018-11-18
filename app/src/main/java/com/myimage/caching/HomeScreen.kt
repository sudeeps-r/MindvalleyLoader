package com.myimage.caching

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.myimage.caching.view.util.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector

import dagger.android.support.HasSupportFragmentInjector
import learning.sudeep.imageloader.Callback
import learning.sudeep.imageloader.MindValley
import learning.sudeep.imageloader.RequestType
import learning.sudeep.imageloader.Response
import javax.inject.Inject

class HomeScreen : BaseActivity(), HasSupportFragmentInjector {


    override fun supportFragmentInjector() = fragmentInjector
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
    }
}
