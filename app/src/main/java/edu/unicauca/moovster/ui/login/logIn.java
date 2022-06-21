package edu.unicauca.moovster.ui.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import edu.unicauca.moovster.MainActivity;
import edu.unicauca.moovster.R;
import edu.unicauca.moovster.db.AdminsSQLHelper;
import edu.unicauca.moovster.ui.home.HomeFragment;
import edu.unicauca.moovster.ui.movies.Show_Movie_list;
import edu.unicauca.moovster.ui.profile.ProfileFF;
import edu.unicauca.moovster.ui.profile.ProfileFragment;

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
                Boolean validation = true;
                    TextView txtEmail = view.findViewById(R.id.loginEmail);
                    TextView txtPassword = view.findViewById(R.id.loginPassword);
                    AdminsSQLHelper admin = new AdminsSQLHelper(getContext(), "dbMoovster", null, 1);
                    SQLiteDatabase Db = admin.getWritableDatabase();
                    MainActivity activity= (MainActivity)getActivity();
                    boolean isEmailValid =Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches();
                    TextInputLayout email = view.findViewById(R.id.loginEmailC);
                    TextInputLayout password = view.findViewById(R.id.loginPasswordC);
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

                if (validation == true) {
                    Cursor fila = Db.rawQuery("select name, email from User where email = '"+txtEmail.getText().toString()+"' and password = '"+txtPassword.getText().toString()+"'",null);
                    if (fila.moveToFirst()) {

                        activity.setUserLogged(true, fila.getString(1));
                        activity.setUserName(fila.getString(0));
                        ContentValues registroUser = new ContentValues();
                        registroUser.put("user_email", txtEmail.getText().toString());
                        registroUser.put("isLogged", "true");
                        Db.insert("UserLogged", null, registroUser);
                        Toast.makeText(getContext(),getString(R.string.loginSuccesfull),Toast.LENGTH_SHORT).show();
                        Db.close();
                        MaterialButton btnUser = getActivity().findViewById(R.id.btnUserLog);
                        btnUser.setIcon(ContextCompat.getDrawable(getContext(),R.drawable.logout));
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace( R.id.nav_host_fragment_activity_main, new ProfileFragment());
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(getContext(),getString(R.string.loginWarning),Toast.LENGTH_SHORT).show();
                        Db.close();
                    }
                }

            }
        });
    }


}