package com.example.nayem.studentinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class StudentRegistrationFragment extends Fragment implements View.OnClickListener {
    EditText nameET,idET,departmentET,resultET,addressET;
    Button saveBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_student_registration, container, false);
        nameET= (EditText) view.findViewById(R.id.nameEditText);
        idET= (EditText) view.findViewById(R.id.idEditText);
        departmentET= (EditText) view.findViewById(R.id.departmentEditText);
        resultET= (EditText) view.findViewById(R.id.resultEditText);
        addressET= (EditText) view.findViewById(R.id.addressEditText);

        saveBtn= (Button) view.findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
