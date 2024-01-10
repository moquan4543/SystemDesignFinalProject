package command;
import exception.PermissionDeniedException;
import object.User;
import object.Permission;

public class ConfirmDecorator implements Command{
    private final Command cmd;
    private String msg = "";
    public ConfirmDecorator(Command cmd){
        this.cmd = cmd;
    }
    private boolean isStaff(User user){
        return user.getUserType().equals(Permission.Staff);
    }
    private boolean hasConfirm(User invoker,String arg){
        if(cmd.getCommandPermissionLevel().equals(CommandPermissionLevel.Staff)){
            msg = cmd.getPermissionDeniedMsg();
            return isStaff(invoker);
        }else if(cmd.getCommandPermissionLevel().equals(CommandPermissionLevel.Self)) {
            msg = cmd.getPermissionDeniedMsg();
            return invoker.getUserType().equals(Permission.Staff) || invoker.getUserName().equals(arg);
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
    @Override
    public CommandPermissionLevel getCommandPermissionLevel() {
        return null;
    }
    @Override
    public String getPermissionDeniedMsg() {
        return null;
    }
}
