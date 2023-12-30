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
        //輸入的單行指令由空格隔開(可以是多個)
        //透過stream的方法轉成List，構造成LinkedList
        //透過LinkedList提供的方法模擬隊列，避免索引寫死如果沒有操作數會越界的問題
        LinkedList<String> strQueue = new LinkedList<>(Arrays.stream(cmdStr.split("\\s+")).collect(Collectors.toList()));
        User invoker = users.get(strQueue.removeFirst());
        String instruction = strQueue.removeFirst();
        String arg;
        if(strQueue.isEmpty()){
            //前面已remove兩次，如果是空的代表後面沒有參數了
            arg = "";
        }else{
            arg = strQueue.removeFirst();
        }
        //如果指令需要用到BufferedReader，就直接用lib的
        this.br = br;
        //仔細觀察可以發現除了addBook外的指令都需引數
        //但為了符合開閉原則，即使是addBook仍然傳遞引數(空字串)
        //呼叫CommandFactory構造指令類
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
