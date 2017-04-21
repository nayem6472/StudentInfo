package com.example.nayem.studentinfo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Activity activity;
    List<UserModel>listUsers;

    LayoutInflater inflater;

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater= (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.list_view,null);
        TextView nameTV= (TextView) view.findViewById(R.id.nameTextView);
        TextView departmentTV= (TextView) view.findViewById(R.id.departmentTextView);

        nameTV.setText(listUsers.get(position).getName());
        departmentTV.setText(listUsers.get(position).getDepartment());

        return view;
    }
}
