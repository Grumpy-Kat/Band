package com.parse.starter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Venkatesh on 5/16/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<RowData> implements CompoundButton.OnCheckedChangeListener {
    private ArrayList<RowData> list, list1;
    int count = 0;
    private Context x;
    int posG;
    SparseBooleanArray mCheckStates;


    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public CustomArrayAdapter(Context context, int textViewResourceId, ArrayList<RowData> rowDataList) {
        //populate the local list with data.
        super(context, textViewResourceId, rowDataList);
        this.x = context;
        this.list = new ArrayList<RowData>();
        this.list.addAll(rowDataList);
        list1 = rowDataList;
        mCheckStates = new SparseBooleanArray(25);

    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        //creating the ViewHolder we defined earlier.
        final ViewHolder holder = new ViewHolder();
        final String[] values = new String[]{"07am-08am Slot", "08am-09am Slot", "09am-10am Slot", "10am-11am Slot", "11am-12pm Slot",
                "12pm-1pm Slot", "1pm-2pm Slot", "2pm-3pm Slot", "3pm-4pm Slot", "4pm-5pm Slot", "5pm-6pm Slot", "6pm-7pm Slot"};
        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater) x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        posG = position;
        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.row, null);

        //setting the views into the ViewHolder.

        holder.title = (TextView) convertView.findViewById(R.id.tvslot);

        holder.label = (TextView) convertView.findViewById(R.id.label);

        holder.chkSelect = (CheckBox) convertView.findViewById(R.id.check);


        //define an onClickListener for the CheckBox.


        //setting data into the the ViewHolder.
        ;

        holder.title.setText(list1.get(position).getSlot());

        if (list1.get(position).getTex() == "Slot Busy") {
            holder.chkSelect.setVisibility(View.GONE);
            holder.label.setTextColor(0xffff0000);
        } else {
            holder.label.setTextColor(0xff00ff00);

        }

//        holder.counter.setGravity(Gravity.CENTER);
        holder.label.setText(list1.get(position).getTex());
        holder.chkSelect.setTag(position);
        holder.chkSelect.setChecked(mCheckStates.get(position, false));
        holder.chkSelect.setOnCheckedChangeListener(this);


        //return the row view.
        return convertView;
    }

    public boolean isChecked(int position) {
        return mCheckStates.get(position, false);
    }

    public void setChecked(int position, boolean isChecked) {
        mCheckStates.put(position, isChecked);

    }

    public void toggle(int position) {
        setChecked(position, !isChecked(position));

    }

    public void setBusy(int position) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCheckStates.put((Integer) compoundButton.getTag(), b);
    }


    static class ViewHolder {

        TextView title;
        TextView label;
        CheckBox chkSelect;

    }
}
