package dell.example.com.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton mAppCompatSignupButton;
    AppCompatButton mAppCompatLoginButton;
    TextInputLayout emailTextInputLayout;
    TextInputLayout passwordTextInputLayout;
  public   EditText emailEditText;
  public   EditText passwordEditText;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTextInputLayout=findViewById(R.id.email_text_input_layout);
        passwordTextInputLayout=findViewById(R.id.password_text_input_layout);
        emailEditText=findViewById(R.id.email_edit_text);
        passwordEditText=findViewById(R.id.password_edit_text);

        mAuth=FirebaseAuth.getInstance();



        mAppCompatLoginButton=findViewById(R.id.login_login_button);
        mAppCompatLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mAppCompatSignupButton=findViewById(R.id.login_signup_button);
        mAppCompatSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });






    }

}
