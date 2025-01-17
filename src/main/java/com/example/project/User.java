package com.example.project;

public class User {
    // requires 3 private attributes String name, String Id, Book book that is
    // initialized to empty
    private String name;
    private String Id;
    private Book[] bookList = new Book[5];

    // requires 1 contructor with two parameters that will initialize the name and
    // id
    public User(String name, String Id) {
        this.name = name;
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Book[] getBooks() {
        return bookList;
    }
    
    // replaces current bookList for user with new bookList
    public void setBooks(Book[] bookList) {
        this.bookList = bookList;
    }

    // puts books at front of bookList and null at end
    public void consolidateBooks() {
        int nextEmpty = 0;
        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] != null) {
                if (i != nextEmpty) {
                    bookList[nextEmpty] = bookList[i];
                    bookList[i] = null;
                }
                nextEmpty++;
            }
        }
    }

    // adds a book to the user and tells how many more books they can borrow, prints
    // max books borrowed if no more can be borrowed
    public void addBook(Book newBook) {
        if (!(bookList[4] == null)) {
            System.out.println("MAX BOOKS BORROWED");
            return;
        }
        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] == null) {
                bookList[i] = newBook;
                consolidateBooks();
                return;
            }
        }
    }

    // returns the information of each book in booklist 
    public String bookListInfo() {
        String result = "";
        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] == null) {
                result += "empty\n";
            } else {
                result += bookList[i].bookInfo() + "\n";
            }
        }
        return result;
    }

    public String userInfo() {
        return "Name: " + name + "\nId: " + Id + "\nBooks: \n" + bookListInfo();
    }
}