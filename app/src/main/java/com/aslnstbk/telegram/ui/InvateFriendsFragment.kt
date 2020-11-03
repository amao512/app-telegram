package com.aslnstbk.telegram.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.databinding.FragmentInvateFriendsBinding
import com.aslnstbk.telegram.databinding.FragmentSettingsBinding

class InvateFriendsFragment : Fragment() {

    private lateinit var mBinding: FragmentInvateFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentInvateFriendsBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }
}