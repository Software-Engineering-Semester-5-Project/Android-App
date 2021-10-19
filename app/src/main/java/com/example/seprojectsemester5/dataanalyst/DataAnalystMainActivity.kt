package com.example.seprojectsemester5.dataanalyst

import android.R.integer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.example.seprojectsemester5.databinding.ActivityDataAnalystMainBinding
import com.example.seprojectsemester5.databinding.DataAnalystFiltersBinding
import com.example.seprojectsemester5.databinding.DataAnalystPieChartBinding
import com.google.android.material.slider.RangeSlider

class DataAnalystMainActivity : AppCompatActivity() {
    private var binding: ActivityDataAnalystMainBinding? = null
    private var shortAnimationDuration = 0
    private var dataAnalystFilterInclude: DataAnalystFiltersBinding? = null

    // pie chart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataAnalystMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
        dataAnalystFilterInclude = binding!!.dataAnalystFilterInclude
        shortAnimationDuration = resources.getInteger(
            integer.config_shortAnimTime
        )
        binding!!.filterOption.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                handleOnCheckedOfSwitch()
            } else {
                dataAnalystFilterInclude!!.dataAnalystFilterView.visibility = View.GONE
                binding!!.dataAnalystApplyButton.visibility = View.GONE
            }
        }
        dataAnalystFilterInclude!!.dataAnalystAgeRangeSlider
            .addOnChangeListener(RangeSlider.OnChangeListener { slider: RangeSlider, _: Float, _: Boolean ->
                setValuesOnTextsOnChangeOfValue(
                    slider
                )
            })
        binding!!.dataAnalystApplyButton.setOnClickListener { handleApplyButtonOnClickListener() }

        // PieChart
        pieChart = PieChart(binding!!.dataAnalystPieChartInclude)
        pieChart.setupPieChart()
    }

    private fun setValuesOnTextsOnChangeOfValue(slider: RangeSlider) {
        val minVal = slider.values[0].toInt().toString()
        val maxVal = slider.values[1].toInt().toString()
        dataAnalystFilterInclude!!.dataAnalystAgeMinValueTextview.text = minVal
        dataAnalystFilterInclude!!.dataAnalystAgeMaxValueTextview.text = maxVal
    }

    private fun handleApplyButtonOnClickListener() {
        binding!!.dataAnalystApplyButton.isEnabled = !binding!!.dataAnalystApplyButton.isEnabled
    }

    private fun handleOnCheckedOfSwitch() {
        val dataAnalystFilterView: View = dataAnalystFilterInclude!!.dataAnalystFilterView
        dataAnalystFilterView.alpha = 0f
        dataAnalystFilterView.visibility = View.VISIBLE
        dataAnalystFilterView.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)
        binding!!.dataAnalystApplyButton.alpha = 0f
        binding!!.dataAnalystApplyButton.visibility = View.VISIBLE
        binding!!.dataAnalystApplyButton.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)
    }
}