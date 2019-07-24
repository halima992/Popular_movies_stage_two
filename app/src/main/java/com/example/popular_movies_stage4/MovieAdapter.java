package com.example.popular_movies_stage4;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;
import android.view.LayoutInflater;
import com.example.popular_movies_stage4.module.MovieModule;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Todo (1) Adding a class called movieAdapter
// TODO (2) Extend RecyclerView.Adapter<movieAdapter.movieAdapterViewHolder>
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    // Todo (3) Creating a private MovieModule Arraylist called movieData
    private List<MovieModule> movieData = new ArrayList<>();
    final private MovieAdapterOnClickHandler Listener ;
    Context context;
    public MovieAdapter(Context context, List<MovieModule> moviesInfo, MovieAdapterOnClickHandler listener) {
        this.context = context;
        this.movieData = moviesInfo;
        Listener = listener;
    }
    // TODO (52) Creating a final private MovieAdapterOnClickHandler called Listener
    //Todo (4) create construct movieAdapter
    MovieAdapter(MovieAdapterOnClickHandler listener){
        Listener = listener;
    }

    // TODO (50) Adding an interface called MovieAdapterOnClickHandler
    // TODO (51) Within that interface, define a void method called onMoviePosterClick that takes an int as a parameter
    public interface MovieAdapterOnClickHandler {
        void onMoviePosterClick(int list);
    }
    // todo (31) Create a setMoviesData method that saves the movieInfo to movieData
    public void setMoviesData(List <MovieModule>movieInfo) {
        movieData.addAll(movieInfo) ;
        Log.d("movieAdapter","the response 55 is : "+ movieData.get(0).getTitle());
        notifyDataSetChanged();
    }
    public void clearMovieData() {
        movieData.clear() ;
        notifyDataSetChanged();
    }
    // Todo (5) Create a class within movieAdapter called movieAdapterViewHolder
    // Todo (6) Extend RecyclerView.ViewHolder
    /**
     * Cache of the children views for a movies list item.
     */
    // Todo (7) Override onCreateViewHolder
    // Todo (8) Within onCreateViewHolder, inflate the list item xml into a view
    // Todo (9) Within onCreateViewHolder, return a new MovieAdapterViewHolder with the above view passed in as a parameter

    @Override
    public MovieAdapter.MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
        return viewHolder;
    }
    @Override
    // todo (10) Override onBindViewHolder
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int position ) {
        String movie = movieData.get(position).getPoster();

        Picasso.get()
                .load(movie)
                .into(movieAdapterViewHolder.posterMovieImageView);

    }

    // todo (12) Override getItemCount
    // todo (13) Return 0 if movieData is null, or the size of movieData if it is not null
    @Override
    public int getItemCount() {
        if (null == movieData) return 0;
        return movieData.size();    }
    // TODO (53) Implement Listener in the NumberViewHolder class
    class MovieAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        public final ImageView posterMovieImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            posterMovieImageView = (ImageView) view.findViewById(R.id.iv_item_number);
            // TODO (55) Call setOnClickListener on the View passed into the constructor
            // (use 'this' as the OnClickListener)
            view.setOnClickListener(this);
        }
        // TODO (54) Override onClick, passing the clicked item's position

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            Listener.onMoviePosterClick(clickPosition);

        }
    }




}
