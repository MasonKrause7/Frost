package csci2010.program1;


import org.frost.util.Component;

/**
 *
 * @author Candelario Aguilar Torres
 */
@Component
public class Playlist {

    public final int MAX = 15;
    private int numberOfSongs;
    public Song[] songs;

    public Playlist() {
        this.numberOfSongs = 0;
        this.songs = new Song[MAX];

    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public int getLength() {
        int length = 0;
        for (int i = 0; i < numberOfSongs; i++) {
            length += songs[i].getLength();
        }

        return length;
    }

    public boolean addSong(Song song) {
        boolean songAdded = false;
        if (numberOfSongs < MAX) {
            songs[numberOfSongs] = song;
            songAdded = true;
        }

        numberOfSongs++;

        return songAdded;

    }

    public void display() {
        if (numberOfSongs == 0) {
            int[] minutesAndSeconds = toMinutesAndSeconds(getLength());
            int minutes = minutesAndSeconds[0];
            int sedonds = minutesAndSeconds[1];
            System.out.println("Playlist is empty");
            System.out.printf("Total time: %s:%02d", minutes, sedonds);
            System.out.println();
        } else {
            for (int i = 0; i < numberOfSongs; i++) {
                songs[i].display();
            }
            int[] minutesAndSeconds = toMinutesAndSeconds(getLength());
            int minutes = minutesAndSeconds[0];
            int sedonds = minutesAndSeconds[1];
            System.out.printf("Total time: %s:%02d", minutes, sedonds);
            System.out.println();
        }
    }

    private int[] toMinutesAndSeconds(int totalLength) {
        int[] trackLength = new int[2];
        trackLength[0] = totalLength / 60;
        trackLength[1] = totalLength % 60;
        return new int[] {totalLength / 60, totalLength % 60};
    }

    public void clear() {
        numberOfSongs = 0;

    }

}
