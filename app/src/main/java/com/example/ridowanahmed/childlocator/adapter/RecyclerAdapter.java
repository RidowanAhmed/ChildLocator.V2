package com.example.ridowanahmed.childlocator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ridowanahmed.childlocator.Model.ChildInformation;
import com.example.ridowanahmed.childlocator.R;
import java.util.ArrayList;


/**
 * Created by Ridowan Ahmed on 0017, August, 17, 2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    Context mContext;
    ArrayList<ChildInformation> childList;

    public RecyclerAdapter(Context mContext, ArrayList<ChildInformation> childList){
        this.mContext = mContext;
        this.childList = childList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(mContext).getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cardview_layout, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        
        holder.card_childName.setText(childList.get(position).getChildName());
        holder.card_childTime.setText("Last Updated: " + childList.get(position).getTimeString());
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        
        TextView card_childName, card_childTime;

        public MyHolder(View itemView) {
            super(itemView);
            card_childName = (TextView) itemView.findViewById(R.id.card_childName);
            card_childTime = (TextView) itemView.findViewById(R.id.card_childTime);
        }
    }
}
