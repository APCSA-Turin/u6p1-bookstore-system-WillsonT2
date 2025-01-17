package com.example.project;

public class BookStore {

    // requires at least 2 attributes Book[] books, User[] users (initialized to an
    // empty array of 10 max users)
    private Book[] books = new Book[0];
    private User[] users = new User[10];

    // requires 1 empty constructor
    public BookStore() {
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Book[] getBooks() {
        return books;
    }

    public void addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                consolidateUsers();
                return;
            }
        }
    }

    public void removeUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
                users[i] = null;
                consolidateUsers();
                return;
            }
        }
    }


    public void consolidateUsers() {
        int nextEmpty = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (i != nextEmpty) {
                    users[nextEmpty] = users[i];
                    users[i] = null;
                }
                nextEmpty++;
            }
        }
    }

    // adds a new book to the books, increases the length of books if needed
    public void addBook(Book book) {
        int newLength = books.length + 1;
        Book[] newBooks = new Book[newLength];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        newBooks[newLength - 1] = book;
        books = newBooks;
    }

    // places a book at index and increases the size of books length by 1
    public void insertBook(Book book, int index) {
        Book[] newBooks = new Book[books.length + 1];
        for (int i = 0; i < index; i++) {
            newBooks[i] = books[i];
        }
        for (int i = index + 1; i < newBooks.length; i++) {
            newBooks[i] = books[i - 1];
        }
        newBooks[index] = book;
        books = newBooks;
    }

    // remove a book from books, then shortens the length of Books
    public void removeBook(Book book) {
        int index = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                index = i;
            }
        }
        if (books[index].getQuantity() == 1) {
            Book[] newList = new Book[books.length - 1];
            for (int i = 0; i < index; i++) {
                newList[i] = books[i];
            }
            for (int i = index + 1; i < books.length; i++) {
                newList[i - 1] = books[i];
            }
            books = newList;
        } else {
            books[index].setQuantity(books[index].getQuantity() - 1);
        }
    }

    // returns a String , using bookInfo() to return all of the info for each book
    public String bookStoreBookInfo() {
        String result = "";
        for (int i = 0; i < books.length; i++) {
            result += i + ". " + books[i].bookInfo() + "\n";
        }
        return result;
    } // you are not tested on this method but use it for debugging purposes

    // returns a String, using userInfo() to return all of the info for each user
    public String bookStoreUserInfo() {
        String result = "";
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                result += users[i].userInfo() + "\n";
            } else {
                result += "User not found" + "\n\n";
            }
        }
        return result;
    } // you are not tested on this method but use it for debugging purposes

    // returns the info of the book with targeted ISBN 
    // precondition that there is no duplicate ISBN
    public String findBookISBN(String targetIsbn) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getIsbn().equals(targetIsbn)) {
                books[i].bookInfo();
            }
        }
        return "No match";
    }

    // returns a string with all the books info that match the target author
    public String findBookAuthor(String author) {
        String result = "";
        for (int i = 0; i < books.length; i++) {
            if (author.equals(books[i].getAuthor())) {
                result += books[i].bookInfo() + "\n";
            }
        }
        if (!result.equals("")) {
            return result;
        } else {
            return "No match";
        }
    }

    // returns string with all books that match the target year
    public String findBookYear(String yearPublished) {
        String result = "";
        for (int i = 0; i < books.length; i++) {
            if (Integer.parseInt(yearPublished) == books[i].getYearPublished()) {
                result += books[i].bookInfo() + "\n";
            }
        }
        if (!result.equals("")) {
            return result;
        } else {
            return "No match";
        }
    }

    // return string with all titles that match target title
    public String findBookTitle(String title) {
        String result = "";
        for (int i = 0; i < books.length; i++) {
            if (title.equals(books[i].getTitle())) {
                result += books[i].bookInfo() + "\n";
            }
        }
        if (!result.equals("")) {
            return result;
        } else {
            return "No match";
        }
    }

}