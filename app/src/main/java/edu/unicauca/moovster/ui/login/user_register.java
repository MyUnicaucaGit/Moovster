package edu.unicauca.moovster.ui.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.unicauca.moovster.MainActivity;
import edu.unicauca.moovster.R;
import edu.unicauca.moovster.db.AdminsSQLHelper;


public class user_register extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user_register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register_user.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogin = view.findViewById(R.id.btnSingUpUser);
        MainActivity activity = (MainActivity) getActivity();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean validation = true;
                AdminsSQLHelper admin = new AdminsSQLHelper(getContext(), "dbMoovster", null, 1);
                SQLiteDatabase Db = admin.getWritableDatabase();
                TextView txtEmail = view.findViewById(R.id.registerEmail);
                TextView txtPassword = view.findViewById(R.id.registerPassword);
                TextView txtName = view.findViewById(R.id.registerName);
                boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches();
                TextInputLayout name = view.findViewById(R.id.registerNameC);
                TextInputLayout email = view.findViewById(R.id.registerEmailC);
                TextInputLayout password = view.findViewById(R.id.registerPasswordC);
                if (isEmailValid){
                    email.setHelperText("");
                }else {
                    email.setHelperText(getString(R.string.emailHelper));
                    validation = false;
                }
                if (txtPassword.getText().toString().length()>0 && txtPassword.getText().toString().length()<=16){
                    password.setHelperText("");
                }else {
                    password.setHelperText(getString(R.string.required));
                    validation = false;
                }
                if (txtName.getText().toString().length()>0){
                    name.setHelperText("");
                }else {
                    password.setHelperText(getString(R.string.required));
                    validation = false;
                }
                    if (validation == true) {
                        Cursor fila = Db.rawQuery("select name, email from User where email = '"+txtEmail.getText().toString()+"'",null);
                        if (fila.moveToFirst()) {
                            Toast.makeText(getContext(),getString(R.string.existingUser),Toast.LENGTH_SHORT).show();
                            Db.close();
                        } else {
                            ContentValues registroUser = new ContentValues();
                            registroUser.put("name", txtName.getText().toString());
                            registroUser.put("email", txtEmail.getText().toString());
                            registroUser.put("password", txtPassword.getText().toString());
                            MainActivity activity = (MainActivity) getActivity();
                            Db.insert("User", null, registroUser);
                            Db.close();

                            txtName.setText("");
                            txtEmail.setText("");
                            txtPassword.setText("");

                            Toast.makeText(getContext(), getString(R.string.registerSuccesfull), Toast.LENGTH_SHORT).show();

                            FragmentManager fragmentManager = activity.getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace( R.id.nav_host_fragment_activity_main, new login_or_register());
                            fragmentTransaction.commit();
                        }

                    } else {
                        Toast.makeText(getContext(), getString(R.string.dataWarning), Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}