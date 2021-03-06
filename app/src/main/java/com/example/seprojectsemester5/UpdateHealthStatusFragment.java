package com.example.seprojectsemester5;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateHealthStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateHealthStatusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateHealthStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateHealthStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateHealthStatusFragment newInstance(String param1, String param2) {
        UpdateHealthStatusFragment fragment = new UpdateHealthStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Activity = inflater.inflate(R.layout.fragment_update_health_status, container, false);


        ImageView help = Activity.findViewById(R.id.help);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Help.class);
                startActivity(intent);
            }
        });

        CardView proceedtonewdata = Activity.findViewById(R.id.addnewdata);
        proceedtonewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PesonalDataFeeding.class);
                startActivity(intent);
            }
        });

        CardView proceedtocollectedcard = Activity.findViewById(R.id.colleceddata);
        proceedtocollectedcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SyncedUnsycnedData.class);
                startActivity(intent);
            }
        });


        return Activity;
    }
}