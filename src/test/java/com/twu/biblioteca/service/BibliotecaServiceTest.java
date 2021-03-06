package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        String mainMenuMessage = bibliotecaService.getMainMenuMessage();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - Check out book\n3 - Return book\n4 - Quit\n");
    }

    @Test
    public void selectMenuOptionListBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bibliotecaService.getBooksList();
        assertThat(bookList).isEqualTo(String.format("%-45s %-20s %-4s\n", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-45s %-20s %-4s\n","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bibliotecaService.printBooksList("2");
        assertThat(bookList).isEqualTo(String.format("%-4s %-45s %-20s %-4s\n", "0001", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-4s %-45s %-20s %-4s\n","0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void checkOutBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        Mockito.when(bookRepository.deleteBookFromList("2")).thenReturn(true);
        String bookRemoved = bibliotecaService.operationBook("2", "2");
        Mockito.verify(bookRepository).deleteBookFromList("2");
        assertThat(bookRemoved).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void checkOutBookNotOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        Mockito.when(bookRepository.deleteBookFromList("25")).thenReturn(false);
        String bookRemoved = bibliotecaService.operationBook("25", "2");
        Mockito.verify(bookRepository).deleteBookFromList("25");
        assertThat(bookRemoved).isEqualTo("That book is not available");
    }

    @Test
    public void selectMenuOptionReturnBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getReturnBookList()).thenReturn(bookListMocked);
        String bookList = bibliotecaService.printBooksList("3");
        assertThat(bookList).isEqualTo(String.format("%-4s %-45s %-20s %-4s\n", "0001", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-4s %-45s %-20s %-4s\n","0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void returnBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        Mockito.when(bookRepository.returnBookFromList("1")).thenReturn(true);
        String bookReturned = bibliotecaService.operationBook("1", "3");
        Mockito.verify(bookRepository).returnBookFromList("1");
        assertThat(bookReturned).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void returnBookNotOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        Mockito.when(bookRepository.returnBookFromList("15")).thenReturn(false);
        String bookReturned = bibliotecaService.operationBook("15", "3");
        Mockito.verify(bookRepository).returnBookFromList("15");
        assertThat(bookReturned).isEqualTo("This is not a valid book to return");
    }

    private List<Book> getBooksList() {
        List<Book> bookListMocked = new ArrayList<>();
        bookListMocked.add(getBook("0001","The Hobbit", "J.R.R Tolkien", 1937));
        bookListMocked.add(getBook("0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        return bookListMocked;
    }

    private Book getBook(String id, String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }
}