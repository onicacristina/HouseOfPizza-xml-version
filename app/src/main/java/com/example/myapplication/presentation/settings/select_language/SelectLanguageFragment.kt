package com.example.myapplication.presentation.settings.select_language

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class SelectLanguageFragment : Fragment() {

    companion object {
        fun newInstance() = SelectLanguageFragment()
    }

    private lateinit var viewModel: SelectLanguageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_language, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectLanguageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}