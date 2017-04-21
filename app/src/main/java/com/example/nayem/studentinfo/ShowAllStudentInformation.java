package com.example.nayem.studentinfo;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowAllStudentInformation extends Fragment implements View.OnClickListener {
    private ListView list_data;
    DatabaseReference databaseReference;
    List<UserModel> studentLists;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_all_student_information, container, false);
        list_data= (ListView) view.findViewById(R.id.listView);
        databaseReference= FirebaseDatabase.getInstance().getReference("students");
        studentLists=new ArrayList<>();

        floatingActionButton= (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentLists.clear();
                for (DataSnapshot studentList : dataSnapshot.getChildren()){
                    UserModel userModel=studentList.getValue(UserModel.class);
                    studentLists.add(userModel);
                }
                StudentDetailsAdapter adapter=new StudentDetailsAdapter(getActivity(),studentLists);
                list_data.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer,new StudentRegistrationFragment());
        ft.commit();
    }
}
