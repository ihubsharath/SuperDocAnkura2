package com.example.superdoc_ankura.adapters;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.holders.AllAppointmentsHolder;
import com.example.superdoc_ankura.pojos.request.CloseConsultantRequest;
import com.example.superdoc_ankura.pojos.request.StartConsultantRequest;
import com.example.superdoc_ankura.pojos.response.AllAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.CancelAppointmentResponse;
import com.example.superdoc_ankura.pojos.response.CloseConsultantResponse;
import com.example.superdoc_ankura.pojos.response.NoShowAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.StartConsultantResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAppointmentsAdapter extends RecyclerSwipeAdapter<AllAppointmentsHolder> {

    AllAppointmentsActivity allAppointmentsActivity;
    List<AllAppointmentsResponse> allAppointmentsResponses;
    int appid, currentAppID;
    int adapterPostion;
    boolean undo;

    ////to open one recycler-view item at a time
    SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public AllAppointmentsAdapter(AllAppointmentsActivity allAppointmentsActivity, List<AllAppointmentsResponse> allAppointmentsResponses) {
        this.allAppointmentsActivity = allAppointmentsActivity;
        this.allAppointmentsResponses = allAppointmentsResponses;


    }


    @NonNull
    @Override
    public AllAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(allAppointmentsActivity);
        View view = layoutInflater.inflate(R.layout.all_appointments_itemview, viewGroup, false);
        AllAppointmentsHolder holder = new AllAppointmentsHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AllAppointmentsHolder holder, final int i) {

        holder.tvTime.setText(allAppointmentsResponses.get(i).getApptTime());
        holder.tvPatientName.setText(allAppointmentsResponses.get(i).getPatientName() + "/" + allAppointmentsResponses.get(i).getApptId());
        holder.tvApptProcedure.setText(allAppointmentsResponses.get(i).getProcedure());



        if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Pending")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
        }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Confirm")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
        }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Reschedule")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
        }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Cancel")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
        }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Checkin")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
            holder.tvStatus.setBackgroundColor(Color.parseColor("#68D8D6"));
        }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("CheckOut")){
            holder.tvStatus.setText("OUT");
            holder.tvStatus.setBackgroundColor(Color.parseColor("#CBC7C7"));
        } else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("NoShow")){
            holder.tvStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
            holder.tvStatus.setBackgroundColor(Color.parseColor("#FF7C7C"));
        }




        if (allAppointmentsResponses.get(i).isFirst() == true) {
            holder.isFirst.setVisibility(View.VISIBLE);
        } else {
            holder.isFirst.setVisibility(View.GONE);
        }
        if (allAppointmentsResponses.get(i).getRefferedType() != null && allAppointmentsResponses.get(i).getRefferedType().equalsIgnoreCase("Walkin")) {
            holder.isWalkin.setVisibility(View.VISIBLE);
        } else {
            holder.isWalkin.setVisibility(View.GONE);
        }
