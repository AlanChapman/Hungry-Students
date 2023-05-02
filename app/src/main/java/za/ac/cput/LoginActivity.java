package za.ac.cput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private Button nonExistingUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);

        nonExistingUserButton = findViewById(R.id.nonExistingUserBtn);
        nonExistingUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == loginButton) {
            startActivity(new Intent(this, MainActivity.class));
        }
        if(view == nonExistingUserButton) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}