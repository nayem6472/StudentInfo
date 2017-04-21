package com.example.nayem.studentinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistrationFragment extends Fragment implements View.OnClickListener {
    EditText nameET,idET,departmentET,resultET,addressET;
    Button saveBtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


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

        initDatabase();

        saveBtn.setOnClickListener(this);





        return view;
    }

    private void initDatabase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("students");
    }


    @Override
    public void onClick(View v) {
        addStudent();
    }

    private void addStudent() {
        String name=nameET.getText().toString().trim();
        String id=idET.getText().toString().trim();
        String department=departmentET.getText().toString().trim();
        String result=resultET.getText().toString().trim();
        String address=addressET.getText().toString().trim();


        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id)){
            String uniqueId=databaseReference.push().getKey();
            UserModel userModel=new UserModel(uniqueId,name,id,department,result,address);
            databaseReference.child(uniqueId).setValue(userModel);
            Toast.makeText(getActivity(),"Student Information added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"You Should Enter name and ID",Toast.LENGTH_SHORT).show();
        }
    }
}