//        holder.tvTime.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvPatientName.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvApptStatus.setTypeface(allAppointmentsActivity.faceLight);


        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.left));
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_to_left));
//to open one recycler-view item at a time
        mItemManger.bindView(holder.itemView, i);
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                undo = false;
                // allAppointmentsActivity.start = false;
                appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
                //currentAppID = appid;
                if (layout.getOpenStatus() == SwipeLayout.Status.Open) {

                    mItemManger.closeAllExcept(layout);


                }

            }

            @Override
            public void onOpen(final SwipeLayout layout) {

                if (layout.getDragEdge() == SwipeLayout.DragEdge.Left) {
                    if (allAppointmentsActivity.start) {
                        allAppointmentsActivity.start = true;
                        //allAppointmentsActivity.showAlertDialog("START button clicked");


                        appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
                        currentAppID = appid;
                        Log.d("appid-open", String.valueOf(appid));
//starting consultaion
                        if (layout.getDragEdge() == SwipeLayout.DragEdge.Left) {
//starting timer
                            startChronometer(holder);
//swaping item to top
                            adapterPostion = holder.getAdapterPosition();
                            Collections.swap(allAppointmentsResponses, holder.getAdapterPosition(), 0);
                            allAppointmentsActivity.allAppointmentsAdapter.notifyItemMoved(holder.getAdapterPosition(), 0);
                            allAppointmentsActivity.rview.scrollToPosition(0);

                            Log.d("adapterposition", String.valueOf(adapterPostion));

                            layout.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (layout.getOpenStatus() == SwipeLayout.Status.Open) {
                                        if (layout.getDragEdge() == SwipeLayout.DragEdge.Left) {
                                            if (holder.layout_undo.getVisibility() == View.VISIBLE) {
                                                try {
                                                    holder.tvTime2.setText(allAppointmentsResponses.get(holder.getAdapterPosition()).getApptTime());
                                                    holder.tvPatientName2.setText(allAppointmentsResponses.get(holder.getAdapterPosition()).getPatientName());
                                                    holder.tvApptProcedure2.setText(allAppointmentsResponses.get(holder.getAdapterPosition()).getProcedure());

                                                    if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Pending")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                    }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Confirm")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                    }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Reschedule")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                    }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Cancel")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                    }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("Checkin")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                        holder.tvStatus2.setBackgroundColor(Color.parseColor("#68D8D6"));
                                                    }else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("CheckOut")){
                                                        holder.tvStatus2.setText("OUT");
                                                        holder.tvStatus2.setBackgroundColor(Color.parseColor("#CBC7C7"));
                                                    } else if (allAppointmentsResponses.get(i).getApptStatus().equalsIgnoreCase("NoShow")){
                                                        holder.tvStatus2.setText(allAppointmentsResponses.get(i).getApptStatus());
                                                        holder.tvStatus2.setBackgroundColor(Color.parseColor("#FF7C7C"));
                                                    }

                                                    startConsultation(holder, appid);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            holder.swipeLayout2.setRightSwipeEnabled(false);
                                            holder.swipeLayout2.setShowMode(SwipeLayout.ShowMode.LayDown);
                                            holder.swipeLayout2.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.left_to_right));
                                            holder.swipeLayout2.addSwipeListener(new SwipeLayout.SwipeListener() {
                                                @Override
                                                public void onStartOpen(SwipeLayout layout) {

                                                }

                                                //62319809894
                                                @Override
                                                public void onOpen(SwipeLayout layout) {
//                                                    appid = allAppointmentsResponses.get(i).getApptID();
//                                                    stopChronometer(holder, appid);
//                                                    holder.swipeLayout2.close();
//                                                    holder.swipeLayout.close();
//                                                    Log.d("appid-sublayout", String.valueOf(appid));

                                                }

                                                @Override
                                                public void onStartClose(SwipeLayout layout) {

                                                }

                                                @Override
                                                public void onClose(SwipeLayout layout) {
//
                                                }

                                                @Override
                                                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                                                }

                                                @Override
                                                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                                                }
                                            });


                                        }
                                    }
                                }
                            }, 3000);


                        }


                    } else {
                        allAppointmentsActivity.start = false;
                        allAppointmentsActivity.showAlertDialog("please enable START button");
                        layout.close();
                    }
                }


            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
                if (undo) {
                    undo = true;
                    BaseActivity.getInstance().showToast("undo clicked");
                } else {
                    undo = false;
                    holder.swipeLayout.setSwipeEnabled(true);
                    if (holder.layout_timer.getVisibility() == View.VISIBLE) {
                        stopChronometer(holder, allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId());
                    } else {
                        resetChronometer(holder, allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId());
                        Log.d("appid-close", String.valueOf(allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId()));
                    }

                    BaseActivity.getInstance().showToast("undo not clicked");

                }

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });


//        holder.tvStart.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvConsult.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvUndoText.setTypeface(allAppointmentsActivity.faceLight);

        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
                stopChronometer(holder, appid);
                holder.swipeLayout2.close();
                holder.swipeLayout.close();
                holder.swipeLayout.setSwipeEnabled(true);
//                checkOut(holder, appid);
            }
        });
        holder.markFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointmentsActivity.showToast("MARK FOLLOWUP" + allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId());
            }
        });

        holder.undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo = true;
                holder.swipeLayout.close();

                Collections.swap(allAppointmentsResponses, 0, adapterPostion);
                allAppointmentsActivity.allAppointmentsAdapter.notifyItemMoved(0, adapterPostion);
//                allAppointmentsActivity.allAppointmentsAdapter.notifyDataSetChanged();


                resetChronometer(holder, allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId());
            }
        });
        holder.startConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
