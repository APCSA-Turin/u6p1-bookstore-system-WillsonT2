package com.example.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookStore store = new BookStore();
        boolean interfaceOn = true;
        while (interfaceOn) {
            Scanner scan = new Scanner(System.in);
            System.out.println(
                    "********************Welcome to the GFG Library!********************\n                 Select From The Following Options:             \n-------------------------------------------------------------------");
            System.out.println(
                    "Press 0 to exit application.\nPress 1 to Add new Book.\nPress 2 to Upgrade Quantity of a Book.\nPress 3 to Search a Book.\nPress 4 to show All Books.\nPress 5 to show Register Student.\nPress 6 to Show All Registered Students.\nPress 7 to Check out Book.\nPress 8 to Check in Book");
            System.out.println("-------------------------------------------------------------------");
            int response = scan.nextInt();
            scan.nextLine();
            if (response == 0) {
                interfaceOn = false;
            } else if (response == 1) {
                System.out.println("Enter Serial No of Book: ");
                String serialNo = scan.nextLine();
                System.out.println("Enter Book Name: ");
                String bookName = scan.nextLine();
                System.out.println("Enter Author Name: ");
                String authorName = scan.nextLine();
                System.out.println("Enter Quantity of Books: ");
                int bookQuantity = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter Year of Publication: ");
                int yearPublish = scan.nextInt();
                scan.nextLine();
                Book book1 = new Book(bookName, authorName, yearPublish, serialNo, bookQuantity);
                store.addBook(book1);
            } else if (response == 2) {
                System.out.println(store.bookStoreBookInfo());
                // put the input in the format [book number, quantity increase]
                System.out.println("Enter the number of the book and new quantity: ");
                String book = scan.nextLine();
                int bookNumber = Integer.parseInt(book.substring(0, book.indexOf(",")));
                int newQuantity = Integer.parseInt(book.substring(book.indexOf(", ") + 1));
                store.getBooks()[bookNumber].setQuantity(newQuantity);
            } else if (response == 3) {
                // can search by title, author, year published, or ISBN
                // returns no match if it does not exist in store
                System.out.println("What do you want to search by?\n1. Title\n2. Author\n3. Year\n4. ISBN");
                String search = scan.nextLine();
                if (search.equals("ISBN")) {
                    System.out.println(store.findBookISBN(search));
                } else if (search.equals("Title")) {
                    System.out.println(store.findBookTitle(search));
                } else if (search.equals("Author")) {
                    System.out.println(store.findBookAuthor(search));
                } else if (search.equals("Year")) {
                    System.out.println(store.findBookYear(search));
                } else {
                    System.out.println("Invalid Input");
                }
            } else if (response == 4) {
                System.out.println(store.bookStoreBookInfo());
            } else if (response == 5) {
                // format of input [student ID]
                System.out.println("What is the name and ID of the student?");
                String studentInfo = scan.nextLine();
                String studentName = studentInfo.substring(0, studentInfo.indexOf(" "));
                String studentID = studentInfo.substring(studentInfo.indexOf(" "));
                User u1 = new User(studentName, studentID);
                store.addUser(u1);
            } else if (response == 6) {
                System.out.println(store.bookStoreUserInfo());
            } else if (response == 7) {
                System.out.println(store.bookStoreBookInfo() + "\n");
                System.out.println("Enter number of checkout book: ");
                int checkoutBook = scan.nextInt();
                scan.nextLine();
                if (store.getBooks()[checkoutBook].getQuantity() == 0) {
                    System.out.println("All books have been checked out already");
                } else {
                    System.out.println(store.bookStoreUserInfo());
                    System.out.println("Enter checkout user number: ");
                    int checkoutUser = scan.nextInt();
                    scan.nextLine();
                    store.getBooks()[checkoutBook].setQuantity(store.getBooks()[checkoutBook].getQuantity() - 1);
                    store.getUsers()[checkoutUser].addBook(store.getBooks()[checkoutBook]);
                    System.out.println(store.getBooks()[checkoutBook].getTitle() + "has been checked out by user "
                            + store.getUsers()[checkoutUser].getName() + ".");
                }
            } else if (response == 8) {
                System.out.println(store.bookStoreUserInfo());
                System.out.println("What user number is checking in a book?");
                int userNumber = Integer.parseInt(scan.nextLine());
                System.out.println(store.getUsers()[userNumber].bookListInfo());
                System.out.println("What is the number of the book your returning?");
                int bookNumber = scan.nextInt();
                scan.nextLine();
                int newQuantity = store.getUsers()[userNumber].getBooks()[bookNumber].getQuantity() - 1;
                System.out.println(store.getUsers()[userNumber].getBooks()[bookNumber].getTitle()
                        + " has been succesfully returned.");
                store.getUsers()[userNumber].getBooks()[bookNumber].setQuantity(newQuantity);
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}