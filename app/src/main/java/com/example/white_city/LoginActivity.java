package com.example.white_city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    TextView crearcuenta;
    private static final String TAG = "GoogleActivity";
    private static int RC_SIGN_IN = 001;

    //[START declare_auth]
    private FirebaseAuth mAuth;
    //END declare_auth

    //Agregar cliente de inicio de sesion de Google
    private GoogleSignInClient mGoogleSignInClient;
    private Button btnSignIn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crearcuenta=findViewById(R.id.crearcuenta);
        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigIn();
            }
        });
        //btnSignIn = findViewById(R.id.buttonLogin);
        // btnSignIn.setOnClickListener(new View.OnClickListener() --->  //Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //Crear  un Google SignInClient  con las opciones espevificadas por gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        //Inicialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //[END inicialize_auth
    }
    //[START on_start_check_user]

    @Override
    public void onStart() {
        super.onStart();
        //Check if user signed in(non-null) and update UI accordingly-
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //[END  om_start_check_user]

    //[START onactivityresult]

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Result returned from launching the Intent from GoogleSignInApi.getSignInInten(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In msm successfull, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWitGoogle;" + account.getId());
                firebaseAuthWhithGoogle(account.getIdToken());
            } catch (ApiException e) {
                //Google Sign In failed, update UI appropiately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    //[END onactivityresult]

    //[START auth_with_google]
    private void firebaseAuthWhithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in falls, update UI with the signed-in user´s information
                            Log.d(TAG, "signInWhitCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //If sign in falls, display a message to the user.
                            Log.w(TAG, "signWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
    //[END auth_with_google]


    //[Start sign in]
    private void sigIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //[END sign in]
    private void updateUI(FirebaseUser user) {
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        if (users != null) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(LoginActivity.this, "Ingresó", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnButtonSingIn(View view) {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}