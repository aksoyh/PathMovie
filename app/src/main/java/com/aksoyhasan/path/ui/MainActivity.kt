package com.aksoyhasan.path.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aksoyhasan.path.R
import com.aksoyhasan.path.base.BaseActivity
import com.aksoyhasan.path.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun observeViewModel() {}

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.acMainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}