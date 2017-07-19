package com.example.corina.hobby;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class Subjects extends AppCompatActivity {
    private static final int SIGN_IN_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_subject);

        //accesul la sign in
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Subjects.this,
                                    "You have been signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Close activity
                            finish();
                        }
                    });
        }
        return true;
    }

        //button de legatura intre subiecte si chat-ul de mancare
        public void foodOnClick (View v){
            Intent intent = new Intent(v.getContext(), FoodChat.class);
            startActivityForResult(intent, 0);
        }
        //button de legatura intre subiecte si chat-ul de sport

    public void sportOnClick(View v) {
        Intent intent = new Intent(v.getContext(), SportChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de jocuri
    public void gamingOnClick(View v) {
        Intent intent = new Intent(v.getContext(), GamingChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de literatura
    public void literatureOnClick(View v) {
        Intent intent = new Intent(v.getContext(), LiteratureChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de filme
    public void moviesOnClick(View v) {
        Intent intent = new Intent(v.getContext(), MoviesChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de muzica
    public void musicOnClick(View v) {
        Intent intent = new Intent(v.getContext(), MusicChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de arta
    public void artOnClick(View v) {
        Intent intent = new Intent(v.getContext(), ArtChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de it
    public void itOnClick(View v) {
        Intent intent = new Intent(v.getContext(), ItChat.class);
        startActivityForResult(intent, 0);
    }

    //button de legatura intre subiecte si chat-ul de fashion
    public void fashionOnClick(View v) {
        Intent intent = new Intent(v.getContext(), FashionChat.class);
        startActivityForResult(intent, 0);
    }
}
