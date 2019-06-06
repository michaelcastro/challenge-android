package com.lodjinha.ui.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lodjinha.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "sobre"
    }

    companion object {
        internal val TAG = "AboutFragment"
        @JvmStatic
        fun newInstance() =
            AboutFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
