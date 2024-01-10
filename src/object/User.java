package object;
import command.Command;

@SuppressWarnings("all")
public class User {
    private final String userName;
    private final Permission userType;
    private Integer predefinedBorrowBookNumber;
    Command cmd;

    public User(Permission userType, String userName){
        this.userType = userType;
        this.userName = userName;
        this.predefinedBorrowBookNumber = Integer.MAX_VALUE;
    }

    public User(Permission userType, String userName, Integer predefinedBorrowBookNumber){
        this.userType = userType;
        this.userName = userName;
        this.predefinedBorrowBookNumber = predefinedBorrowBookNumber;
    }

    public String getUserName() {
        return userName;
    }

    public Permission getUserType(){
        return userType;
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

    public void invoke(String arg){
        try{
            cmd.execute(this,arg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
