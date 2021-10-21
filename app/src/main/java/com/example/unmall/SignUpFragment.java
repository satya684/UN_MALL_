package com.example.unmall;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private TextView alreadyHaveAnAccout;
    private FrameLayout parentFrameLayout;


    private EditText fullName;
    private  EditText email;
    private EditText password;
    private EditText confirmPassword;


    private ImageButton closebtn;
    private Button signUpbtn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    private  String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public static boolean disableCloseBtn = false;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        alreadyHaveAnAccout = view.findViewById(R.id.alreadyHaveAnAccount);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_Layout);

        fullName = view.findViewById(R.id.signUpfullName);
        email = view.findViewById(R.id.signUpEmailId);
        password = view.findViewById(R.id.signUpPassword);
        confirmPassword = view.findViewById(R.id.signUpConfirmPassword);

        progressBar = view.findViewById(R.id.signUp_progressBar);

        signUpbtn = view.findViewById(R.id.signUpbtn);
        closebtn = view.findViewById(R.id.signUpCroosClose);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();

        alreadyHaveAnAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        if (disableCloseBtn == true){
            closebtn.setVisibility(View.GONE);
        }else {
            closebtn.setVisibility(View.VISIBLE);
        }
        return  view;
    }
    private  void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mainIntent();

            }
        });


        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }
    private  void checkInputs(){
        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(fullName.getText())){
                if (!TextUtils.isEmpty(password.getText()) && password.length() >=8){
                    if (!TextUtils.isEmpty(confirmPassword.getText())){
                        signUpbtn.setEnabled(true);
                    }else {
                        signUpbtn.setEnabled(false);

                    }
                }else {
                    signUpbtn.setEnabled(false);

                }
            }else {
                signUpbtn.setEnabled(false);

            }
        }else {
            signUpbtn.setEnabled(false);

        }
    }
    private  void checkEmailAndPassword(){

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error);
        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());
        if (email.getText().toString().matches(emailPattern)){
            if (password.getText().toString().equals(confirmPassword.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);
                signUpbtn.setEnabled(false);

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Map<Object, String > userdata = new HashMap<>();
                                    userdata.put("fullname", fullName.getText().toString());

                                    firebaseFirestore.collection("USERS")
                                            .add(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {

                                                    if (task.isSuccessful()){

                                                       mainIntent();

                                                    }else {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        signUpbtn.setEnabled(true);
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                    }


                                                }
                                            });




                                }else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpbtn.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                confirmPassword.setError("Password does't matched!",customErrorIcon);


            }
        }else {

            email.setError("Invalid email!", customErrorIcon);

        }
    }
    private  void mainIntent(){
        if (disableCloseBtn){
            disableCloseBtn = false;
        }else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);
            disableCloseBtn = false;
        }
        getActivity().finish();
    }
}