//                boolean value = allAppointmentsResponses.get(i).isStartConsultation();
//                if (value == true) {
//                    startConsult = 1;
//                } else {
//                    startConsult = 0;
//                }

                // startConsultation();
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
                cancelAppointment(holder, appid);
            }
        });
        holder.noshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(holder.getAdapterPosition()).getApptId();
                boolean isShow = true;
                noShowAppointment(appid, isShow, holder);
            }
        });

    }

    private void onSwipeOpened(final SwipeLayout layout, final AllAppointmentsHolder holder, final int i) {

    }


    private void checkOut(AllAppointmentsHolder holder, int appid) {

    }

    private void noShowAppointment(int appid, boolean isShow, final AllAppointmentsHolder holder) {
        Call<NoShowAppointmentsResponse> call = BaseActivity.getInstance().serviceCalls.noShowAppointment(appid, isShow);
        call.enqueue(new Callback<NoShowAppointmentsResponse>() {
            @Override
            public void onResponse(Call<NoShowAppointmentsResponse> call, Response<NoShowAppointmentsResponse> response) {
                if (response.code() == 200) {
                    BaseActivity.getInstance().showAlertDialog(response.body().getMsg());
                    holder.swipeLayout.close();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();
                } else {
                    BaseActivity.getInstance().showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<NoShowAppointmentsResponse> call, Throwable t) {
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });
    }

    private void cancelAppointment(final AllAppointmentsHolder holder, int appid) {

        Call<CancelAppointmentResponse> call = BaseActivity.getInstance().serviceCalls.cancelAppointment(appid);
        call.enqueue(new Callback<CancelAppointmentResponse>() {
            @Override
            public void onResponse(Call<CancelAppointmentResponse> call, Response<CancelAppointmentResponse> response) {
                if (response.code() == 200) {
                    BaseActivity.getInstance().showAlertDialog(response.body().getMsg());
                    holder.swipeLayout.close();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();
                } else {
                    BaseActivity.getInstance().showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CancelAppointmentResponse> call, Throwable t) {
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });
    }

    private void stopChronometer(AllAppointmentsHolder holder, int appid) {
        if (holder.running) {
            //allAppointmentsActivity.sessionManager.logout();
//            currentAppID = 0;
            holder.chronometer.stop();
            holder.pauseOffSet = SystemClock.elapsedRealtime() - holder.chronometer.getBase();
            holder.running = false;
            BaseActivity.getInstance().showToast("chronometer-stopped" + "\n" + "consultation closed successfully");
            int elapsedMillis = (int) (SystemClock.elapsedRealtime() - holder.chronometer.getBase());
            String elapsedTime = holder.chronometer.getText().toString();
            Log.d("elapsedMillis1", String.valueOf(elapsedMillis));
            Log.d("elapsedMillis2", holder.chronometer.getText().toString());
            closeConsultation(holder, appid, elapsedMillis, elapsedTime);
        }
    }

    private void resetChronometer(AllAppointmentsHolder holder, int appid) {

        holder.chronometer.setBase(SystemClock.elapsedRealtime());
        holder.pauseOffSet = 0;
        BaseActivity.getInstance().showToast("chronometer-reset" + appid);

    }

    private void startChronometer(AllAppointmentsHolder holder) {
        if (!holder.running) {
            holder.chronometer.setBase(SystemClock.elapsedRealtime() - holder.pauseOffSet);
            holder.chronometer.start();
            holder.running = true;

        }
    }


    private void startConsultation(final AllAppointmentsHolder holder, int appid) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        StartConsultantRequest startConsultantRequest = new StartConsultantRequest(appid);
        Log.d("startConsultantRequest", startConsultantRequest.toString());
        Call<StartConsultantResponse> call = BaseActivity.getInstance().serviceCalls.doStartConsultant(startConsultantRequest);
//        allAppointmentsActivity.showDialog();
        call.enqueue(new Callback<StartConsultantResponse>() {
            @Override
            public void onResponse(Call<StartConsultantResponse> call, Response<StartConsultantResponse> response) {
//                allAppointmentsActivity.closeDialog();
                if (response.code() == 200) {
//                    currentAppID = appid;
//                    Log.d("appid-open", String.valueOf(appid));
                    StartConsultantResponse startConsultantResponse = response.body();
                    //allAppointmentsActivity.showAlertDialog(startConsultantResponse.getMsg());
                    BaseActivity.getInstance().showToast(startConsultantResponse.getMsg());


// slide-up animation
                    Animation undoAnimation = AnimationUtils.loadAnimation(allAppointmentsActivity, R.anim.undo);

                    if (holder.layout_timer.getVisibility() == View.GONE) {
                        holder.layout_timer.setVisibility(View.VISIBLE);
                        holder.layout_undo.setVisibility(View.GONE);
                        holder.layout_timer.startAnimation(undoAnimation);
                    }

                    holder.swipeLayout.setSwipeEnabled(false);

                } else {
                    BaseActivity.getInstance().showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartConsultantResponse> call, Throwable t) {
//                allAppointmentsActivity.closeDialog();
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });
    }

    private void closeConsultation(final AllAppointmentsHolder holder, int appid, int elapsedMillis, final String elapsedTime) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        CloseConsultantRequest closeConsultantRequest = new CloseConsultantRequest(appid);
        Log.d("startConsultantRequest", closeConsultantRequest.toString());
        Call<CloseConsultantResponse> call = BaseActivity.getInstance().serviceCalls.doCloseConsultant(closeConsultantRequest);
        BaseActivity.getInstance().showDialog();
        call.enqueue(new Callback<CloseConsultantResponse>() {
            @Override
            public void onResponse(Call<CloseConsultantResponse> call, Response<CloseConsultantResponse> response) {
                BaseActivity.getInstance().closeDialog();
                if (response.code() == 200) {
                    CloseConsultantResponse closeConsultantResponse = response.body();
                    holder.swipeLayout.close();
                    BaseActivity.getInstance().showToast(closeConsultantResponse.getMsg() + "\n" + "time :" + elapsedTime);
                    allAppointmentsActivity.getAllAppointments();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();

                } else {
                    BaseActivity.getInstance().showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CloseConsultantResponse> call, Throwable t) {
                BaseActivity.getInstance().closeDialog();
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allAppointmentsResponses.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {

        return R.id.swipe;
    }

}
