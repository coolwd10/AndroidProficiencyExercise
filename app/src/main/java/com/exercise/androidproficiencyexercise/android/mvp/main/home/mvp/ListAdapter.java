package com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exercise.androidproficiencyexercise.R;
import com.exercise.androidproficiencyexercise.data.Rows;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Akash on 20-05-2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context mContext;
    private Rows[] mRows;

    public ListAdapter(Context context, Rows[] rows) {
        this.mContext = context;
        mRows =  rows;
    }

    public void refreshView( Rows[] rows)
    {
        if(rows == null || mRows.length==0)
            return;
        this.mRows = rows;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Rows data = mRows[position];
        holder.tittle.setText(data.getTitle());
        if(data.getDescription()!=null) {
            holder.info.setText(data.getDescription());
        } else {
            holder.info.setText("No Discription available");
        }
        //if(data.getImageHref()!=null) {
            Picasso.with(mContext)
                    .load(data.getImageHref())
                    .placeholder(R.drawable.no_image)   // optional
                    .error(R.drawable.no_image)
                    .into(holder.imageView);
//        } else {
//            holder.imageView(mContext.getDrawable(R.drawable.vehicle_placeholder));
//        }
    }

    public void resetSelectedFlightRowIndex() {
        //selectedRowIndex = -1;
    }

    public Rows getListView(int position) {
        return mRows[position];
    }

    @Override
    public int getItemCount() {
        return mRows.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleTextView)
        TextView tittle;

        @BindView(R.id.descriptionTextView)
        TextView info;

        @BindView(R.id.listingImageView)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
