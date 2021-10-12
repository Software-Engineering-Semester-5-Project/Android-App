package com.example.seprojectsemester5.dataanalyst.view_data_report.ui.synced_data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SyncedDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SyncedDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}