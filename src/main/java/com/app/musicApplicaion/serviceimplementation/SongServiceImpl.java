package com.app.musicApplicaion.serviceimplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.repository.SongRepository;
import com.app.musicApplicaion.service.SongService;
import java.util.List;


@Service
public class SongServiceImpl implements SongService {

    @Autowired
   private SongRepository songRepository;
   
    
    @Override
    public String addSongs(Song song)  
    {
    if (titleExists(song.getTitle())) {
       return "the song alredy added";
    } else
    songRepository.save(song);
    return "Song added succefully";
}


    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

	@Override
	public boolean titleExists(String title) {
		return songRepository.findByTitle(title).isPresent();
	}
	@Override
	public boolean findByTitle(String title) {
		 return songRepository.findByTitle(title).isPresent();
	}


	@Override
	public void updateSong(Song song) {
		songRepository.save(song);
		
	}


    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);
    }


	

   

}
