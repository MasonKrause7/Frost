package csci2010.program1;

import org.frost.util.Component;

/**
 *CSCI 2010 Programming Assignment 1.
 * maintains a song title, artists an length  of the song in seconds.
 * @author Candelario Aguilar Torres
 */
@Component
public class Song {

    private String title;
    private String artist;
    private int length;
   /**
    * Default constructor initialized member variables; Uses toSeconds helper method to initialize the total
    * length of the song in seconds.
    *
    * @param title title of the song.
    * @param artist name of the artist.
    * @param minutes song minutes ex. 4:30 4 is the number of minutes passed.
    * @param seconds song seconds  ext 4:30 30 is the number of seconds passed.
    */
    public Song(String title, String artist, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.length = toSeconds(minutes, seconds);

    }
    public Song(){

    }
  /**
   * 
   * @return  title of song.
   */
    public String getTitle() {
        return title;
    }
/**
   * 
   * sets title of the song.
   */
    public void setTitle(String title) {
        this.title = title;
    }
/**
   * return song artist.
   * @return  artist.
   */
    public String getArtist() {
        return artist;
    }
/**
   * 
   * sets artist of the song.
   */
    public void setArtist(String artist) {
        this.artist = artist;
    }
/**
 * returns length of song.
 * @return the length of the song in seconds.
 */
    public int getLength() {
        return length;
    }
/**
 * 
 * Sets the length of the song in seconds.
 * @param length seconds the length of the song should be set to.
 */
    public void setLength(int length) {
        this.length = length;
    }
/**
 * displays the the title, artist, and the length of the song in format "title - artist (minutes:seconds)".
 */
    public void display() {
        int[] trackLength = toMinutesAndSeconds();
        int minutes = trackLength[0];
        int seconds = trackLength[1];
        System.out.printf("%s - %s (%d:%02d)", title, artist, minutes, seconds);
        System.out.println();
    }
/**
 * helper methods that converts song length in to seconds.
 * @param minutes how many minutes the song is.
 * @param seconds how many seconds the song is.
 * @return total length of song in seconds.
 */
    private int toSeconds(int minutes, int seconds) {
        return minutes * 60 + seconds;
    }

    /**
     * helper method that takes the total song length in seconds and returns an array
     * where the first index stores the minutes of the song, and the second index stores
     * the seconds of the song.
     * @return array of seconds and minutes
     */
    private int[] toMinutesAndSeconds() {
  
        return new int[] {this.length / 60, this.length % 60};
    }

}
