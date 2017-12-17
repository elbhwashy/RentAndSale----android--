package com.example.rentaandsale.realestate.AnnouncementData;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.rentaandsale.realestate.R;
        import com.example.rentaandsale.realestate.UserData.UserAdapter;

        import java.util.List;



public class AnnouncementDetailsAdapter extends RecyclerView.Adapter<AnnouncementDetailsAdapter.AnnouncementDetailsViewHolder> {
    private List<Announcement> listAnnouncements;
    public AnnouncementDetailsAdapter(List<Announcement> listAnnouncements) {
        this.listAnnouncements = listAnnouncements;
    }


    @Override
    public AnnouncementDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_details, parent, false);

        return new AnnouncementDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnnouncementDetailsAdapter.AnnouncementDetailsViewHolder holder, int position) {
        holder.textViewTitle.setText(listAnnouncements.get(position).getAnnouncementTitle());
        holder.textViewPrice.setText(listAnnouncements.get(position).getAnnouncementPrice());
        holder.textViewType.setText (listAnnouncements.get(position).getAnnouncementType());

    }



    @Override
    public int getItemCount() {
        Log.v(AnnouncementDetailsAdapter.class.getSimpleName(),""+listAnnouncements.size());
        return listAnnouncements.size();
    }

    public class AnnouncementDetailsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewPrice;
        public TextView textViewType;
        public TextView textViewNumberOfRooms;
        public TextView textViewFloor;
        public TextView textViewDescription;

        public AnnouncementDetailsViewHolder(View view) {
            super(view);
            textViewTitle = (TextView) view.findViewById(R.id.text_view_title);
            textViewPrice = (TextView) view.findViewById(R.id.text_view_price);
            textViewType = (TextView) view.findViewById(R.id.text_view_type);



        }
    }
}
