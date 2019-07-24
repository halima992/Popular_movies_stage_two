package com.example.popular_movies_stage4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.popular_movies_stage4.module.ReviewModule;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter  extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private List<ReviewModule> ReviewContent = new ArrayList<>();

    public ReviewsAdapter(){
    }
    public ReviewsAdapter(List <ReviewModule>reviewInfo){
        ReviewContent.addAll(reviewInfo);
    }
    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_review_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup,shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
//Todo back
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String Review=ReviewContent.get(position).getTheContent();
        String Author=ReviewContent.get(position).getTheAuthor();
        Log.d("ReviewAdapter","the response miliion is : "+
                ReviewContent.get(position).getTheAuthor());

        viewHolder.mReview.setText(Review);
        viewHolder.mAuthor.setText(Author );

    }

    @Override
    public int getItemCount() {
        if (null == ReviewContent) return 0;
        Log.d("ReviewAdapter","the response miliion is : "+
                ReviewContent.size());
        return ReviewContent.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mReview ;
        public final TextView mAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
               mReview=(TextView)itemView.findViewById(R.id.tv_review);
               mAuthor=(TextView)itemView.findViewById(R.id.tv_author);
            //
        }
    }
    public void setmReview(List <ReviewModule>reviewInfo) {
        ReviewContent.addAll(reviewInfo) ;
        Log.d("ReviewAdapter","the response 55 is : "+ ReviewContent.get(0).getTheAuthor());
        notifyDataSetChanged();
    }
    public void clearmReview() {
        ReviewContent.clear() ;
        notifyDataSetChanged();
    }
}
