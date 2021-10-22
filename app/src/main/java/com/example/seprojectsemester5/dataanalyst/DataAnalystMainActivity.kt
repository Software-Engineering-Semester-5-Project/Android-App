package com.example.seprojectsemester5.dataanalyst

import android.R.integer
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color.BLACK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.seprojectsemester5.R
import com.example.seprojectsemester5.databinding.ActivityDataAnalystMainBinding
import com.example.seprojectsemester5.databinding.DataAnalystFiltersBinding
import com.example.seprojectsemester5.databinding.DataAnalystPieChartBinding
import com.example.seprojectsemester5.models.DiseaseFilters
import com.example.seprojectsemester5.repositories.remote.Resource
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.slider.RangeSlider

@BindingAdapter("android:setPieData")
fun setPieData(pieChart : com.github.mikephil.charting.charts.PieChart, entries: List<PieEntry>?){
    pieChart.isDrawHoleEnabled = true
    pieChart.setUsePercentValues(true)
    pieChart.setEntryLabelTextSize(12f)
    pieChart.setEntryLabelColor(BLACK)
    pieChart.centerText = "Disease Distribution"
    pieChart.setCenterTextSize(24f)
    pieChart.description.isEnabled = false

    val legend = pieChart.legend
    legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
    legend.orientation = Legend.LegendOrientation.VERTICAL
    legend.setDrawInside(false)
    legend.isEnabled = true


    val colors = mutableListOf<Int>()
    for(color in ColorTemplate.MATERIAL_COLORS){
        colors.add(color)
    }
    for(color in ColorTemplate.VORDIPLOM_COLORS){
        colors.add(color)
    }

    val dataSet = PieDataSet(entries, "Disease Types")
    dataSet.colors = colors

    val pieData = PieData(dataSet)
    pieData.setDrawValues(true)
    pieData.setValueFormatter(PercentFormatter(pieChart))
    pieData.setValueTextSize(12f)
    pieData.setValueTextColor(BLACK)

    pieChart.data = pieData
    pieChart.invalidate()
    println("Function is working")
}

class DataAnalystMainActivity : AppCompatActivity() {
    private var binding: ActivityDataAnalystMainBinding? = null
    private var shortAnimationDuration = 0

    // filter include
    private lateinit var dataAnalystFilterInclude: DataAnalystFiltersBinding

    // viewModel
    private lateinit var viewModel : DataAnalystMainActivityViewModel

    // pie chart
    private lateinit var pieChartIncludeBinding : DataAnalystPieChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding
        binding = ActivityDataAnalystMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_analyst_main)

        dataAnalystFilterInclude = binding!!.dataAnalystFilterInclude

        pieChartIncludeBinding = binding!!.dataAnalystPieChartInclude
                // animation settings
        shortAnimationDuration = resources.getInteger(
            integer.config_shortAnimTime
        )

        // viewModel initializing
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(DataAnalystMainActivityViewModel::class.java)

        dataAnalystFilterInclude.viewModel = viewModel
        pieChartIncludeBinding.viewModel = viewModel
        // filterOption
        binding!!.filterOption.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                handleOnCheckedOfSwitch()
            } else {
                handleOnUncheckedOfSwitch()
            }
        }

        // Filter Settings
        dataAnalystFilterInclude.dataAnalystAgeRangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { slider: RangeSlider, _: Float, _: Boolean ->
            val minVal = slider.values[0].toInt()
            val maxVal = slider.values[1].toInt()
            dataAnalystFilterInclude.dataAnalystAgeMinValueTextview.text = minVal.toString()
            dataAnalystFilterInclude.dataAnalystAgeMaxValueTextview.text = maxVal.toString()
        })

        binding!!.dataAnalystApplyButton.setOnClickListener { handleApplyButtonOnClickListener() }

        // refresh data button
        binding!!.dataAnalystRefreshResultButton.setOnClickListener {
            handleRefreshDataButtonOnClickListener()
        }

        // PieChart
