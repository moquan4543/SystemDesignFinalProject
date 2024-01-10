package controller;
import command.*;

public class CommandFactory{
    public static Command createCommand(String cmdString) throws ClassNotFoundException {
        try{
            cmdString = "command." + cmdString.substring(0,1).toUpperCase() + cmdString.substring(1);
            Class<?> cmdClass = Class.forName(cmdString);
            Object cmdInstance = cmdClass.getDeclaredConstructor().newInstance();
            if (cmdInstance instanceof Command) {
                return new ConfirmDecorator((Command) cmdInstance);
            }
        }catch(Exception x){
            throw new ClassNotFoundException("Command not found");
        }
        return null;
    }
}
