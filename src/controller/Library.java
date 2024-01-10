package controller;
import command.*;
import object.Book;
import object.User;
import java.io.BufferedReader;
import java.util.*;
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
    @SuppressWarnings("all")
    public int processTransaction(String cmdStr, BufferedReader br){
        LinkedList<String> strQueue = new LinkedList<>(Arrays.stream(cmdStr.split("\\s+")).collect(Collectors.toList()));
        String username = strQueue.getFirst();
        User invoker = users.get(strQueue.removeFirst());
        if(invoker == null){
            System.out.println("User "+ username+" not found");
            return -1;
        }
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
