/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.services;

import java.util.ArrayList;

import com.mycompany.demo.entities.Book;

import org.springframework.stereotype.Service;


@Service
public class DashboardService {
    ArrayList<Book> books = new ArrayList<>();

    public DashboardService() {
        books.add(new Book("tytuł1", "autor1", 1991));
        books.add(new Book("tytuł2", "autor2", 1992));
        books.add(new Book("tytuł3", "autor3", 1993));
        books.add(new Book("tytuł4", "autor4", 1994));
        books.add(new Book("tytuł5", "autor5", 1995));
        books.add(new Book("tytuł6", "autor6", 1996));
        books.add(new Book("tytuł7", "autor7", 1997));
        books.add(new Book("tytuł8", "autor8", 1998));
    }

    public Book getBook(int id) {
        return books.get(id);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book createBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        books.add(book);
        return book;
    }

    public Book updateBook(int id, String title, String author, int year) {
        Book book = new Book(title, author, year);
        books.set(id, book);
        return book;
    }

    public Book removeBook(int id) {
        Book book = books.get(id);
        books.remove(id);
        return book;
    }

}
