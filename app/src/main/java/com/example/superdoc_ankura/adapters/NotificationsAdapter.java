package com.example.superdoc_ankura.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.NotificationActivity;
import com.example.superdoc_ankura.pojos.response.GetNotificationsResponse;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsHolder> {
    NotificationActivity notificationActivity;
    ArrayList<GetNotificationsResponse> notificationsResponses;
    public NotificationsAdapter(NotificationActivity notificationActivity, ArrayList<GetNotificationsResponse> notificationsResponses) {
    this.notificationActivity = notificationActivity;
    this.notificationsResponses = notificationsResponses;
    }

    @NonNull
    @Override
    public NotificationsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.notification_item,viewGroup,false);
        NotificationsHolder holder = new NotificationsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsHolder holder, int i) {

        holder.notificationMessage.setText("Patient " +notificationsResponses.get(i).getPatientName()+ " Booked an appointment at "+notificationsResponses.get(i).getApptTime());

    }

    @Override
    public int getItemCount() {
        return notificationsResponses.size();
    }

    public class NotificationsHolder extends RecyclerView.ViewHolder{
        TextView notificationMessage;
        ImageView notificationClear;
        public NotificationsHolder(@NonNull View itemView) {
            super(itemView);
             notificationMessage = itemView.findViewById(R.id.notificationMessage);
             notificationClear = itemView.findViewById(R.id.notificationCrossMark);

            notificationMessage.setTypeface(notificationActivity.faceRegular);
        }
    }
}
