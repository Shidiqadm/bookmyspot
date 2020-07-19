package com.example.newpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.opencensus.tags.Tag;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText emailId, password ;
    EditText name, vehId;
    Button btnSignUp;
    TextView tvSignIn;
    String userID;

    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fstore;
    //FirebaseDatabase rootNode;
    //DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameTxt);
        vehId = findViewById(R.id.vehId);
        mFirebaseAuth = FirebaseAuth.getInstance();
        //reference = rootNode.getReference("users");
        fstore = FirebaseFirestore.getInstance();
        emailId = findViewById(R.id.mailTxt);
        password = findViewById(R.id.pwdTxt);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp = findViewById(R.id.signupBtn);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();
                final String fullname = name.getText().toString();
                final String vehicleid = vehId.getText().toString();

                if(email.isEmpty()) {
                    emailId.setError("Please enter a valid Email-Id");
                    emailId.requestFocus();
                }
                else if(fullname.isEmpty()){
                    name.setError("Please enter your name");
                    name.requestFocus();
                }
                else if(vehicleid.isEmpty()){
                    vehId.setError("Please enter your vehicle Reg No");
                    vehId.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())) {

//                    rootNode = FirebaseDatabase.getInstance();
//                    reference = rootNode.getReference("users");
//                    UserHelperClass helperClass = new UserHelperClass(fullname,email,pwd,vehicleid);
//                    reference.child(vehicleid).setValue(helperClass);

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                                userID = mFirebaseAuth.getCurrentUser().getUid();

                                DocumentReference documentReference = fstore.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("name",fullname);
                                user.put("email",email);
                                user.put("vehID",vehicleid);
                                documentReference.set(user);

                                startActivity(new Intent(MainActivity.this,DashboardActivity.class));
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Registration Failure!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
