package com.favoritebookmarks.repository;

import com.favoritebookmarks.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>
{

}
