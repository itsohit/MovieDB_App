package contacts.test.moviesdatabase;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    Button submit_btn;
    String email_id;
    FirebaseAuth firebaseAuth;
    NetworkCheck networkCheck;
    EditText email_forget;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

            email_forget = (EditText) findViewById(R.id.email_id_forget);
           submit_btn = (Button) findViewById(R.id.submit_forget_btn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_forget);
              networkCheck = new NetworkCheck(this);
            firebaseAuth = FirebaseAuth.getInstance();

            submit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   email_id = email_forget.getText().toString().trim();
                    if(email_id.length()==0)
                        email_forget.setError("Invalid Email");
                    else if(networkCheck.checkInternet())
                        email_forget.setError("Invalid Email");
                    else if(networkCheck.checkEmail(email_id))
                        email_forget.setError("Invalid Email");
                    else{
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseAuth.sendPasswordResetEmail(email_id);
                        progressBar.setVisibility(View.GONE);

                  //      Toast.makeText(ForgetPassword.this,"Success send email",Toast.LENGTH_SHORT).show();
//                      Task task =firebaseAuth.sendPasswordResetEmail(email_id);(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//                                    email_forget.setText("");
//                                    progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(ForgetPassword.this,"Success, Check Mail",Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(ForgetPassword.this,Login_page.class));
//                                }
//                                else
//                                    Toast.makeText(ForgetPassword.this,"Email is not exists, Try Again",Toast.LENGTH_SHORT).show();
//                            }
//                        });
                    }
                }
            });
    }

}
