package com.example.corina.hobby;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class FoodChat extends AppCompatActivity  {

    private FirebaseListAdapter<ChatMessages> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_chat);

        //add new chat messages to the Firebase real-time database
        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.send);
        //adding on click function to the send button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);
                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database

                FirebaseDatabase.getInstance()
                        .getReference("Food") //referinta la baza de date
                        .push()//punere in baza de date
                        .setValue(new ChatMessages(input.getText().toString(), //pune obiect de tipul chatmessage in baza de date
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );

                // Clear the input
                BubbleTextView bubbleTextView = (BubbleTextView) findViewById(R.id.message_text);
                bubbleTextView.setText("");
                input.setText("");

                //inchide keyboard-ul dupa ce ai trimis mesajul
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }
        });
        displayChatMessages();
    }
    private void displayChatMessages(){
        ListView listOfMessages = (ListView)findViewById(R.id.show);

        adapter = new FirebaseListAdapter <ChatMessages>(this, ChatMessages.class,
                R.layout.messages, FirebaseDatabase.getInstance().getReference("Food")) {
            @Override
            protected void populateView(View v, ChatMessages model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (BubbleTextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

}
