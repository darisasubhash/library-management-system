import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateException;
import com.library.exception.NoActiveIssueException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.BookType;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.UserRepository;
import com.library.service.LibraryService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepo=new BookRepository();
        IssueRepository issueRepo=new IssueRepository();
        UserRepository userRepo=new UserRepository();
        LibraryService libraryService=new LibraryService(bookRepo,issueRepo,userRepo);
        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.println("Hello and welcome to the library management system!");
            System.out.println("Enter 1 to Add User");
            System.out.println("Enter 2 to Add Book");
            System.out.println("Enter 3 to Issue Book");
            System.out.println("Enter 4 to Return Book");
            System.out.println("Enter 5 to Exit");
            System.out.print("Enter your choice: ");
            int number=scanner.nextInt();
            switch (number){
                case 1:
                    System.out.println("Enter user id");
                    int userId=scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the username");
                    String userName=scanner.nextLine();
                    try{
                        userRepo.addUser(new User(userId,userName));
                        System.out.println("User added Successfully");
                    } catch (DuplicateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter Book id");
                    int bookId=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Book Title");
                    String bookTitle=scanner.nextLine();
                    BookType bookType = null;
                    while (bookType == null) {
                        System.out.println("Enter Book type ( FICTION / NON_FICTION / SCIENCE / HISTORY / TECHNOLOGY / MATHEMATICS / COMEDY / OTHER )");
                        try {
                            bookType = BookType.fromString(scanner.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid book type reenter book type again.");
                        }
                    }
                    try{
                        bookRepo.addBook(new Book(bookId,bookTitle,bookType));
                        System.out.println("Book added Successfully");
                    } catch (DuplicateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter use id ");
                    int issueUserId=scanner.nextInt();
                    System.out.println("Enter Book id");
                    int issueBookId=scanner.nextInt();
                    try{
                        libraryService.issueBook(issueUserId,issueBookId);
                    }
                    catch (BookNotFoundException | UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    System.out.println("Enter User id");
                    int returnUserId=scanner.nextInt();
                    try{
                        libraryService.returnBook(returnUserId);
                    } catch (UserNotFoundException | NoActiveIssueException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting Library Management System");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}