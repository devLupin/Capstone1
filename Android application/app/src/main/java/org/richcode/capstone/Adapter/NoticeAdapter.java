package org.richcode.capstone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.richcode.capstone.Data.NoticeInfo;
import org.richcode.capstone.Data.SeatInfo;
import org.richcode.capstone.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    ArrayList<NoticeInfo> list = new ArrayList<NoticeInfo>();
    Context context;

    public NoticeAdapter(ArrayList<NoticeInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice,viewGroup,false);

        NoticeAdapter.ViewHolder vh = new NoticeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.NameText.setText(list.get(i).getName());
        viewHolder.DateText.setText(list.get(i).getDate());
        viewHolder.ContentText.setText(list.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView NameText;
        TextView DateText;
        TextView ContentText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameText = (TextView)itemView.findViewById(R.id.item_notice_username);
            DateText = (TextView)itemView.findViewById(R.id.item_notice_date);
            ContentText = (TextView)itemView.findViewById(R.id.item_notice_content);
        }

    }
}
