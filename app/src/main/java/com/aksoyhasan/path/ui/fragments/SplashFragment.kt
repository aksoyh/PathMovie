package com.aksoyhasan.path.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.aksoyhasan.path.R
import com.aksoyhasan.path.base.BaseFragment
import com.aksoyhasan.path.databinding.FragmentSplashBinding
import com.aksoyhasan.path.ui.MainActivity
import com.aksoyhasan.path.utils.InternetConnectionUtil
import com.aksoyhasan.path.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    override fun observeViewModel() {}

    override fun initViewBinding() {
        binding = FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        navController = mainActivity.navController

        val handler = Handler()

        if (InternetConnectionUtil.isInternetOn()) {
            navController.navigate(R.id.action_splashFragment_to_listFragment)
        } else {
            binding.root.showSnackbar(resources.getString(R.string.network_error), 1500)
            handler.postDelayed({
                mainActivity.finish()
            }, 2000)
        }
    }

}