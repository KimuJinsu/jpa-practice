package com.jinsu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jinsu.entity.Album;
import com.jinsu.entity.Book;
import com.jinsu.entity.Item;
import com.jinsu.entity.Movie;

public class Main {

    public static void main(String... args) throws InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
        	
            tx.begin();
            
            Album album = new Album();
            album.setName("haris");
            album.setPrice(20000);
            album.setArtist("swift");
            em.persist(album);
            
            Movie movie = new Movie();
            movie.setName("poter");
            movie.setPrice(25000);
            movie.setActor("jinsu");
            movie.setDirector("bumjun");
            em.persist(movie);
            
            Book book = new Book();
            book.setName("romeo");
            book.setPrice(15000);
            book.setAuthor("kim");
            book.setIsbn("12345");
            em.persist(book);
            
            em.flush();
            em.clear();
            
//            Book gotBook = em.find(Book.class, book.getId());
//            Album gotAlbum = em.find(Album.class, album.getId());
            Item item = em.find(Item.class, book.getId());
      
            tx.commit();
            
          } catch (Exception e) {
            tx.rollback();	
          } finally {
        	em.close();
        
        }

    }
}
