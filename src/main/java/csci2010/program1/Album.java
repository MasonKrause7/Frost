package csci2010.program1;

import org.frost.util.annotations.Component;

/**
 *CSCI 2010 Programming Assignment 1.
 * stores and maintains a collection of songs.
 * @author Candelario Aguilar Torres
 */
@Component
public class Album {

    private String title;
    private String artist;
    private Song[] trackList;
 /**
  * Initialized member variables; Array of songs is passed and copied into the album trackList.
  * @param title title of album
  * @param artist artist of album
  * @param trackList songs to be copied into the album track list.
  */
    public Album(String title, String artist, Song[] trackList) {
        this.title = title;
        this.artist = artist;
        this.trackList = new Song[trackList.length];
        copyTrackList(trackList, this.trackList);

    }
/**
 * 
 * @return title
 */
    public String getTitle() {
        return title;
    }
/**
 * 
 * @return artists
 */
    public String getArtist() {
        return artist;
    }
/**
 * 
 * @return the number of tracks in the album.
 */
    public int getNumTracks() {
        return trackList.length;
    }
/**
 * user chooses a track starting from track list starting from 1. 
 * @param trackNumber track chosen to return
 * @return track.
 */
    public Song getTrack(int trackNumber) {
        if (trackNumber <= 0 || trackNumber > trackList.length) {
            return null;
        } else {
            return trackList[trackNumber - 1];// trackNumber -1 to avoid off by one error
        }

    }
/**
 * compares album passed as argument, comparison is made by artist first, if artist is the same 
 * then comparison will be by title.
 * @param album album to be compared with
 * @return true if this object album comes before the compared album.
 */
    public boolean comesBefore(Album album) {
        boolean comesBefore = false;
        if (this.artist.equalsIgnoreCase(album.artist)) {
            if (this.title.compareToIgnoreCase(album.title) < 0) {
                comesBefore = true;
            }

        } else {
            if (this.artist.compareToIgnoreCase(album.artist) < 0) {
                comesBefore = true;
            }
        }

        return comesBefore;

    }

    /**
     * Displays album in the format of "title - artist (numberOftracks track)
     */
    public void displayAlbum() {
        String tracks = trackList.length == 1 ? "track" : "tracks";
        System.out.printf("%s - %s (%d %s)", title, artist, trackList.length, tracks);
        System.out.println();

    }
/**
 * displays the song number and song with the song number formatted with two digits to the right.
 */
    public void displayTrackList() {
        int songNumber = 1;
        for (Song song : trackList) {

            System.out.printf("%2d. ", songNumber);
            song.display();
            songNumber++;
        }
    }
     /**
      * copies songs from one array into the other array.
      * @param trackListCopyFrom array to copy from.
      * @param trackListCopyTo  array to copy to.
      */
    private void copyTrackList(Song[] trackListCopyFrom, Song[] trackListCopyTo) {
        for (int i = 0; i < trackListCopyTo.length; i++) {
            trackListCopyTo[i] = trackListCopyFrom[i];
        }

    }

}
