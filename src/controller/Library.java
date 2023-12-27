package controller;
import command.*;
import object.Book;
import object.User;
import java.io.BufferedReader;
import java.util.*;
import java.lang.Object;
import java.util.stream.Collectors;

public class Library {
    public Map<String, User> users = new HashMap<>();
    public Map<Integer, Book> books = new LinkedHashMap<>();
    public BufferedReader br;
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
     * @param   cmdStr   a single line instruction which type is String
     * @return  0 if run successfully, -1 if parsing failed.
     **/
    @SuppressWarnings("all")
    public int processTransaction(String cmdStr, BufferedReader br){

        LinkedList<String> strQueue = new LinkedList<>(Arrays.stream(cmdStr.split("\\s+")).collect(Collectors.toList()));
        User invoker = users.get(strQueue.removeFirst());
        String instruction = strQueue.removeFirst();
        String arg;
        if(strQueue.isEmpty()){
            arg = "";
        }else{
            arg = strQueue.removeFirst();
        }
        this.br = br;

        try{
            Command cmd = CommandFactory.createCommand(instruction);
            invoker.setCmd(cmd);
            invoker.invoke(arg);
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }
}
