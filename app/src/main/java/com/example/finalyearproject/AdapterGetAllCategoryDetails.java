package com.example.finalyearproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterGetAllCategoryDetails extends BaseAdapter{
    //baseAadapter = mulitple view load show
    // AdapterGetAllCategoryDetails show multiple view collect show on listview

    List<POJOGetAllCategoryDetails> pojoGetAllCategoryDetails;

    public AdapterGetAllCategoryDetails(List<POJOGetAllCategoryDetails> list, Activity activity) {
        this.pojoGetAllCategoryDetails = list;
        this.activity = activity;
    }

    Activity activity;


    @Override
    public int getCount() {
        return pojoGetAllCategoryDetails.size();
    }

    @Override
    public Object getItem(int position)
    {
        return pojoGetAllCategoryDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View  view, ViewGroup viewGroup) {

        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view == null)
        {
            holder = new ViewHolder();
          view =  inflater.inflate(R.layout.lv_get_all_category,null);
          holder.ivCategoryImage = view.findViewById(R.id.ivcategoryimage);
          holder.tvCategoryName = view.findViewById(R.id.tvCategoryName);

          view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        final POJOGetAllCategoryDetails obj = pojoGetAllCategoryDetails.get(position);
        holder.tvCategoryName.setText(obj.getCategoryName());

        Glide.with(activity)
                .load( "http://192.168.34.114:80/hostelmanagementAPI/images/"+obj.getCategoryImage())
                .skipMemoryCache(true)
                .error(R.drawable.image_not_available)
                .into(holder.ivCategoryImage);
        return view;
    }

    class ViewHolder
    {
        ImageView ivCategoryImage;
        TextView tvCategoryName;
    }

}
