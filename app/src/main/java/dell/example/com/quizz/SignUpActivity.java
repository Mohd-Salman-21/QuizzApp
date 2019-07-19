package dell.example.com.quizz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout nameTextInputLayout;
    TextInputLayout emailTextInputLayout;
    TextInputLayout passwordTextInputLayout;

    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;

    AppCompatButton signUPAppCompatButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();

        nameTextInputLayout=findViewById(R.id.name_text_input_layout);
        emailTextInputLayout=findViewById(R.id.email_text_input_layout);
        passwordTextInputLayout=findViewById(R.id.password_text_input_layout);

        nameEditText=findViewById(R.id.name_edit_text);
        emailEditText=findViewById(R.id.email_edit_text);
        passwordEditText=findViewById(R.id.password_edit_text);

        signUPAppCompatButton=findViewById(R.id.sign_up);

        signUPAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=nameEditText.getText().toString().trim();
                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        }else
                        {
                            Toast.makeText(SignUpActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });



    }
}
