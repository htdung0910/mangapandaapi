package com.example.demo.service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import com.example.demo.ReturnEntity.ReturnBookEntity;
import com.example.demo.ServiceInterface.BookServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {
    private static Logger log = LogManager.getLogger(BookService.class);
    @Autowired
    private BookRepository bRepo;

    @Autowired
    private ChapterReposiory cRepo;

    @Autowired
    private ImageRepository iRepo;

    @Autowired
    private GenresRepository gRepo;

    @Autowired
    private BookProcessRepository bpRepo;

    @Autowired
    private UserRepository uRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getAllTitile() {
        return bRepo.getAllTitle();
    }

    @Override
    public List<BookEntity> getMangaByTitle(String title) {
        if (title.isEmpty() || title == null) {
            return null;
        }
        try {
            List<BookEntity> returnData = bRepo.getBookByTitle(title);
            return returnData;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BookEntity> getHottestManga() {
        List<BookEntity> returnData = null;
        try {
            returnData = bRepo.findTop10ByOrderByRatingValueDesc();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnData;
    }

    @Override
    public BookEntity getByID(String id) {
        BookEntity returnData = null;
        try {
            if (bRepo.existsById(id))
                returnData = bRepo.findById(id).orElse(null);
            return returnData;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnData;
    }

    @Override
    public List<String> getImagesByChapterID(String chapterID) {
        List<String> returnData = null;
        try {
            if(!cRepo.existsById(chapterID))
                return null;
            returnData = iRepo.getImagesByChapterID(chapterID);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnData;
    }

    @Override
    public List<ChapterEntity> getChaptersByBookID(String bookID) {
        BookEntity manga = getByID(bookID);
        if (manga == null)
            return null;
        List<ChapterEntity> tempData = null;
        try {
            tempData = cRepo.getChaptersByBookID(bookID);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return tempData;
    }

    @Override
    public List<BookEntity> getBookByGenres(int quantity, int genreID) {
        try{
            if (!gRepo.existsById(genreID))
                return null;
            List<BookEntity> temp = bRepo.getBookByGenres(quantity,genreID);
            return temp;
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public BookProcessEntity getInfoUserBetweenBook(String username, String bookID) {
        BookProcessEntity bpEntity = null;
        try{
            if(uRepo.existsById(username) && bRepo.existsById(bookID)){
                bpEntity = bpRepo.getInfoUserBetweenBook(username,bookID);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return bpEntity;
    }

    @Override
    public void saveBookProcessEntity(BookProcessEntity bpEntity) {
        try{
            bpRepo.save(bpEntity);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveBookEntity(BookEntity bEntity) {
        try{
            bRepo.save(bEntity);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<BookEntity> getRecommend(String username) {
        List<BookEntity> listBookRecommend = null;
        try{
            List<Integer> listGenresRecommend= bRepo.getListGenresRecommend(username);
            String sql = "";
            for (int i = 0; i < listGenresRecommend.size(); i++) {
                if(i == 0){
                    sql += "AND g.genreID = "+listGenresRecommend.get(i)+" ";
                }else{
                    sql += "OR g.genreID = "+listGenresRecommend.get(i)+" ";
                }
            }
            Query query = entityManager.createNativeQuery(
                    "Select Top 10 * From Book b\n" +
                            "INNER JOIN Book_genres bg ON bg.bookID = b.bookID\n" +
                            "INNER JOIN Genres g ON bg.genreID = g.genreID\n" +
                            "WHERE b.bookID in (Select Top 50 b.bookID From Book b\n" +
                            "\t\t\t\t\tWhere b.rating_value between 3 and 5\n" +
                            "\t\t\t\t\torder by b.rating_value * b.rating_count Desc) " + sql + " \n"+
                            "Order by g.genreID ,newid()"
                    ,BookEntity.class);
            listBookRecommend = query.getResultList();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return listBookRecommend;
    }

    @Override
    public List<BookEntity> getFollow(String username) {
        List<BookEntity> listBookFollow = null;
        try{
            listBookFollow = bRepo.getListMangaFollowByUser(username);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return listBookFollow;
    }

    @Override
    public List<UserEntity> getTopUserPostBook() {
        List<UserEntity> listTopUserPostManga = null;
        try{
            listTopUserPostManga = uRepo.getListTopUserByRateBook();
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return listTopUserPostManga;
    }

    @Override
    public List<BookEntity> getTop10ListMangaOrderByUploadDate() {
        List<BookEntity> listBook = null;
        try{
            listBook = bRepo.getTop10ListMangaOrderByUploadDate();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return listBook;
    }
}
