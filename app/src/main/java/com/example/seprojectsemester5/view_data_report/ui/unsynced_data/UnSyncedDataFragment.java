package com.example.seprojectsemester5.view_data_report.ui.unsynced_data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.seprojectsemester5.databinding.FragmentUnSyncedDataBinding;

public class UnSyncedDataFragment extends Fragment {

    private UnSyncedDataViewModel dashboardViewModel;
    private FragmentUnSyncedDataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(UnSyncedDataViewModel.class);

        binding = FragmentUnSyncedDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}