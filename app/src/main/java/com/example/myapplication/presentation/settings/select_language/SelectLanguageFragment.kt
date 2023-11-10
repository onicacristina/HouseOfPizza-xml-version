package com.example.myapplication.presentation.settings.select_language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSelectLanguageBinding
import com.example.myapplication.presentation.base.NoBottomNavigationFragment
import com.example.myapplication.presentation.settings.select_language.language_adapter.LanguageAdapter
import com.example.myapplication.utils.LocaleHelper
import com.example.myapplication.utils.extensions.viewBinding
import kotlinx.coroutines.launch

class SelectLanguageFragment :
    NoBottomNavigationFragment<FragmentSelectLanguageBinding>(R.layout.fragment_select_language) {

    override val viewBinding: FragmentSelectLanguageBinding by viewBinding(
        FragmentSelectLanguageBinding::bind
    )
    override val viewModel: SelectLanguageViewModel by viewModels()
    private lateinit var languageAdapter: LanguageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        initToolbar()
        setupRecyclerView()
    }

    private fun initToolbar() {
        viewBinding.toolbar.ivBack.visibility = View.VISIBLE
        viewBinding.toolbar.tvTitle.setText(R.string.select_language)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
        viewBinding.btnSave.setOnClickListener {
            selectLanguage(viewModel.languageSelected)
            navController.popBackStack()
        }
    }

    private fun selectLanguage(language: ELanguage) {
        LocaleHelper.setLocale(language)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.languageObservable.collect { languages ->
                        setList(languages)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.rvLanguages
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        languageAdapter = LanguageAdapter(
            onItemClickListener = {
                viewModel.selectLanguage(it)
            }
        )
        recyclerView.adapter = languageAdapter
    }

    private fun setList(data: List<Language>) {
        languageAdapter.submitList(data)
    }

}