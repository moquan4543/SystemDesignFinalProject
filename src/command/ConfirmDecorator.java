package command;
import controller.Library;
import exception.PermissionDeniedException;
import object.User;
import object.priority;

public class ConfirmDecorator implements Command{
    private final Command cmd;
    private String msg = "";
    private final Library lib = Library.getInstance();

    public ConfirmDecorator(Command cmd){
        this.cmd = cmd;
    }
    private boolean isStaff(User user){
        return user.getUserType().equals(priority.Staff);
    }
    private boolean hasConfirm(User invoker,String arg){
        String cmdString = cmd.getClass().getName().replace("command.","");
        if(lib.commandErrorList.containsKey(cmdString+"Permission")){
            msg = lib.commandErrorList.get(cmdString+"Permission");
            return isStaff(invoker);
        }else if(lib.commandErrorList.containsKey(cmdString+"SelfOnly")) {
            msg = lib.commandErrorList.get(cmdString + "SelfOnly");
            return invoker.getUserType().equals(priority.Staff) || invoker.getUserName().equals(arg);
        }
        return true;
    }
    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        if(hasConfirm(invoker,arg)){
            cmd.execute(invoker,arg);
        }else{
            throw new PermissionDeniedException(msg);
        }
    }

}
