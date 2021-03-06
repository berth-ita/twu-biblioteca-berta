package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void getBookListOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        assertThat(bookList).hasSize(5);
    }

    @Test
    public void deleteBookFromListListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        bookRepository.deleteBookFromList("0012");
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void deleteBookFromListOkTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteBookFromList("0012");
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void deleteBookFromListNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteBookFromList("25");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void returnBookListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> returnBookList = bookRepository.getReturnBookList();
        bookRepository.deleteBookFromList("0012");
        assertThat(returnBookList).hasSize(1);
    }

    @Test
    public void returnBookOkTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteBookFromList("0012");
        Boolean bookRemoved = bookRepository.returnBookFromList("0012");
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void returnBookNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.returnBookFromList("7");
        assertThat(bookRemoved).isFalse();
    }

}