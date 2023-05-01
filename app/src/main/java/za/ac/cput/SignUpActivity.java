package za.ac.cput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signUpButton;
    private Button existingUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.signUpBtn);
        signUpButton.setOnClickListener(this);

        existingUserButton = findViewById(R.id.existingUserBtn);
        existingUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == signUpButton) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view == existingUserButton) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}