package object;

import command.Command;
import exception.BookCheckoutException;
import exception.PermissionDeniedException;

public class User {
    String userName;
    priority userType;
    Integer predefinedBorrowBookNumber;
    Command cmd;

    public User(priority userType, String userName){
        this.userType = userType;
        this.userName = userName;
        this.predefinedBorrowBookNumber = Integer.MAX_VALUE;
    }
    public User(priority userType, String userName, Integer predefinedBorrowBookNumber){
        this.userType = userType;
        this.userName = userName;
        this.predefinedBorrowBookNumber = predefinedBorrowBookNumber;
    }
    public String getUserName() {
        return userName;
    }
    public Integer getPredefinedBorrowBookNumber() {
        return predefinedBorrowBookNumber;
    }
    public void setPredefinedBorrowBookNumber(Integer predefinedBorrowBookNumber) {
        this.predefinedBorrowBookNumber = predefinedBorrowBookNumber;
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }
    public void invoke() throws BookCheckoutException, PermissionDeniedException {
        if(cmd.canExecute(this.userType)){
            cmd.execute();
        }else{
            throw new PermissionDeniedException();
        }
    }
}
