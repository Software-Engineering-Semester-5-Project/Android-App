package com.example.seprojectsemester5.dataanalyst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.seprojectsemester5.databinding.ActivityDataAnalystMainBinding;
import com.example.seprojectsemester5.databinding.DataAnalystFiltersBinding;
import com.google.android.material.slider.RangeSlider;

public class DataAnalystMainActivity extends AppCompatActivity {

    private ActivityDataAnalystMainBinding binding;
    private int shortAnimationDuration;
    private DataAnalystFiltersBinding dataAnalystFilterInclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAnalystMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataAnalystFilterInclude = binding.dataAnalystFilterInclude;

        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime
        );

        binding.filterOption.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                handleOnCheckedOfSwitch();
            } else {
                dataAnalystFilterInclude.dataAnalystFilterView.setVisibility(View.GONE);
                binding.dataAnalystApplyButton.setVisibility(View.GONE);
            }
        });

        dataAnalystFilterInclude
                .dataAnalystAgeRangeSlider
                .addOnChangeListener((slider, value, fromUser) ->
                        setValuesOnTextsOnChangeOfValue(slider));

        binding.dataAnalystApplyButton.setOnClickListener(v -> {
            handleApplyButtonOnClickListener();
        });
    }

    private void setValuesOnTextsOnChangeOfValue(RangeSlider slider) {
        float minVal = slider.getValues().get(0);
        float maxVal = slider.getValues().get(1);

        dataAnalystFilterInclude
                .dataAnalystAgeMinValueTextview
                .setText(String.valueOf((int) minVal));

        dataAnalystFilterInclude
                .dataAnalystAgeMaxValueTextview
                .setText(String.valueOf((int) maxVal));
    }

    private void handleApplyButtonOnClickListener() {
        binding.dataAnalystApplyButton.setEnabled(!binding.dataAnalystApplyButton.isEnabled());
    }

    private void handleOnCheckedOfSwitch() {
        View dataAnalystFilterView = dataAnalystFilterInclude.dataAnalystFilterView;
        dataAnalystFilterView.setAlpha(0f);
        dataAnalystFilterView.setVisibility(View.VISIBLE);
        dataAnalystFilterView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        binding.dataAnalystApplyButton.setAlpha(0f);
        binding.dataAnalystApplyButton.setVisibility(View.VISIBLE);
        binding.dataAnalystApplyButton.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);
    }
}