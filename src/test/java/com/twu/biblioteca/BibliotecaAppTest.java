package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.BufferedReader;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Mock
    private BibliotecaService bibliotecaService;
    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void selectIncorrectMenuOption() throws Exception {
        String invalidOption = BibliotecaApp.selectMenuOptionUserLogged("a");
        assertThat(invalidOption).isEqualTo("Select a valid option!\n");
    }

    @Test
    public void selectMenuOptionToListBooks() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOptionUserLogged("1");
        Mockito.verify(bibliotecaService).getBooksList();
    }

    @Test
    public void selectMenuOptionToQuitApp() throws Exception {
        String quit = BibliotecaApp.selectMenuOptionUserLogged("9");
        assertThat(quit).isEqualTo("Quit");
    }


    @Test
    public void selectMenuOptionCheckOutUserAlreadyCheckOutABook() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Book book = new Book();
        BibliotecaApp.user.setBookCheckedOut(book);
        String result = BibliotecaApp.selectMenuOptionUserLogged("2");
        assertThat(result).isEqualTo("You have already checked out one, return it before check out a new one");
    }

    @Test
    public void selectMenuOptionCheckOutBookEmptyList() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("There are no books available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutBook() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("1", "2", BibliotecaApp.user)).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionCheckOutBookSelectingOneWrongAndCheckingCorrectAfter() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("7", "2", BibliotecaApp.user)).thenReturn("That book is not available");
        Mockito.when(bibliotecaService.operationBook("1", "2", BibliotecaApp.user)).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionListMovie() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOptionUserLogged("4");
        Mockito.verify(bibliotecaService).getMoviesList();
    }

    @Test
    public void selectMenuOptionCheckOutUserAlreadyCheckOutAMovie() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Movie movie = new Movie();
        BibliotecaApp.user.setMovieCheckedOut(movie);
        String result = BibliotecaApp.selectMenuOptionUserLogged("5");
        assertThat(result).isEqualTo("You have already checked out one, return it before check out a new one");
    }

    @Test
    public void selectMenuOptionCheckOutMovieEmptyList() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("There are no movies available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutMovies() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("1", "5", BibliotecaApp.user)).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void selectMenuOptionCheckOutMovieSelectingOneWrongAndCheckingCorrectAfter() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        BibliotecaApp.user = new User();
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("7", "5", BibliotecaApp.user)).thenReturn("That movie is not available");
        Mockito.when(bibliotecaService.operationMovie("1", "5", BibliotecaApp.user)).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void logOutUser() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        String result =  BibliotecaApp.selectMenuOptionUserLogged("8");
        assertThat(result).isEqualTo("Logged out");
    }

    @Test
    public void logInUserSuccessful() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        User user = new User();
        Mockito.when(bufferedReader.readLine()).thenReturn("123-4567").thenReturn("password");
        Mockito.when(bibliotecaService.userLogIn("123-4567","password")).thenReturn(user);
        BibliotecaApp.selectMenuOptionUserNotLogged("3");
        Mockito.verify(bibliotecaService).userLogIn("123-4567", "password");
    }

    @Test
    public void logInUserIncorrectData() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("3");
        String result =  BibliotecaApp.selectMenuOptionUserNotLogged("3");
        assertThat(result).isEqualTo("This is not a valid user");
    }

   @Test
    public void userProfileInformationCorrect() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        User user = new User();
        BibliotecaApp.user = user;
        Mockito.when(bibliotecaService.getUserProfile(user)).thenReturn("ok test text");
        String result = BibliotecaApp.selectMenuOptionUserLogged("7");
        assertThat(result).isNotEmpty();
        BibliotecaApp.user = null;
    }

}
