package com.example.seprojectsemester5.dataanalyst.view_data_report.ui.unsynced_data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnSyncedDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnSyncedDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}