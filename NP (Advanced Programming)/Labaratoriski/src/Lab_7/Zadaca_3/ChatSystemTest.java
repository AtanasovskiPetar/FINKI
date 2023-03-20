package Lab_7.Zadaca_3;

//package ChatRooms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }

}
class ChatRoom{
    String name;
    Set<String> userSet;
    ChatRoom(String name){
        this.name=name;
        this.userSet=new TreeSet<>();
    }
    public void addUser(String username){
        this.userSet.add(username);
    }
    public void removeUser(String username){
        this.userSet.remove(username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", name));
        if(userSet.isEmpty()){
            sb.append("EMPTY\n");
            return sb.toString();
        }
        userSet.forEach(user->sb.append(user).append("\n"));
        return sb.toString();
    }
    public boolean hasUser(String username){
        return userSet.contains(username);
    }
    public int numUsers(){
        return userSet.size();
    }

    public String getName() {
        return name;
    }

    public Set<String> getUserSet() {
        return userSet;
    }
}
class ChatSystem{
    Map<String, ChatRoom> chatRoomMap;
    Set<String> usersSet;
    public ChatSystem() {
        this.chatRoomMap=new TreeMap<>();
        this.usersSet = new TreeSet<>();
    }
    public void addRoom(String roomName){
        if(!chatRoomMap.containsKey(roomName)){
            chatRoomMap.put(roomName, new ChatRoom(roomName));
        }
    }
    public void removeRoom(String roomName){
        if(chatRoomMap.containsKey(roomName)){
            chatRoomMap.remove(roomName);
        }
    }
    ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if(!chatRoomMap.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }
        return chatRoomMap.get(roomName);
    }
    public void register(String userName) throws NoSuchRoomException, NoSuchUserException {
        usersSet.add(userName);
        if(!chatRoomMap.isEmpty()){
            List<ChatRoom> newList = chatRoomMap.values().stream().sorted(Comparator.comparing(ChatRoom::numUsers)).collect(Collectors.toList());
            ChatRoom roomToAdd = newList.get(0);
            registerAndJoin(userName, roomToAdd.getName());
        }

//        chatRoomMap.get(roomToAdd.getName()).addUser(userName);
    }
    public void registerAndJoin(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        usersSet.add(userName);
        //chatRoomMap.get(roomName).addUser(userName);
        joinRoom(userName, roomName);
    }
    public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if(!chatRoomMap.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }if(!usersSet.contains(userName)){
            throw new NoSuchUserException(userName);
        }
        chatRoomMap.get(roomName).addUser(userName);
    }
    public void leaveRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if(!chatRoomMap.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }if(!usersSet.contains(userName)){
            throw new NoSuchUserException(userName);
        }
        chatRoomMap.get(roomName).removeUser(userName);

    }
    public void followFriend(String userName, String friend_username) throws NoSuchUserException {
        if(!usersSet.contains(friend_username)){
            throw new NoSuchUserException(friend_username);
        }
        chatRoomMap.values().stream()
                .filter(room->room.hasUser(friend_username))
                .forEach(room->room.addUser(userName));
    }
}
class NoSuchRoomException extends Exception{
    public NoSuchRoomException(String msg) {
        super(msg);
    }
}
class NoSuchUserException extends Exception{
    public NoSuchUserException(String msg) {
        super(msg);
    }
}
