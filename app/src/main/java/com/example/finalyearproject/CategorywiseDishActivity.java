package com.example.finalyearproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyearproject.Comman.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CategorywiseDishActivity extends AppCompatActivity {

    SearchView searchCategorywiseDish;
    ListView lvCategorywiseDish;
    TextView tvNoDishAvailable;
    String strCategoryname ;

    List<POJOCategorywiseDish> pojoCategorywiseDishList;
    AdapterCategorywiseDish adapterCategorywiseDish;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorywise_dish);

        searchCategorywiseDish = findViewById(R.id.svCategorywiseDishsearchDish);
        lvCategorywiseDish = findViewById(R.id.lvCategorywiseDishlistofdish);
        tvNoDishAvailable = findViewById(R.id.tvCategorywiseDishNoDishAvailable);

        pojoCategorywiseDishList = new ArrayList<>();

        strCategoryname = getIntent().getStringExtra("categoryname");

        getCategorywiseDishList();

    }

    private void getCategorywiseDishList() {
        AsyncHttpClient client = new AsyncHttpClient(); // Client Server Communication over network
        RequestParams params = new RequestParams(); //Put the data

        params.put("categoryname",strCategoryname);

        client.post(Urls.categoryWiseDishesWebService,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("getCategorywiseDish");
                            if(jsonArray.isNull(0))
                            {
                                lvCategorywiseDish.setVisibility(View.GONE);
                                tvNoDishAvailable.setVisibility(View.VISIBLE);
                            }
                            for(int i=0 ; i<jsonArray.length() ; i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String strid = jsonObject.getString("id");
                                String strcategoryname = jsonObject.getString("categoryname");
                                String strestaurantname = jsonObject.getString("restaurantname");
                                String strdishcategory = jsonObject.getString("dishcategory");
                                String strdishimage = jsonObject.getString("dishimage");
                                String strdishname = jsonObject.getString("dishname");
                                String strdishprice = jsonObject.getString("dishprice");
                                String strdishrating = jsonObject.getString("dishrating");
                                String strdishoffer = jsonObject.getString("dishoffer");
                                String strdishdescription = jsonObject.getString("dishdescription");

                                pojoCategorywiseDishList.add(new POJOCategorywiseDish(strid,strcategoryname,strestaurantname,strdishcategory,
                                        strdishimage,strdishname, strdishprice,strdishrating,strdishoffer,strdishdescription));
                            }
                            adapterCategorywiseDish = new AdapterCategorywiseDish(pojoCategorywiseDishList,CategorywiseDishActivity.this);

                            lvCategorywiseDish.setAdapter(adapterCategorywiseDish);



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(CategorywiseDishActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }



        );


    }
}