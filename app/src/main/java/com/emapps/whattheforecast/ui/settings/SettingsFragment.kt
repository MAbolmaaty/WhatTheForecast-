package com.emapps.whattheforecast.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.emapps.whattheforecast.R
import com.emapps.whattheforecast.databinding.FragmentSettingsBinding
import com.emapps.whattheforecast.utility.Constants.APP_PREFERENCES
import com.emapps.whattheforecast.utility.Constants.DAY_MODE
import com.emapps.whattheforecast.utility.Constants.NIGHT_MODE
import com.emapps.whattheforecast.utility.Constants.UI_MODE

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToggleBtn()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    private fun setPreferences() {
        sharedPreferences = requireActivity().getSharedPreferences(
            APP_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    private fun setToggleBtn() {
        if (sharedPreferences.getInt(UI_MODE, 0) == 1) {
            binding.btnMode.isChecked = true
            binding.darkMode.text = getString(R.string.dark_mode)
        } else {
            binding.btnMode.isChecked = false
            binding.darkMode.text = getString(R.string.day_mode)
        }
        binding.btnMode.setOnCheckedChangeListener { compoundButton, isChecked ->
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPreferences.edit().putInt(UI_MODE, NIGHT_MODE).apply()
                    binding.darkMode.text = getString(R.string.dark_mode)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPreferences.edit().putInt(UI_MODE, DAY_MODE).apply()
                    binding.darkMode.text = getString(R.string.day_mode)
                }
            }
        }
    }
}