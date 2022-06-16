package edu.unicauca.moovster.ui.logIn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import edu.unicauca.moovster.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link logIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class logIn extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public logIn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment logIn.
     */
    // TODO: Rename and change types and number of parameters
    public static logIn newInstance(String param1, String param2) {
        logIn fragment = new logIn();
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
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogin = view.findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    TextView txtEmail = view.findViewById(R.id.loginEmail);
                    TextView txtPassword = view.findViewById(R.id.loginPassword);
                    boolean isEmailValid =Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches();
                    TextInputLayout email = view.findViewById(R.id.loginEmailC);
                    TextInputLayout password = view.findViewById(R.id.loginPasswordC);
                    if (isEmailValid){
                        email.setHelperText("");
                    }else {
                        email.setHelperText("Ingrese un email valido.");
                    }

                    if (txtPassword.getText().toString().length()>0 && txtPassword.getText().toString().length()<=16){
                        password.setHelperText("");
                    }else {
                        password.setHelperText(getString(R.string.required));
                    }
            }
        });
    }


}