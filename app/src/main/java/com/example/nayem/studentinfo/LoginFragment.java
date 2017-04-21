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

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static String TAG="Login";
    EditText userName,password;
    Button loginBtn;
    TextView stateChanged;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_login, container, false);
        userName= (EditText) view.findViewById(R.id.loginUserName);
        password= (EditText) view.findViewById(R.id.loginPassword);
        loginBtn= (Button) view.findViewById(R.id.loginButton);
        stateChanged= (TextView) view.findViewById(R.id.loginStateChanged);

        loginBtn.setOnClickListener(this);
        stateChanged.setOnClickListener(this);

        auth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=auth.getCurrentUser();
                if (firebaseUser!= null){
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
            case R.id.loginButton:
                String email=userName.getText().toString().trim();
                String pass=password.getText().toString().trim();

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(TAG,"onComplete"+task.isSuccessful());
                        if (!task.isSuccessful()){
                            Toast.makeText(getActivity(),"authentication failed",Toast.LENGTH_SHORT).show();
                        }else {
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            String currentEmail=firebaseUser.getEmail();
                           // Toast.makeText(getActivity(),"Welcome "+currentEmail,Toast.LENGTH_SHORT).show();
                            FragmentManager fm=getActivity().getSupportFragmentManager();
                            FragmentTransaction ft=fm.beginTransaction();
                            ft.replace(R.id.fragmentContainer,new StudentRegistrationFragment());
                            ft.commit();

                        }
                    }
                });

                break;
            case R.id.loginStateChanged:
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                SignupFragment signupFragment=new SignupFragment();
                ft.replace(R.id.fragmentContainer,signupFragment);
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
