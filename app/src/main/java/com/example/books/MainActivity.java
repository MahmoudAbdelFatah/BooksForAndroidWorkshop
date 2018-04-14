package com.example.books;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.books.adapter.BooksAdapter;
import com.example.books.model.Books;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;
    public static List<Books> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: 7
        booksList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_books);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        booksAdapter = new BooksAdapter(this, booksList);
        recyclerView.setAdapter(booksAdapter);
        downloadFromInternet();
    }

    private void downloadFromInternet() {
        //https://www.googleapis.com/books/v1/volumes?q=time&printType=books&AIzaSyBd97lbpG6qb_j1ERREEBOom_Q3IFUMi8k
        String URL = getString(R.string.BASE_URL) + "&" + getString(R.string.PRINT_TYPE) + "&" +
                getString(R.string.KEY);
        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("items");
                            booksList.clear();
                            String temp = "";
                            Books book;
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                book = new Books();
                                // Get current json object
                                JSONObject item = array.getJSONObject(i);
                                JSONObject  volumeInfo = (JSONObject) item.get("volumeInfo");
                                book.setTitle((String)volumeInfo.get("title"));
                                Log.i("title", book.getTitle());
                                temp = volumeInfo.get("publishedDate").toString();
                                book.setPublishDate(temp);
                                JSONObject  imageLinks = (JSONObject) volumeInfo.get("imageLinks");
                                String  smallThumbnail = (String) imageLinks.get("smallThumbnail");
                                book.setImageBook(imageLinks.get("smallThumbnail").toString()
                                        .substring(0, smallThumbnail.length() - 1));


                                booksList.add(book);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        booksAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.i("error", "there is error here");
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);

    }
}
