package com.example.finalyearproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class CategoryFragment extends Fragment {

    ListView lvShowAllCategory;
    TextView tvNoCategoryAvailable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        lvShowAllCategory = view.findViewById(R.id.lvCategoryFragmentShowMultipleCategory);
        tvNoCategoryAvailable = view.findViewById(R.id.tvCategoryFragmentNoCategoryAvailable);

        getAllCategory();

        return view;
    }

    private void getAllCategory() {
        AsyncHttpClient client = new AsyncHttpClient(); // Client - Server communication Over the network
        RequestParams params = new RequestParams(); // Put the data in asynchttpclient

        client.post("http://192.168.34.114:80/hostelmanagementAPI/getAllCategoryDetails.php",
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(getActivity(),"Server Error",Toast.LENGTH_SHORT).show();
                    }
                }



        );
    }
}