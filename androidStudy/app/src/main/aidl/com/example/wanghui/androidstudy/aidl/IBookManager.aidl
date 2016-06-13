// IBookManager.aidl
package com.example.wanghui.androidstudy.aidl;

import com.example.wanghui.androidstudy.aidl.Book;

// Declare any non-default types here with import statements

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
