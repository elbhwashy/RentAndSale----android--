package com.example.rentaandsale.realestate.AnnouncementData;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentaandsale.realestate.AnnouncementActivity;
import com.example.rentaandsale.realestate.AnnouncementDetailsActivity;
import com.example.rentaandsale.realestate.LogInActivity;
import com.example.rentaandsale.realestate.MainActivity;
import com.example.rentaandsale.realestate.R;
import com.example.rentaandsale.realestate.UserData.UserAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {
    private ArrayList<Announcement> announcements = new ArrayList<Announcement>();
    Context ctx;
    public AnnouncementAdapter(ArrayList<Announcement> listAnnouncements,Context ctx) {
        this.announcements = listAnnouncements;
        this.ctx = ctx;
    }


    @Override
    public AnnouncementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_announcement, parent, false);

        return new AnnouncementViewHolder(itemView,ctx,announcements);
    }

    @Override
    public void onBindViewHolder(AnnouncementAdapter.AnnouncementViewHolder holder, int position) {
        holder.textViewTitle.setText(announcements.get(position).getAnnouncementTitle());
        holder.textViewPrice.setText(announcements.get(position).getAnnouncementPrice());
        holder.textViewType.setText (announcements.get(position).getAnnouncementType());
     }



    @Override
    public int getItemCount() {
        Log.v(AnnouncementAdapter.class.getSimpleName(),""+announcements.size());
        return announcements.size();
    }

    public class AnnouncementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewTitle;
        public TextView textViewPrice;
        public TextView textViewType;
        public LinearLayout linearLayout;
        ArrayList<Announcement> announcements = new ArrayList<Announcement>();
        Context ctx;

        public AnnouncementViewHolder(View view,Context ctx , ArrayList<Announcement> announcements) {
            super(view);
            view.setOnClickListener(this);
            this.announcements = announcements;
            this.ctx = ctx;
            textViewTitle = (TextView) view.findViewById(R.id.textViewTilte);
            textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
            textViewType = (TextView) view.findViewById(R.id.textViewType);
            linearLayout = (LinearLayout)view.findViewById(R.id.linear);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Announcement announcement = this.announcements.get(position);
            Intent intent = new Intent(this.ctx , AnnouncementDetailsActivity.class);
            intent.putExtra("announcementTitle" ,announcement.getAnnouncementTitle());
            intent.putExtra("announcementPrice" ,announcement.getAnnouncementPrice());
            intent.putExtra("announcementType" ,announcement.getAnnouncementType());
            this.ctx.startActivity(intent);

        }
    }
}
