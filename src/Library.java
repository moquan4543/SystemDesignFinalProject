import java.io.BufferedReader;
import java.util.*;

public class Library {
    public Map<String, User> users = new HashMap<>();
    public Map<Integer,Book> books = new LinkedHashMap<>();
    private static final Library instance = new Library();

    private Library(){}
    public static Library getInstance(){
        return instance;
    }
    /**
     * Analyze a single command,
     * where the supported command format is
     * <p/>
     * [invoker] command [...parameters].
     * <p/>
     * Therefore, the standard processing can be applied uniformly.
     * If the invoker has the permission to execute the command,
     * the command execution is handled by the invoking class.
     *
     * @param   cmd   a single line instruction which the type is String
     * @return  0 if run successfully, -1 if parsing failed.
     **/
    public int processTransaction(String cmd, BufferedReader br){
        List<String> strList = Arrays.stream(cmd.split("\\s+")).toList();
        User invoker = users.get(strList.get(0));
        String instruction = strList.get(1);
        if(invoker.equals(null)){
            System.out.println("User not found");
            return -1;
        }


        //解析指令
        if(instruction.matches("(?i)addBook")){
            try{
                invoker.setCmd(new AddBook(br));
                invoker.invoke();
            }catch(PermissionDeniedException e){
                System.out.println("Borrower can not add book");
                return 0;
            }
        } else if (strList.size() < 3) {
            System.out.println("Should give an argument");
        }
        //除了addBook外的指令都需引數
        //所以可以寫在一個if-statement
        String arg = strList.get(2);
        if(instruction.matches("(?i)removeBook")){

            try{
                invoker.setCmd(new RemoveBook(Integer.parseInt(arg)));
                invoker.invoke();
            }catch(PermissionDeniedException e){
                System.out.println("Borrower can not remove book");
            }

        }else if(instruction.matches("(?i)checkout")){

            try{
                invoker.setCmd(new Checkout(users.get(arg),br));
                invoker.invoke();
            }catch(PermissionDeniedException e){
                System.out.println("Borrower can not check out the books");
            }catch(BookCheckoutException e){
                System.out.println("Can not check out since the book is checked out");
            }catch(ExceedLimitationException e){
                System.out.println("Can not check out since the number of books exceed the limitation of user can check-out");
            }

        }else if(instruction.matches("(?i)return")){

            try{
                invoker.setCmd(new Return(Integer.parseInt(arg)));
                invoker.invoke();
            }catch(PermissionDeniedException e){
                System.out.println("Borrower can not return book");
            }catch(BookCheckoutException e){
                System.out.println("Can not return since the book isn't checked out");
            }

        }else if(instruction.matches("(?i)listAuthor")){
            invoker.setCmd(new ListAuthor(arg));
            invoker.invoke();
        }else if(instruction.matches("(?i)listSubject")){
            invoker.setCmd(new ListSubject(arg));
            invoker.invoke();
        }else if(instruction.matches("(?i)findChecked")){

            try{
                invoker.setCmd(new FindChecked(invoker, users.get(arg)));
                invoker.invoke();
            }catch (PermissionDeniedException e){
                System.out.println("Borrower can not find books checked out by other users");
            }

        }else if(instruction.matches("(?i)findBorrower")){
            invoker.setCmd(new FindBorrower(Integer.parseInt(arg)));
            invoker.invoke();
        }
        return 0;
    }
}
