package com.example.nayem.studentinfo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.android.gms.internal.zzt.TAG;

public class SignupFragment extends Fragment implements View.OnClickListener {
    private static final String TAG="SignUp";
    EditText userName,password;
    Button signUpBtn;
    TextView signUpStateChanged;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_signup, container, false);
        userName= (EditText) view.findViewById(R.id.signUpUserName);
        password= (EditText) view.findViewById(R.id.signUpPassword);
        signUpBtn= (Button) view.findViewById(R.id.signUpButton);
        signUpStateChanged= (TextView) view.findViewById(R.id.signUpStateChanged);

        signUpBtn.setOnClickListener(this);
        signUpStateChanged.setOnClickListener(this);

        auth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=auth.getCurrentUser();
                if (firebaseUser != null){
                    Log.e(TAG,"onAuthStateChanged"+firebaseUser.getEmail());
                }else {
                    Log.e(TAG,"onAuthStateChanged"+"sign_out");
                }
            }
        };
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton:
                String email=userName.getText().toString().trim();
                String pass=password.getText().toString().trim();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(TAG,"onComplete"+task.isSuccessful());
                        if (!task.isSuccessful()){
                            Toast.makeText(getActivity(),"authentication failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.signUpStateChanged:
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragmentContainer,new LoginFragment());
                ft.commit();
                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        auth.removeAuthStateListener(authStateListener);
    }
}
