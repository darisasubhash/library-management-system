import com.library.model.Book;
import com.library.model.BookType;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.UserRepository;
import com.library.service.LibraryService;

import java.time.LocalDate;
import java.util.Collection;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome to the library management system!");

        BookRepository bookRepo=new BookRepository();
        IssueRepository issueRepo=new IssueRepository();
        UserRepository userRepo=new UserRepository();
        LibraryService libraryService=new LibraryService(bookRepo,issueRepo,userRepo);
        Book firstBook=new Book(1,"Programming in Java", BookType.TECHNOLOGY);
        Book secondBook=new Book(2,"Maha Bharatham",BookType.HISTORY);
        bookRepo.addBook(firstBook);
        bookRepo.addBook(secondBook);
        User user1=new User(101, "Subhash");
        User user2=new User(102, "Danny");

        userRepo.addUser(user1);
        userRepo.addUser(user2);


        libraryService.issueBook(1, 101, LocalDate.of(2025,1,14));
        libraryService.returnBook(1,LocalDate.of(2025,2,16));
        Collection<Book> books =bookRepo.getAllBooks();
        System.out.println("List of Books present in Library");
        for(Book book:books){
            System.out.println("Book id ="+ book.getId());
            System.out.println("Book Title ="+book.getTitle());
        }






    }
}