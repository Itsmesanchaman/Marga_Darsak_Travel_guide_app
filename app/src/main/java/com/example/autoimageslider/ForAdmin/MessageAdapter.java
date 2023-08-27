package com.example.autoimageslider.ForAdmin;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.autoimageslider.R;

import java.util.List;


public class MessageAdapter extends ArrayAdapter<Messages> {
    private final List<Messages> messageList;
    private final Context context;

    public MessageAdapter(Context context, List<Messages> messageList) {
        super(context, R.layout.item_message, messageList);
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_message, parent, false);
        }


        TextView interactionTextView = convertView.findViewById(R.id.interactionId);
        TextView visitLocationTextView = convertView.findViewById(R.id.visitLocationId);
        TextView tripSelectionTextView = convertView.findViewById(R.id.tripSelectionId);

        Messages message = messageList.get(position);

        Log.d("MessageAdapter", "Interaction: " + message.getInteraction());
        Log.d("MessageAdapter", "Visit Location: " + message.getVisitLocation());
        Log.d("MessageAdapter", "Trip Selection: " + message.getTripSelection());



        interactionTextView.setText(message.getInteraction());
        visitLocationTextView.setText(message.getVisitLocation());
        tripSelectionTextView.setText(message.getTripSelection());

        return convertView;
    }
}
