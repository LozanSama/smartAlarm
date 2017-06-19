package com.patatos.lozansama.smartalarmclock.util;

import android.content.Context;
import android.widget.EditText;

import com.patatos.lozansama.smartalarmclock.R;

public class ValidateUtil {

    public static boolean validation(Context mContext, EditText etEmail, EditText etPassword, String email, String password) {
        boolean validate = true;

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(mContext.getString(R.string.errorEmail));
            validate = false;

        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError(mContext.getString(R.string.errorPassword));
            validate = false;

        } else {
            etPassword.setError(null);
        }

        return validate;
    }
}
