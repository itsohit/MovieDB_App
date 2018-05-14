package contacts.test.moviesdatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration_page extends AppCompatActivity implements TextView.OnEditorActionListener {
    FirebaseAuth firebaseAuth;
    EditText email_id_et,mobile_no_et,name_et,password_et;
    String name,email,mobileno,password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button signup;
    User user;
    ProgressDialog progressBar;
    DatabaseReference mdatabase;
    NetworkCheck networkCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        firebaseAuth = FirebaseAuth.getInstance();
        email_id_et = (EditText) findViewById(R.id.email_register);
        mobile_no_et = (EditText) findViewById(R.id.mobile_no_register);
        name_et = (EditText) findViewById(R.id.username_registration);
        password_et = (EditText) findViewById(R.id.password_register);
        signup = (Button) findViewById(R.id.register_btn);
     //   progressBar = (ProgressBar) findViewById(R.id.progress_bar_registration);
        mdatabase = FirebaseDatabase.getInstance().getReference();
        progressBar = new ProgressDialog(this);
         networkCheck = new NetworkCheck(this);
        signup.setOnEditorActionListener(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              registerMethod();
            }
        });
    }

  private Boolean checkEmail(String email){
      Pattern pattern = Pattern.compile(emailPattern);
      Matcher matcher = pattern.matcher(email);
       if(matcher.find())
            return false;

      return true;
  }
   private void registerMethod(){
       name = name_et.getText().toString().trim();
       email = email_id_et.getText().toString().trim();
       password = password_et.getText().toString().trim();
       mobileno = mobile_no_et.getText().toString().trim();

       if(name.length()==0){
           name_et.setError("Invalid Name");
           name_et.setFocusable(true); }
       else if(mobileno.length()==0)
           mobile_no_et.setError("Invalid Mobile No");
       else if(mobileno.length()>10)
           mobile_no_et.setError("Invalid Mobile No");
       else if(checkEmail(email))
           email_id_et.setError("Invalid Email");
       else if(password.length()==0)
           password_et.setError("Invalid Password");
       else if(password.length()<6) {
           password_et.setError("Small Password");
           Toast.makeText(Registration_page.this,"Enter long Password",Toast.LENGTH_SHORT).show();
       }else if(networkCheck.checkInternet())
           Toast.makeText(Registration_page.this,"No Internet connection",Toast.LENGTH_SHORT).show();
       else{
           progressBar.setMessage("Processing");
           progressBar.show();
           firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       user = new User(name,email,mobileno);
                       mdatabase.child("users").child(firebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if(task.isSuccessful()){
                                           name_et.setText("");
                                           mobile_no_et.setText("");
                                           email_id_et.setText("");
                                           password_et.setText("");
                                           Toast.makeText(Registration_page.this,"Successfully",Toast.LENGTH_SHORT).show();
                                           progressBar.dismiss();
                                           startActivity(new Intent(Registration_page.this,Login_page.class)); }
                                       else
                                           Toast.makeText(Registration_page.this,"Registration Fail, Try Again",Toast.LENGTH_SHORT).show();
                                   }
                               });

                   }else
                       Toast.makeText(Registration_page.this,"Registration Fail, Try Again",Toast.LENGTH_SHORT).show();
               }
           });
       }
   }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
         if(actionId== EditorInfo.IME_ACTION_DONE){
             Registration_page.this.registerMethod();
           return true; }
        return false;
    }
}
