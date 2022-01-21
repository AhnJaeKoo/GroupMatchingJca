package com.enuri.gm.jca.repository.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.main.TblCategory;

@Repository
public interface TblCategoryRepository extends JpaRepository<TblCategory, String> {

	@Query(value = """
			SELECT m
			  FROM TblCategory m
			 WHERE m.cSeqno > 0
			""")
	@Transactional(readOnly = true)
	List<TblCategory> findBycSeqnoGreaterThan();
}