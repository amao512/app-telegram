package com.aslnstbk.telegram.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.databinding.FragmentCreateChannelBinding
import com.aslnstbk.telegram.databinding.FragmentSettingsBinding

class CreateChannelFragment : Fragment() {

    private lateinit var mBinding: FragmentCreateChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCreateChannelBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }
}