package edu.unicauca.moovster.ui.login;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.unicauca.moovster.R;
import edu.unicauca.moovster.db.AdminsSQLHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_register extends Fragment {

    private TextInputLayout uName, uEmail, uPassword;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static user_register newInstance(String param1, String param2) {
        user_register fragment = new user_register();
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

        uName = (TextInputLayout) uName.findViewById(R.id.registerName);
        uEmail = (TextInputLayout) uEmail.findViewById(R.id.registerEmail);
        uPassword = (TextInputLayout) uPassword.findViewById(R.id.registerPassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdminsSQLHelper admin = new AdminsSQLHelper(getContext(), "administracion", null, 1);
        SQLiteDatabase Db = admin.getWritableDatabase();
        Button btnLogin = view.findViewById(R.id.btnSingUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtName = view.findViewById(R.id.registerName);
                String tvName = txtName.getText().toString();
                TextInputLayout name = view.findViewById(R.id.registerNameC);
                name.setHelperText("true");

                TextView txtEmail = view.findViewById(R.id.registerEmail);
                String tvEmail = txtEmail.getText().toString();
                TextInputLayout email = view.findViewById(R.id.registerEmailC);
                email.setHelperText("true");

                TextView txtPsw = view.findViewById(R.id.registerPassword);
                String tvPsw = txtPsw.getText().toString();
                TextInputLayout Psw = view.findViewById(R.id.registerPasswordC);
                Psw.setHelperText("true");
            }
        });
    }
}