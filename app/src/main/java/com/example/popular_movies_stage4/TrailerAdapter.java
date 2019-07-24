package com.example.popular_movies_stage4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.popular_movies_stage4.module.trailerModule;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter <TrailerAdapter.videoViewHolder>{
    private List<trailerModule> video = new ArrayList<>();
    Context context;
    public TrailerAdapter(){
    }
    public TrailerAdapter(List <trailerModule>TrailerInfo,Context context){
        video.addAll(TrailerInfo);
        this.context=context;
    }
    @NonNull
    @Override
    public TrailerAdapter.videoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.d("trailerAdapter","onCreateViewHolder : ");

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_trailer_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup,shouldAttachToParentImmediately);
        videoViewHolder viewHolder = new videoViewHolder(view);
        return viewHolder;
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.videoViewHolder videoHolder, int position) {
        final String nameTrailer=video.get(position).getName();
        final String keyTrailer=video.get(position).getKey();
        videoHolder.mTrailerList.setText(nameTrailer);
        Log.d("trailerAdapter","the response nameTrailer is : ");

        videoHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage=Uri.parse("https://www.youtube.com/watch?v="+keyTrailer);
                Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        Log.d("trailerAdapter","item video: "+video.size());

        if (null == video) return 0;
     else{ return video.size();
        }
    }
    public class videoViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTrailerList;
        public videoViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrailerList = (TextView) itemView.findViewById(R.id.tv_trailer_name);
        }
    }
       public void setmTrailer(List <trailerModule>TrailerInfo,Context context) {
        video.addAll(TrailerInfo) ;
        this.context=context;
        //Log.d("ReviewAdapter","the response 55 is : "+ video.get(0).);
        notifyDataSetChanged();
    }

}
