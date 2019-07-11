package com.example.superdoc_ankura.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.adapters.ContactAdapter;
import com.example.superdoc_ankura.pojos.response.ListOfDoctorContactsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends android.support.v4.app.Fragment {
    //    LinearLayout layoutSingleContact;
    ListView listView;
    ArrayList<String> contactnames;
    List<String> alphabetsList = new ArrayList<>();
    LinearLayout layoutAlphabets;
    TextView alphabet;
    TextView textPhoneBook;
    TextView noDataFound;
    ImageView searchIcon;
    CardView cardView;
    SearchView searchView;
    public ContactAdapter adapter2;
    ArrayList<ListOfDoctorContactsResponse> listOfDoctorContactsResponses;
     ShimmerFrameLayout mShimmerViewContainer;
    LinearLayout layoutSearchIcon,layoutSearchView;
    TextView cancelSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);
        alphabetsList = Arrays.asList(getResources().getStringArray(R.array.alphabets_array));
        listView = view.findViewById(R.id.listView);
        layoutSearchIcon = view.findViewById(R.id.layoutSearchIcon);
        layoutSearchView = view.findViewById(R.id.layoutSearchView);
        cancelSearch = view.findViewById(R.id.cancelSearch);
        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer);

        searchIcon = view.findViewById(R.id.ivSearchIcon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSearchIcon.setVisibility(View.GONE);
                layoutSearchView.setVisibility(View.VISIBLE);
            }
        });
        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSearchIcon.setVisibility(View.VISIBLE);
                layoutSearchView.setVisibility(View.GONE);
            }
        });
        cardView = view.findViewById(R.id.cardView);
        searchView = view.findViewById(R.id.searchView);



        //alphabet = view.findViewById(R.id.alphabet);
        layoutAlphabets = view.findViewById(R.id.layoutAlphabets);
        textPhoneBook = view.findViewById(R.id.textPhoneBook);
        textPhoneBook.setTypeface(BaseActivity.getInstance().faceMedium);
        noDataFound = view.findViewById(R.id.noDataFound);
        noDataFound.setTypeface(BaseActivity.getInstance().faceProximaRegular);
        contactnames = new ArrayList<>();

        mShimmerViewContainer.startShimmerAnimation();
        Call<ArrayList<ListOfDoctorContactsResponse>> call =
                BaseActivity.getInstance().serviceCalls.getListOfDoctorContacts(BaseActivity.getInstance().sessionManager.getDOCTORID());
        call.enqueue(new Callback<ArrayList<ListOfDoctorContactsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfDoctorContactsResponse>> call, Response<ArrayList<ListOfDoctorContactsResponse>> response) {

                if (response.code() == 200) {
                     listOfDoctorContactsResponses = response.body();
                    Log.d("listOfContactsResponses", listOfDoctorContactsResponses.toString());
                    if (listOfDoctorContactsResponses.size() == 0) {
                        noDataFound.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mShimmerViewContainer.stopShimmerAnimation();
                            }
                        }, 500);
                        for (ListOfDoctorContactsResponse c : listOfDoctorContactsResponses) {
                            contactnames.add(c.getPatientName());
                        }
                        noDataFound.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        adapter2 = new ContactAdapter(getActivity(), listOfDoctorContactsResponses);
                        listView.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();

                    }

                } else if (response.code() == 204) {
                    BaseActivity.getInstance().showToast("OPPS Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListOfDoctorContactsResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });


        final TextView[] myTextViews = new TextView[alphabetsList.size()];

        for (int i = 0; i < alphabetsList.size(); i++) {
            // create a new textview
            alphabet = new TextView(getActivity());
            // set some properties of rowTextView or something
            alphabet.setText(alphabetsList.get(i));
//            alphabet.setTextAppearance(getActivity(), R.style.alphabetTextView);
            alphabet.setTag(alphabetsList.get(i));
            alphabet.setTypeface(BaseActivity.getInstance().faceBold);
            alphabet.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f);
            // add the textview to the linearlayout
            layoutAlphabets.addView(alphabet);

            // save a reference to the textview for later
            myTextViews[i] = alphabet;
            myTextViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String firstLetter = (String) v.getTag();
                        Log.d("fletter", firstLetter);
                        int index = 0;
                        if (contactnames.size() != 0) {
                            for (String string : contactnames) {
                                if (string.startsWith(firstLetter)) {
                                    index = contactnames.indexOf(string);
                                    break;
                                }
                            }
                        }
//                        else {
//                            Toast.makeText(getActivity(), "there is no contact starts with this alphabet", Toast.LENGTH_LONG).show();
//                        }
                        listView.setSelectionFromTop(index, 0);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (listOfDoctorContactsResponses.toString().contains(query)) {
                    adapter2.getFilter().filter(query);
                } else {
                    Toast.makeText(getActivity(), "No Match Found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    adapter2.getFilter().filter(newText);
                    //rview.getTextFilter();
                    listView.getFilterTouchesWhenObscured();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }
        });
        return view;
    }

}
