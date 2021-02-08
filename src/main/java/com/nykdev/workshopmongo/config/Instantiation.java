package com.nykdev.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nykdev.workshopmongo.domain.Post;
import com.nykdev.workshopmongo.domain.User;
import com.nykdev.workshopmongo.dto.AuthorDTO;
import com.nykdev.workshopmongo.dto.CommentDTO;
import com.nykdev.workshopmongo.repository.PostRepository;
import com.nykdev.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	@Autowired
	private UserRepository useRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		useRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		useRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		
		Post post1 = new Post(null, sdf.parse("21/03/2021"),"Partiu viagem", "Vou viajar para são paulo",new AuthorDTO(bob));
		Post post2 = new Post(null,sdf.parse("23/04/2021"),"Bom dia ", "Acordei Feliz Hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2021"), new AuthorDTO(maria));
		CommentDTO c2 = new CommentDTO("Aproveite",sdf.parse("22/03/2021"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia", sdf.parse("23/03/2021"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPost().addAll(Arrays.asList(post1, post2));
		useRepository.save(maria);
		
	}

}
