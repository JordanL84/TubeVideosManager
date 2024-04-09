package tests;

import org.junit.Test;
import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;
import java.util.*;

/**
 * 
 * You need student tests if you are asking for help during
 * office hours about bugs in your code. Feel free to use
 * tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {
	
	@Test
	public void testVideo() {
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		
		Video video = new Video(title, url, durationInMinutes, genre);
		video.addComments("Nice video");
		video.addComments("Recommended");
		Video videoCopy = new Video(video);
		System.out.println(video.toString());
		System.out.println(videoCopy.toString());
		System.out.println("\n" + video.compareTo(videoCopy));
		System.out.println(video.equals(videoCopy));
		
		video.addComments("Fantastic");
		System.out.println();
		System.out.println(video.getComments());
		System.out.println(videoCopy.getComments());
		
		//Testing getters
		System.out.println("\n**Testing Getters**");
		System.out.println(video.getTitle());
		System.out.println(video.getUrl());
		System.out.println(video.getDurationInMinutes());
		System.out.println(video.getGenre());
		
	}
	
	@Test
	public void testPlaylist() {
		Playlist playlist = new Playlist("Favorites");
		String title = "How to Draw in Java Tutorial";
		playlist.addToPlaylist(title);
		playlist.addToPlaylist(title); 
		playlist.addToPlaylist("Hello");
		System.out.println(playlist.toString());
		playlist.removeFromPlaylistAll(title);
		System.out.println(playlist.getPlaylistVideosTitles());
		playlist.addToPlaylist(title);
		playlist.addToPlaylist("Happy");
		playlist.addToPlaylist("Sad");
		System.out.println(playlist.toString());
		playlist.shuffleVideoTitles(null);
		System.out.println(playlist.toString());
		
		System.out.println("\n**Testing copy constructor**");
		Playlist playlistCopy = new Playlist(playlist);
		System.out.println(playlistCopy + "\n");
		playlistCopy.removeFromPlaylistAll("Happy");
		System.out.println(playlist);
		System.out.println(playlistCopy);
	}
	
	@Test
	public void testTubeVideosManager() {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		
		//Adding first video
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		
		//Adding second video
		title = "Git & GitHub Crash Course for Beginners";
		url = "https://www.youtube.com/embed/SWYqp7iY_Tc";
		durationInMinutes = 33;
		genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		
		System.out.println(tubeVideosManager.getAllVideosInDB());
		System.out.println("\n" + tubeVideosManager.findVideo(title));
		tubeVideosManager.addComments(title, "Amazing");
		System.out.println(tubeVideosManager.findVideo(title).getComments());
		
		System.out.println("\n**Playlist Methods**");
		String playlistName = "ToWatch";
		tubeVideosManager.addPlaylist(playlistName);
		tubeVideosManager.addVideoToPlaylist(title, playlistName);
		tubeVideosManager.addVideoToPlaylist("How to Draw in Java Tutorial", playlistName);
		Playlist playlist = tubeVideosManager.getPlaylist(playlistName);
		System.out.println(playlist);
		System.out.println(Arrays.toString(tubeVideosManager.getPlaylistsNames()));
		System.out.println("\n" + tubeVideosManager.searchForVideos(playlistName, title, durationInMinutes, genre));
		
		tubeVideosManager.clearDatabase();
		//Checking if database is cleared
		System.out.println("\n" + tubeVideosManager.getAllVideosInDB());
		System.out.println(Arrays.toString(tubeVideosManager.getPlaylistsNames()));
	}
}
