package com.example.superdoc_ankura.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.ContactAdapter;
import com.example.superdoc_ankura.pojos.response.ListOfDoctorContactsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_with_alphabets, container, false);
        alphabetsList = Arrays.asList(getResources().getStringArray(R.array.alphabets_array));
        listView = view.findViewById(R.id.listView);
        //alphabet = view.findViewById(R.id.alphabet);
        layoutAlphabets = view.findViewById(R.id.layoutAlphabets);
        textPhoneBook = view.findViewById(R.id.textPhoneBook);
        textPhoneBook.setTypeface(BaseActivity.getInstance().faceMedium);
        noDataFound = view.findViewById(R.id.noDataFound);
        noDataFound.setTypeface(BaseActivity.getInstance().faceProximaRegular);
        contactnames = new ArrayList<>();


        Call<ArrayList<ListOfDoctorContactsResponse>> call =
                BaseActivity.getInstance().serviceCalls.getListOfDoctorContacts(BaseActivity.getInstance().sessionManager.getDOCTORID());
        Log.d("listOfContactsResponses", BaseActivity.getInstance().sessionManager.getDOCTORID());
        call.enqueue(new Callback<ArrayList<ListOfDoctorContactsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfDoctorContactsResponse>> call, Response<ArrayList<ListOfDoctorContactsResponse>> response) {
                if (response.code() == 200) {
                    ArrayList<ListOfDoctorContactsResponse> listOfDoctorContactsResponses = response.body();
                    Log.d("listOfContactsResponses", listOfDoctorContactsResponses.toString());
                    if (listOfDoctorContactsResponses.size() == 0) {
                        noDataFound.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    } else {
                        for (ListOfDoctorContactsResponse c : listOfDoctorContactsResponses) {
                            contactnames.add(c.getPatientName());
                        }
                        noDataFound.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        ContactAdapter adapter2 = new ContactAdapter(getActivity(), listOfDoctorContactsResponses);
                        listView.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();
                    }

                } else if (response.code() == 204) {
                    BaseActivity.getInstance().showToast("OPPS Something went wrong....");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListOfDoctorContactsResponse>> call, Throwable t) {
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

        return view;
    }

}
