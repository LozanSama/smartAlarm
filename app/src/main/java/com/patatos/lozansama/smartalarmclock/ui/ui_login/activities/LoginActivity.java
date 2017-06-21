package com.patatos.lozansama.smartalarmclock.ui.ui_login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.domain.User;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities.AlarmList;
import com.patatos.lozansama.smartalarmclock.util.RealmUtil;
import com.patatos.lozansama.smartalarmclock.util.ValidateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email_login)
    EditText etEmail;
    @BindView(R.id.et_password_login)
    EditText etPassword;

    private FirebaseAuth mFireAuth;
    private FirebaseAuth.AuthStateListener mFireAuthListener;

    private String email;
    private String password;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity_layout);

        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);

        final Intent intent = new Intent(this, AlarmList.class);
        mFireAuth = FirebaseAuth.getInstance();

        final Realm realm = Realm.getDefaultInstance();
        mFireAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    RealmUtil.addToRealm(realm, email, null);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


    @OnClick(R.id.btn_login)
    public void onClickBtn() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (!ValidateUtil.validation(this, etEmail, etPassword, email, password)) {
            Toast.makeText(getBaseContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
        } else {
            mFireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), R.string.login_successful,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @OnClick(R.id.tv_noAccount)
    public void onClickTv() {
        finish();
    }

    @OnClick(R.id.tv_forgot_password)
    public void onClickForgot() {
        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.introduce_email,
                    Toast.LENGTH_LONG).show();
        } else {
            mFireAuth.sendPasswordResetEmail(etEmail.getText().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireAuth.addAuthStateListener(mFireAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFireAuth.removeAuthStateListener(mFireAuthListener);
    }

}
