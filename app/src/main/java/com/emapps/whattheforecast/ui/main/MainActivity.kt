package com.emapps.whattheforecast.ui.main

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emapps.whattheforecast.R
import com.emapps.whattheforecast.data.model.ForecastDay
import com.emapps.whattheforecast.databinding.ActivityMainBinding
import com.emapps.whattheforecast.ui.main.adapters.ForecastAdapter
import com.emapps.whattheforecast.ui.settings.SettingsFragment
import com.emapps.whattheforecast.utility.Constants.APP_PREFERENCES
import com.emapps.whattheforecast.utility.Constants.DAY_MODE
import com.emapps.whattheforecast.utility.Constants.NIGHT_MODE
import com.emapps.whattheforecast.utility.Constants.UI_MODE
import com.emapps.whattheforecast.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val forecastViewModel: ForecastViewModel by viewModels()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setPreferences()
        setUiMode()
        setSearchView()
        setForecastList()
        observeCityForecast()
        observeLoading()

        binding.icSettings.setOnClickListener {
            navToSettings()
        }
    }

    private fun setPreferences() {
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
    }

    private fun setUiMode() {
        val uiMode = sharedPreferences.getInt(UI_MODE, -1)
        when (uiMode) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> {
                if (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) != Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPreferences.edit().putInt(UI_MODE, DAY_MODE).apply()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPreferences.edit().putInt(UI_MODE, NIGHT_MODE).apply()
                }
            }
        }
    }

    private fun setSearchView() {
        forecastViewModel.fetchCityForecast(
            "16589d494121424eac6170157252501",
            "Cairo", 7
        )
        val searchEditText = binding.searchView
            .findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setText(getString(R.string.default_city))
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    forecastViewModel.fetchCityForecast(
                        "16589d494121424eac6170157252501",
                        query, 7
                    )
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun navToSettings() {
        supportFragmentManager.commit {
            add(
                R.id.fragment_container_view,
                SettingsFragment.newInstance(),
            )
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun hideForecast() {
        binding.icLocation.visibility = View.INVISIBLE
        binding.txtCity.visibility = View.INVISIBLE
        binding.txtTemp.visibility = View.INVISIBLE
    }

    private fun displayForecast() {
        binding.icLocation.visibility = View.VISIBLE
        binding.txtCity.visibility = View.VISIBLE
        binding.txtTemp.visibility = View.VISIBLE
    }

    private fun setForecastList() {
        binding.rvForecast.layoutManager = LinearLayoutManager(this)
        binding.rvForecast.adapter = ForecastAdapter(listOf())
    }

    private fun observeCityForecast() {
        forecastViewModel.observeCityForecast(this) { response ->
            if (response.data != null) {
                displayForecast()
                binding.txtCity.text = response.data.location.name
                binding.txtTemp.text =
                    getString(R.string.temp_celsius_degree).format(response.data.current.tempInCelsius.toInt())
                binding.txtCondition.text = response.data.current.condition.text
                Glide.with(this).load("https:/${response.data.current.condition.icon}").into(binding.icCondition)
                (binding.rvForecast.adapter as? ForecastAdapter)?.changeForecast(response.data.forecast.forecastDay)
            } else {
                hideForecast()
            }
        }
    }

    private fun observeLoading() {
        forecastViewModel.observeLoading(this) { isLoading ->
            if (isLoading) {
                binding.viewHeaderShimmer.showShimmer(true)
                binding.forecastShimmer.showShimmer(true)
            } else {
                binding.viewHeaderShimmer.hideShimmer()
                binding.forecastShimmer.hideShimmer()
            }
        }
    }
}