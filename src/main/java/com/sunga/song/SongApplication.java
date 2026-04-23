package com.sunga.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class SongApplication {

	@Bean
	CommandLineRunner seedSongs(SongRepository songRepository) {
		return args -> {
			if (songRepository.count() > 0) {
				return;
			}

			Song song1 = new Song();
			song1.setAlbum("Achtung Baby");
			song1.setArtist("U2");
			song1.setGenre("Rock");
			song1.setTitle("One");
			song1.setUrl("https://youtu.be/ftjEcrrf7r0");

			Song song2 = new Song();
			song2.setAlbum("...Baby One More Time");
			song2.setArtist("Britney Spears");
			song2.setGenre("Pop");
			song2.setTitle("Sometimes");
			song2.setUrl("https://youtu.be/t0bPrt69rag");

			Song song3 = new Song();
			song3.setAlbum("Ultraelectromagneticpop!");
			song3.setArtist("Eraserheads");
			song3.setGenre("OPM");
			song3.setTitle("Ligaya");
			song3.setUrl("https://youtu.be/XibB-5BPdrY");

			songRepository.saveAll(List.of(song1, song2, song3));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SongApplication.class, args);
	}

}