package controller;
import command.*;

@SuppressWarnings("all")
public class CommandFactory{
    public static Command createCommand(String cmdString) throws IllegalArgumentException{
        try{
            //把cmdString首字母改成大寫
            cmdString = "command." + cmdString.substring(0,1).toUpperCase() + cmdString.substring(1);
            //因為Java有Reflection可以用，所以我就不用Factory Method寫了
            //用Reflection獲取字節碼Class類
            Class<?> cmdClass = Class.forName(cmdString);
            //用Reflection構造類的實例
            Object cmdInstance = cmdClass.getDeclaredConstructor().newInstance();
            //判斷是否為Command接口的實現類
            if (cmdInstance instanceof Command) {
                return (Command) cmdInstance;
            } else {
                throw new IllegalArgumentException("Command not found");
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
