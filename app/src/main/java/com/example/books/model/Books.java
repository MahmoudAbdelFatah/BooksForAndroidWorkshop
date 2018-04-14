package com.example.books.model;

/**
 * Created by mahmoudabdelfatahabd on 14-Apr-18.
 */

public class Books {
    private String title;
    private String publishDate;
    private String imageBook;

    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }
}
