package com.example.finalyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {

    EditText etname, etMobileNo, etEmailId, etUserName, etPassword;
    Button btnSignUp;
    CheckBox cbShowHidePassword;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle("Registration Activity");

        preferences = PreferenceManager.getDefaultSharedPreferences(RegistrationActivity.this);
        editor = preferences.edit();

        // Corrected initialization of views
        etname = findViewById(R.id.etRegisterName);
        etMobileNo = findViewById(R.id.etRegisterMobileNO);
        etEmailId = findViewById(R.id.etRegisterEmail);
        etUserName = findViewById(R.id.etRegisterUserName);
        etPassword = findViewById(R.id.etRegisterPassword);
        btnSignUp = findViewById(R.id.btnRegisterSignUP);
        cbShowHidePassword = findViewById(R.id.cbRegisterShowHidePassword);

        cbShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etname.getText().toString().isEmpty()) {
                    etname.setError("Please enter Name");
                } else if (etMobileNo.getText().toString().isEmpty()) {
                    etMobileNo.setError("Please enter Mobile Number");
                } else if (etEmailId.getText().toString().isEmpty()) {
                    etEmailId.setError("Please enter Email Id");
                } else if (etUserName.getText().toString().isEmpty()) {
                    etUserName.setError("Please enter Username");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please enter Password");
                } else if (!etPassword.getText().toString().matches(".*[A-Z].*")) {
                    etPassword.setError("Please enter at least one Uppercase letter");
                } else if (!etPassword.getText().toString().matches(".*[a-z].*")) {
                    etPassword.setError("Please enter at least one Lowercase letter");
                } else if (!etPassword.getText().toString().matches(".*[0-9].*")) {
                    etPassword.setError("Please enter at least one digit");
                } else if (!etPassword.getText().toString().matches(".*[!@#$*].*")) {
                    etPassword.setError("Please enter at least one Special Symbol");
                } else if (!etEmailId.getText().toString().contains("@gmail.com")) {
                    etEmailId.setError("Email must contain @gmail.com");
                } else if (etMobileNo.getText().toString().length() != 10) {
                    etMobileNo.setError("Please enter 10 digit");
                } else {
                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait...!!");
                    progressDialog.setMessage("Registration is in Process");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                   PhoneAuthProvider.getInstance().verifyPhoneNumber(
                           "+91" + etMobileNo.getText().toString(),
                           60, TimeUnit.SECONDS, RegistrationActivity.this,
                           new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                               @Override
                               public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                   progressDialog.dismiss();
                                   Toast.makeText(RegistrationActivity.this,"Verification Success",Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onVerificationFailed(@NonNull FirebaseException e) {
                                   progressDialog.dismiss();
                                   Toast.makeText(RegistrationActivity.this,"Verification Failed",Toast.LENGTH_SHORT).show();

                               }

                               @Override
                               public void onCodeSent(@NonNull String verificationCode ,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                   Intent intent = new Intent(RegistrationActivity.this,VerifyOTPActivity.class);
                                   intent.putExtra("verificationcode",verificationCode);
                                   intent.putExtra("name",etname.getText().toString());
                                   intent.putExtra("mobileno",etMobileNo.getText().toString());
                                   intent.putExtra("email",etEmailId.getText().toString());
                                   intent.putExtra("username",etUserName.getText().toString());
                                   intent.putExtra("password",etPassword.getText().toString());
                                   startActivity(intent);
                               }
                           }
                   );

                }
            }
        });
    }
}