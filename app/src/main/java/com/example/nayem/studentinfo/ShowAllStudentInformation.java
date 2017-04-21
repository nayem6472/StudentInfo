package com.example.nayem.studentinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ShowAllStudentInformation extends Fragment {
    private ListView list_data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_all_student_information, container, false);
        list_data= (ListView) view.findViewById(R.id.listView);
        return view;
    }

}