//        pieChart = PieChart(pieChartIncludeBinding)
        viewModel.getDataSummaryResponse.observe(this, {
            when(it) {
                is Resource.Success -> {
                    if(it.value.status == "OK"){
                        val dataAnalystGetDataSummary = it.value.data
//                        setupPieChart(dataAnalystGetDataSummary!!)
                        viewModel.createPieChartEntries(dataAnalystGetDataSummary!!)
                    } else {
                        showToast("Failed to get data summary response")
                    }
                }
                else -> {
                    showToast("Failed to get data summary response")
                }
            }
        })

        viewModel.pieChartEntries.observe(this, {
            try{
                setPieData(pieChartIncludeBinding.dataAnalystPieChart, it)
            } catch (e : Exception){
                println("Error occurred ${e.printStackTrace()}")
            }
        })

        // update summary response
        viewModel.updateSummaryDataResponse.observe(this, {
            when(it) {
                is Resource.Success -> showToast(it.value.message)
                is Resource.Failure -> showToast("Failed to update Status Summary")
            }
        })
    }

    // switch settings
    private fun handleOnCheckedOfSwitch() {
        binding!!.filterOption.text = resources.getString(R.string.filters_checked)
        val dataAnalystFilterView: View = dataAnalystFilterInclude.dataAnalystFilterView

        // filter view
        dataAnalystFilterView.alpha = 0f
        dataAnalystFilterView.visibility = View.VISIBLE
        dataAnalystFilterView.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)

        // PieChartInclude
        val pieChartView = pieChartIncludeBinding.dataAnalystPieChartView
        if(pieChartView.visibility == View.GONE){
            pieChartView.alpha = 0f
            pieChartView.visibility = View.VISIBLE
            pieChartView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }

        // apply button
        binding!!.dataAnalystApplyButton.alpha = 0f
        binding!!.dataAnalystApplyButton.visibility = View.VISIBLE
        binding!!.dataAnalystApplyButton.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)

        // refresh data button
        binding!!.dataAnalystRefreshResultButton.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding!!.dataAnalystRefreshResultButton.visibility = View.GONE
                }
            })
    }

    private fun handleOnUncheckedOfSwitch() {
        binding!!.filterOption.text = resources.getString(R.string.filters_un_checked)
        dataAnalystFilterInclude.dataAnalystFilterView.visibility = View.GONE
        binding!!.dataAnalystApplyButton.visibility = View.GONE

        // refresh data button
        binding!!.dataAnalystRefreshResultButton.visibility = View.VISIBLE
        binding!!.dataAnalystRefreshResultButton.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)
    }

    // filter settings
    private fun handleApplyButtonOnClickListener() {

//        toggleProgressBar()
        val pinCode = binding!!.dataAnalystPinCodeEditText.text.toString()

        val diseaseFilters = if(pinCode.length < 6){
            showToast("Please add pin code of length 6")
            null
        } else {
            getFilterDetails(Integer.parseInt(pinCode))
        }

        if(diseaseFilters != null){
            viewModel.getDataSummary(diseaseFilters)
        }

//        toggleProgressBar()
    }

    private fun getFilterDetails(pin : Int) : DiseaseFilters {
        // init and pin
        val diseaseFilters = DiseaseFilters(pin = pin)

        // sex/gender
        when(dataAnalystFilterInclude.dataAnalystSexRadioGroup.checkedRadioButtonId) {
            R.id.data_analyst_sex_radiobutton_male -> diseaseFilters.sex = "male"
            R.id.data_analyst_sex_radiobutton_female -> diseaseFilters.sex = "female"
            R.id.data_analyst_sex_radiobutton_all -> diseaseFilters.sex = "all"
        }

        // age
        val sliderValues = dataAnalystFilterInclude.dataAnalystAgeRangeSlider.values
        val minVal = sliderValues[0].toInt()
        val maxVal = sliderValues[1].toInt()
        diseaseFilters.startDate = minVal
        diseaseFilters.endDate = maxVal

        // disease
        val diseasesArray = viewModel.getDiseaseArray()
        when(dataAnalystFilterInclude.dataAnalystDiseaseAutoCompleteTextview.text.toString()){
            diseasesArray[0] -> diseaseFilters.diseaseType = "fever"
            diseasesArray[1] -> diseaseFilters.diseaseType = "cough"
            diseasesArray[2] -> diseaseFilters.diseaseType = "bodyPain"
            diseasesArray[3] -> diseaseFilters.diseaseType = "breathe"
            diseasesArray[4] -> diseaseFilters.diseaseType = "eye"
            diseasesArray[5] -> diseaseFilters.diseaseType = "skin"
        }

        return diseaseFilters
    }

    // refresh button settings
    private fun handleRefreshDataButtonOnClickListener() {
//        toggleProgressBar()

        val pinCode = binding!!.dataAnalystPinCodeEditText.text.toString()
        if(pinCode.length < 6){
            showToast("Please add pin code of length 6")
        } else {
            val sharedPreferences = getSharedPreferences("FIXED", MODE_PRIVATE)
            val token = sharedPreferences.getString("jwtToken", "ANY DEFAULT VALUE")
            viewModel.updateSummaryDataResponse(token!!, Integer.parseInt(pinCode))
        }

//        toggleProgressBar()
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

//    private fun toggleProgressBar(){
//        if(binding!!.dataAnalystProgressBar.visibility == View.VISIBLE){
//            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//            binding!!.dataAnalystProgressBar.visibility = View.GONE
//        } else {
//            binding!!.dataAnalystProgressBar.visibility = View.VISIBLE
//            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        }
//    }
}
