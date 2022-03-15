package com.aksoyhasan.path.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.aksoyhasan.path.BuildConfig
import com.aksoyhasan.path.R
import com.aksoyhasan.path.adapters.CharactersAdapter
import com.aksoyhasan.path.base.BaseFragment
import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.error.SHOW_SERVER_MESSAGE
import com.aksoyhasan.path.databinding.FragmentCharactersBinding
import com.aksoyhasan.path.ui.MainActivity
import com.aksoyhasan.path.ui.MainViewModel
import com.aksoyhasan.path.utils.*
import com.aksoyhasan.path.utils.internal.extension.emptyIfNull
import com.aksoyhasan.path.utils.internal.extension.zeroIfNull
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment : BaseFragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private val adapter = CharactersAdapter()

    private var offset: Int = 0

    override fun observeViewModel() {
        observe(viewModel.charactersLiveData, ::handleCharactersResponse)
    }

    private fun handleCharactersResponse(status: Resource<CharactersResponse>) {
        when (status) {
            is Resource.Loading -> {
                mainActivity.showLoading()
            }
            is Resource.DataError -> {
                mainActivity.hideLoading()
                status.errorCode?.let {
                    if (it == SHOW_SERVER_MESSAGE) {
                        binding.root.showSnackbar(status.errorMessage.emptyIfNull(), 1500)
                    } else {
                        val error = viewModel.errorManager.getError(it)
                        binding.root.showSnackbar(error.description, 1500)
                    }
                }
            }
            is Resource.Success -> {
                mainActivity.hideLoading()
                status.data.let {
                    with(binding) {
                        rvCharacters.adapter = adapter
                        adapter.differ.submitList(it?.results)
                        adapter.setOnItemClickListener {
                            navigateToDetail(it.zeroIfNull())
                        }
                    }
                }
            }
        }
    }

    override fun initViewBinding() {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
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

        viewModel.getCharacters("name", 30, offset, BuildConfig.API_KEY, UiHelper.md5("${UiHelper.getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"), UiHelper.getTimeStamp())

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    newListRequest(true)
                } else if (!recyclerView.canScrollVertically(-1)) {
                    newListRequest(false)
                }
            }
        })
    }

    private fun newListRequest(isNewList: Boolean) {
        if (isNewList) {
            offset += 10
        } else {
            if (offset>=10)
                offset -= 10
        }
        viewModel.getCharacters("name", 30, offset, BuildConfig.API_KEY, UiHelper.md5("${UiHelper.getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"), UiHelper.getTimeStamp())
    }

    private fun navigateToDetail(id: Int) {
        val bundle = Bundle().apply {
            putInt("characterId", id.zeroIfNull())
        }
        navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

}