package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.starter.RowData;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Venkatesh on 5/16/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<RowData>
{
    private ArrayList<RowData> list,list1;int count=0;
    private  Context x;int posG;
    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public CustomArrayAdapter(Context context, int textViewResourceId, ArrayList<RowData> rowDataList)
    {
        //populate the local list with data.
        super(context,textViewResourceId, rowDataList);
        this.x=context;
        this.list = new ArrayList<RowData>();
        this.list.addAll(rowDataList);
        list1=rowDataList;
    }



    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        final ViewHolder holder = new ViewHolder();
        final String[] values = new String[] { "07am-08am Slot","08am-09am Slot","09am-10am Slot","10am-11am Slot","11am-12pm Slot",
                "12pm-1pm Slot","1pm-2pm Slot","2pm-3pm Slot","3pm-4pm Slot","4pm-5pm Slot","5pm-6pm Slot","6pm-7pm Slot"};
        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater)x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        posG=position;
        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.row, null);

        //setting the views into the ViewHolder.

        holder.title = (TextView) convertView.findViewById(R.id.tvslot);

        holder.counter = (TextView) convertView.findViewById(R.id.label);




        //define an onClickListener for the CheckBox.


        //setting data into the the ViewHolder.
        ;

        holder.title.setText(list1.get(position).getSlot());

        holder.counter.setText(list1.get(position).getTex());


        //return the row view.
        return convertView;
    }


    static class ViewHolder
    {

        TextView title;
        TextView counter;

    }
}
