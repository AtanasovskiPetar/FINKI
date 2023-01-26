package Lab_8.Zadaca_1;

//package MP3Player;

import java.util.ArrayList;
import java.util.List;

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

//Vasiot kod ovde
interface State{
    void pressPlay();
    void pressStop();
    void pressFWD();
    void pressREW();
}
abstract class AbstratStateClass implements State{
    MP3Player player;
    AbstratStateClass(MP3Player player){
        this.player=player;
    }

    public void pressFWD(){
        System.out.println("Forward...");
        if(player.getCurrSong()==player.getSongList().size()-1){
            player.setCurrSong(0);
        }else{
            player.setCurrSong(player.getCurrSong()+1);
        }
        player.setStopState(player.getStopState());
    }

    public void pressREW(){
        System.out.println("Reward...");
        if(player.getCurrSong()==0){
            player.setCurrSong(player.getSongList().size()-1);
        }else{
            player.setCurrSong(player.getCurrSong()-1);
        }
        player.setStopState(player.getStopState());
    }

}
class PlayState extends AbstratStateClass{
    public PlayState(MP3Player player) {
        super(player);
    }
    @Override
    public void pressPlay() {
        System.out.println("Song is already playing");
    }

    @Override
    public void pressStop() {
        System.out.printf("Song %d is paused\n", player.getCurrSong());
        player.setStopState(player.getStopState());
    }
}
class StopState extends AbstratStateClass{
    Boolean falg=false;
    StopState(MP3Player player) {
        super(player);
    }

    @Override
    public void pressPlay() {
        System.out.printf("Song %d is playing\n", player.getCurrSong());
        player.setPlayState(player.getPlayState());
        falg=false;
    }

    @Override
    public void pressStop() {
        if(falg){
            System.out.printf("Songs are already stopped\n", player.getCurrSong());
            return;
        }
        System.out.printf("Songs are stopped\n");
        player.setCurrSong(0);
        falg=true;
    }
}
class MP3Player{
    private State playState;
    private State stopState;
    private State state;
    private int currSong;
    List<Song> songList;
    public MP3Player(List<Song> songList) {
        this.songList=songList;
        this.playState = new PlayState(this);
        this.stopState = new StopState(this);
        this.state=stopState;
    }

    public int getCurrSong() {
        return currSong;
    }

    public State getPlayState() {
        return playState;
    }

    public State getStopState() {
        return stopState;
    }

    public void setPlayState(State playState) {
        this.state = playState;
    }

    public void setStopState(State stopState) {
        this.state = stopState;
    }

    public void setCurrSong(int currSong) {
        this.currSong = currSong;
    }
    void pressPlay(){
        state.pressPlay();
    }
    void pressStop(){
        state.pressStop();
    }
    void pressFWD(){
        state.pressFWD();
    }
    void pressREW(){
        state.pressREW();
    }
    void printCurrentSong(){
        System.out.println(songList.get(currSong).toString());
    }

    public State getState() {
        return state;
    }

    public List<Song> getSongList() {
        return songList;
    }

    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + currSong +
                ", songList = " + songList +
                '}';
    }
}
class Song{
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    //Song{title=first-title, artist=first-artist}
    public String toString() {
        return "Song{" +
                "title=" + title  +
                ", artist=" + artist  +
                '}';
    }
}
