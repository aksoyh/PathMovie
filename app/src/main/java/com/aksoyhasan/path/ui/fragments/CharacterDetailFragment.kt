package com.aksoyhasan.path.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.aksoyhasan.path.BuildConfig
import com.aksoyhasan.path.adapters.ComicsAdapter
import com.aksoyhasan.path.base.BaseFragment
import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import com.aksoyhasan.path.data.error.SHOW_SERVER_MESSAGE
import com.aksoyhasan.path.databinding.FragmentCharacterDetailBinding
import com.aksoyhasan.path.ui.MainActivity
import com.aksoyhasan.path.ui.MainViewModel
import com.aksoyhasan.path.utils.*
import com.aksoyhasan.path.utils.internal.extension.emptyIfNull
import com.aksoyhasan.path.utils.internal.extension.zeroIfNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    private val adapter = ComicsAdapter()

    override fun observeViewModel() {
        observe(viewModel.characterDetailLiveData, ::handleCharacterResponse)
        observe(viewModel.characterComicsLiveData, ::handleCharacterComicsResponse)
    }

    private fun handleCharacterResponse(status: Resource<SpecificCharacterResponse>) {
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
                        val imageUrl = "${it?.results?.get(0)?.thumbnail?.path.emptyIfNull()}.${it?.results?.get(0)?.thumbnail?.extension.emptyIfNull()}"
                        ivCharacterPhoto.loadImage(imageUrl)
                        tvCharacterName.text = it?.results?.get(0)?.name.emptyIfNull()
                        tvDescription.text = it?.results?.get(0)?.description.emptyIfNull()
                    }
                }
            }
        }
    }

    private fun handleCharacterComicsResponse(status: Resource<CharacterComicsResponse>) {
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
                        rvComics.adapter = adapter
                        adapter.differ.submitList(it?.results)
                    }
                }
            }
        }
    }

    override fun initViewBinding() {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
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

        with(binding) {
            back.setOnClickListener { mainActivity.onBackPressed() }
        }

        viewModel.getSpecificCharacter(args.characterId.zeroIfNull(), BuildConfig.API_KEY, UiHelper.md5("${UiHelper.getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"), UiHelper.getTimeStamp())
        viewModel.getCharacterComics(args.characterId.zeroIfNull(), "2005-01-01,2022-03-15", "focDate", 10, BuildConfig.API_KEY, UiHelper.md5("${UiHelper.getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"), UiHelper.getTimeStamp())

    }

}