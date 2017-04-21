package com.example.nayem.studentinfo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StudentDetailsAdapter extends ArrayAdapter<UserModel> {
    private Activity context;
    private List<UserModel> studentLists;


    public StudentDetailsAdapter(Activity context, List<UserModel> studentLists) {
        super(context, R.layout.list_view,studentLists);
        this.context=context;
        this.studentLists=studentLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.list_view,null,true);
        TextView nameTV= (TextView) view.findViewById(R.id.nameTextView);
        TextView departmentTV= (TextView) view.findViewById(R.id.departmentTextView);

        UserModel userModel=studentLists.get(position);
        nameTV.setText(userModel.getName());
        departmentTV.setText(userModel.getDepartment());

        return view;
    }
}
