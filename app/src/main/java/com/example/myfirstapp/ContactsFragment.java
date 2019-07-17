/**package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContactsFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener  {
      //
     // Defines an array that contains column names to move from
     //the Cursor to the ListView.

    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };

     // Defines an array that contains resource ids for the layout views
    // that get the Cursor column contents. The id is pre-defined in
    //  the Android framework, so it is prefaced with "android.R.id"

    private final static int[] TO_IDS = {
            android.R.id.text1
    };
    // Define global mutable variables
    // Define a ListView object
    ListView contactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long contactId;
    // The contact's LOOKUP_KEY
    String contactKey;
    // A content URI for the selected contact
    Uri contactUri;
    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter cursorAdapter;

    // Empty public constructor, required by the system
    public ContactsFragment() {}

    // A UI Fragment must inflate its View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contact_list_fragment,
                container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Gets the ListView from the View list of the parent activity
        contactsList =
                (ListView) getActivity().findViewById(R.layout.contact_list_view);
        // Gets a CursorAdapter
        cursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.contact_list_item,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
        contactsList.setAdapter(cursorAdapter);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // Set the item click listener to be the current fragment.
        contactsList.setOnItemClickListener(this);
    }
}
**/