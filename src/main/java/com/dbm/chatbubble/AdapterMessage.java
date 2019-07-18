package com.dbm.chatbubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMessage extends ArrayAdapter<Message> {
    Context myContext;
    ArrayList<Message> myArrayList;

    public AdapterMessage(Context context, int resource, ArrayList<Message> arrayList) {
        super(context, resource);
        myContext = context;
        myArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return myArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Message currentMessage = myArrayList.get(position);
        int layoutResource = 0;
        LayoutInflater inflater = LayoutInflater.from(myContext);
        int viewType = getItemViewType(position);
        // 0 is the right chat bubble
        // 1 is the left chat bubble
        if (viewType == 0)
            layoutResource = R.layout.right_chat_bubble;
        else
            layoutResource = R.layout.left_chat_bubble;

        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.msg.setText(currentMessage.getContent());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    private class ViewHolder {
        private TextView msg;
        public ViewHolder(View v) {
            msg = (TextView) v.findViewById(R.id.txt_msg);
        }
    }
}
