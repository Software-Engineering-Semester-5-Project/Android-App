package com.example.seprojectsemester5.view_data_report.ui.synced_data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;;import com.example.seprojectsemester5.databinding.FragmentSyncedDataBinding;

public class SyncedDataFragment extends Fragment {

    private SyncedDataViewModel homeViewModel;
    private FragmentSyncedDataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(SyncedDataViewModel.class);

        binding = FragmentSyncedDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}