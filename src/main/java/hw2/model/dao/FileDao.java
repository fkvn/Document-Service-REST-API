package hw2.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hw2.model.File;

@Repository
public interface FileDao extends JpaRepository<File, String> {

}
