package com.example.autoimageslider.ForAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.autoimageslider.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<UserData> {

    public UserListAdapter(Context context, List<UserData> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
        }

        UserData user = getItem(position);

        TextView usernameTextView = convertView.findViewById(R.id.userUsername);
        ImageView userImageView = convertView.findViewById(R.id.userImageView);

        usernameTextView.setText(user.getUsername());
        Glide.with(getContext())
                .load(user.getUserPhotoUrl())
                .circleCrop()
                .into(userImageView);

        return convertView;
    }
}

