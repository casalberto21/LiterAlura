package com.alura.literalura.repository;

import com.alura.literalura.model.BookData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BooksRepository extends JpaRepository<BookData, Long> {

    @Query("SELECT b FROM BookData b WHERE b.languages LIKE :language")
    List<BookData> searchBookByLanguage(String language);

    @Query("SELECT b FROM BookData b ORDER BY b.downloadCount DESC")
    List<BookData> findTop5ByOrderByDownloadCountDesc(Pageable pageable);
}