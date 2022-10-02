package csci2010.program1;

import org.frost.util.annotations.Component;

/**
 *
 * @author Candelario Aguilar Torres
 */
@Component
public class AlbumCollection {

    public final int MAX = 15;

    private int numberOfAlbums;
    private Album[] albums;

    public AlbumCollection() {
        this.numberOfAlbums = 0;
        albums = new Album[MAX];

    }

    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public boolean addAlbum(Album album) {
        boolean albumAdded = false;
        if(numberOfAlbums < MAX) {
          albums[numberOfAlbums] = album;
         numberOfAlbums++;
        }
        return albumAdded;

    }

    public Album findAlbum(String title, String artitst) {
        Album foundAlbum = null;
        if (numberOfAlbums != 0) {

            for (int i = 0; i < numberOfAlbums; i++) {
                if (albums[i].getTitle().equalsIgnoreCase(title) && albums[i].getArtist().equalsIgnoreCase(artitst)) {
                    foundAlbum = albums[i];
                    break;
                }
            }
        }

        return foundAlbum;
    }

    public void sortAlbums() {
        for (int i = 0; i < numberOfAlbums - 1; i++) {
            for (int j = i + 1; j < numberOfAlbums; j++) {
                if (albums[j].comesBefore(albums[i])) {
                    Album tmp = albums[j];
                    albums[j] = albums[i];
                    albums[i] = tmp;

                }
            }
        }
    }

    public void displayAlbums() {

        for (int i = 0; i < numberOfAlbums; i++) {
            String tracks = albums[i].getNumTracks() == 1 ? "track" : "tracks";
            System.out.printf("%s - %s (%d %s)", albums[i].getTitle(), albums[i].getArtist(), albums[i].getNumTracks(), tracks);
            System.out.println();
        }

    }

    public void displaySongs() {
        for (int i = 0; i < numberOfAlbums; i++) {
            albums[i].displayAlbum();
            albums[i].displayTrackList();
        }

    }

}
