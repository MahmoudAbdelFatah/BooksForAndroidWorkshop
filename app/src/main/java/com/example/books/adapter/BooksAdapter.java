package com.example.books.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.books.R;
import com.example.books.model.Books;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mahmoudabdelfatahabd on 14-Apr-18.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    private Context context;
    private List<Books> booksList;
    public BooksAdapter(Context context, List<Books> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.book_item, parent, false);

        // Return a new holder instance
        BooksViewHolder viewHolder = new BooksViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {
        // Get the data model based on position
        final Books book = booksList.get(position);

        //Download image using picasso library
        Picasso.with(context).load(book.getImageBook())
                .into(holder.bookImage);
        holder.bookName.setText(book.getTitle());
        holder.bookYear.setText(book.getPublishDate());
    }


    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {
        public ImageView bookImage;
        public TextView bookName;
        public TextView bookYear;
        public CardView cardView;
        public BooksViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.iv_book);
            bookName = itemView.findViewById(R.id.tv_book_title);
            bookYear = itemView.findViewById(R.id.tv_publisher_date);
            cardView = itemView.findViewById(R.id.cv_book);
        }
    }
}
