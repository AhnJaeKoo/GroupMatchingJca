package com.enuri.gm.jca.repository.eloc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.eloc.TblEnuriMaker;

@Repository
public interface TblEnuriMakerRepository extends JpaRepository<TblEnuriMaker, Integer> {

	@Query(value = """
			SELECT m
			  FROM TblEnuriMaker m INNER JOIN FETCH m.brands b
			 WHERE m.delYn = 'N'
			       AND b.delYn = 'N'
			       AND m.makerId = :makerId
			""")
	@Transactional(readOnly = true)
	List<TblEnuriMaker> findByMakerIdAndDelYn(@Param("makerId") int makerId);

	@Query(value = """
			SELECT distinct m
			  FROM TblEnuriMaker m INNER JOIN FETCH m.brands b
			 WHERE m.delYn = 'N'
			 	   AND b.delYn = 'N'
			 	   AND m.makerNm LIKE :makerNm
			""")
	@Transactional(readOnly = true)
	List<TblEnuriMaker> findByMakerNmContainsAndDelYn(@Param("makerNm") String makerNm);

	@Query(value = """
			SELECT m
			  FROM TblEnuriMaker m INNER JOIN FETCH m.brands b
			 WHERE m.delYn = 'N'
			       AND b.delYn = 'N'
			       AND b.id.brandId = :brandId
			""")
	@Transactional(readOnly = true)
	List<TblEnuriMaker> findByBrandIdAndDelYn(@Param("brandId") int brandId);

	@Query(value = """
			SELECT distinct m
			  FROM TblEnuriMaker m INNER JOIN FETCH m.brands b
			 WHERE m.delYn = 'N'
			 	   AND b.delYn = 'N'
			 	   AND b.brandNm LIKE :brandNm
			""")
	@Transactional(readOnly = true)
	List<TblEnuriMaker> findByBrandNmContainsAndDelYn(@Param("brandNm") String brandNm);
}