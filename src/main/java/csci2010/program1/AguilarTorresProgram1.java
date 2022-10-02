package csci2010.program1;

import org.frost.util.Component;
import org.frost.util.Inject;

import java.util.Scanner;

/**
 *CSCI 2010 Programming Assignment 1.
 * Program is a music manager where albums with track lists can be create stored and sorted in 
 * an album collection. A playlist of songs can also be created and managed.
 *
 * @author Candelario Aguilar Torres
 */
@Component
public class AguilarTorresProgram1 {
    AlbumCollection albumCollection;
    Playlist playlist;
    @Inject
    public AguilarTorresProgram1(AlbumCollection albumCollection, Playlist playlist) {
        this.albumCollection = albumCollection;
        this.playlist = playlist;

    }
    public AguilarTorresProgram1(){

    }

    public void start() {

        System.out.println("Welcome to Candelario's music manager!");

        boolean flag = true;

        while (flag) {
            System.out.println();
            System.out.println("Choose one of the following:");
            int choice;
            displayMenu();
            choice = getChoice();
            switch (choice) {
                case 1: {
                    System.out.println();
                    Album album = createAlbum();
                    albumCollection.addAlbum(album);

                    break;
                }
                case 2: {
                    System.out.println();
                    albumCollection.displayAlbums();
                    break;
                }
                case 3: {
                    System.out.println();
                    albumCollection.displaySongs();
                    break;
                }
                case 4: {
                    albumCollection.sortAlbums();
                    break;
                }
                case 5: {
                    Album album = getAlbumFromCollection(albumCollection);
                    Song song = getSongFromAlbum(album);
                    playlist.addSong(song);
                    break;

                }

                case 6: {
                    System.out.println();
                    playlist.display();
                    break;
                }

                case 7: {
                    playlist.clear();
                    break;
                }

                case 8: {
                    System.out.println("Thank you for choosing Candelario's music manager!");
                    flag = false;

                    break;
                }
                default: {
                    System.out.println();
                    System.out.println("Invalid choice");
                    break;
                }

            }

        }

    }

    private static Scanner scanner = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("1. Add an album to the collection");
        System.out.println("2. Display the albums in the collection");
        System.out.println("3. Display the songs in the collection");
        System.out.println("4. Sort the albums in the collection");
        System.out.println("5. Add a song to the playlist");
        System.out.println("6. Display the playlist");
        System.out.println("7. Clear playlist");
        System.out.println("8. Exit the program");

    }

    public static int getChoice() {

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;

    }

    public static Album createAlbum() {

        System.out.println("Album title:");
        String title = scanner.nextLine();
        System.out.println();
        System.out.println("Album artist:");
        String artist = scanner.nextLine();
        System.out.println();
        System.out.println("How many tracks on the album?");
        int numberOfTracks = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        Song[] trackList = getTrackList(numberOfTracks, artist);
        Album album = new Album(title, artist, trackList);
        return album;

    }

    private static Song[] getTrackList(int numberOfTracks, String artist) {

        int tracksAdded = 0;
        Song[] songs = new Song[numberOfTracks];
        while (tracksAdded < songs.length) {
            String title;
            int lengthInMinutes;
            int lengthInSeconds;
            System.out.println("Track " + (tracksAdded + 1) + " title:");
            title = scanner.nextLine();
            System.out.println("Track " + (tracksAdded + 1) + " length:");
            lengthInMinutes = scanner.nextInt();
            lengthInSeconds = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            Song song = new Song(title, artist, lengthInMinutes, lengthInSeconds);
            songs[tracksAdded] = song;
            tracksAdded++;
        }

        return songs;

    }

    private static Album getAlbumFromCollection(AlbumCollection albumCollection) {

        String title;
        String artist;
        Album album = null;

        while (album == null) {
            System.out.println("Album title:");
            title = scanner.nextLine();
            System.out.println();
            System.out.println("Album artist");
            artist = scanner.nextLine();
            System.out.println();
            album = albumCollection.findAlbum(title, artist);
            if (album == null) {
                System.out.println("Album is not in the collection");
            }

        }
        return album;

    }

    private static Song getSongFromAlbum(Album album) {

        album.displayTrackList();

        Song song = null;
        while (song == null) {
            System.out.println("Choose a track:");
            int trackNumber = scanner.nextInt();
            scanner.nextLine();
            song = album.getTrack(trackNumber);
        }
        return song;
    }
}
