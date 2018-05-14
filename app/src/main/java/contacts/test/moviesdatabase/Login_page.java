package contacts.test.moviesdatabase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_page extends AppCompatActivity implements TextView.OnEditorActionListener {
        Button login,registration;
    EditText email_id,password_et;
    TextView forget_btn;
    String email,pass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    NetworkCheck networkCheck;
    FirebaseAuth firebaseAuth;
        ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

            login = (Button) findViewById(R.id.login_button);
            registration = (Button) findViewById(R.id.registation_btn_in_loginpage);
            email_id = (EditText) findViewById(R.id.email_login);
            password_et = (EditText) findViewById(R.id.password_login);
            forget_btn = (TextView) findViewById(R.id.forget_password_tv);

            networkCheck = new NetworkCheck(this);
           firebaseAuth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(Login_page.this);
        progress.setMessage("Processing");
        login.setOnEditorActionListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                loginMethod();
             }
         });

         registration.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Login_page.this,Registration_page.class));
             }
         });

         forget_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                    startActivity(new Intent(Login_page.this,ForgetPassword.class));
             }
         });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId==EditorInfo.IME_ACTION_DONE){
            Login_page.this.loginMethod();
            return true; }
        return false;
    }
    private void loginMethod(){
        email = email_id.getText().toString().trim();
        pass = password_et.getText().toString().trim();

        if(email.length()==0){
            email_id.setError("Invalid Name");
            email_id.setFocusable(true); }
        else if(checkEmail(email)) {
            email_id.setError("Invalid Mobile No");
            email_id.setFocusable(true);
        }
       else if(password_et.length()==0)
            password_et.setError("Invalid Password");
        else if(networkCheck.checkInternet())
            Toast.makeText(Login_page.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        else{
            progress.show();
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        email_id.setText("");
                        password_et.setText("");
                        Toast.makeText(Login_page.this, "Success", Toast.LENGTH_SHORT).show();
                          progress.dismiss();
                        startActivity(new Intent(Login_page.this, Navigation_Drawer.class));
                    } else {
                        progress.dismiss();
                        Toast.makeText(Login_page.this, "Wrong Email and Password, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }

            });
         }
    }
    private Boolean checkEmail(String email){
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.find())
            return false;

        return true;
    }

    @Override
    public void onBackPressed() {

    }
}
