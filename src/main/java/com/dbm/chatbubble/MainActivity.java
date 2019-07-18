package com.dbm.chatbubble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Message> listOfMessage = new ArrayList<>();
    private Message message;

    private AdapterMessage adapterMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the input View
        View layoutInput = findViewById(R.id.input);
        final EditText editText = (EditText) layoutInput.findViewById(R.id.msg_type);
        final Button sendButton = (Button) layoutInput.findViewById(R.id.btn_chat_send);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        // delete the divider line in the list view
        listView.setDivider(null);
        adapterMessage = new AdapterMessage(MainActivity.this, 0, listOfMessage);
        listView.setAdapter(adapterMessage);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageContent = editText.getText().toString();
                if (messageContent.trim().equals(""))
                    Toast.makeText(MainActivity.this, "Empty message! Try again", Toast.LENGTH_SHORT).show();
                else {
                    message = new Message(messageContent);
                    listOfMessage.add(message);
                    adapterMessage.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });
    }

}
