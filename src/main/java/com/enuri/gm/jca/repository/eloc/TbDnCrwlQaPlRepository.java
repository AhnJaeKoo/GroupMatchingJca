package com.enuri.gm.jca.repository.eloc;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.eloc.TbDnCrwlQaPl;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlQaPlRepositoryCustom;

@Repository
public interface TbDnCrwlQaPlRepository extends JpaRepository<TbDnCrwlQaPl, Long>, TbDnCrwlQaPlRepositoryCustom {

	@Query(value = """
			SELECT m.plNo AS plNo,
				   m.useYn AS useYn,
				   p.plModelno AS plModelno,
				   p.plGoodsnm AS plGoodsnm,
				   p.plSrvflag AS plSrvflag
			  FROM TbDnCrwlQaPl m INNER JOIN FETCH Pricelist p on m.plNo = p.plNo
			 WHERE m.qaId = :qaId
		  ORDER BY p.plModelno, m.plNo
			""")
	@Transactional(readOnly = true)
	List<Map<String, Object>> findByQaId(@Param("qaId") Long qaId);

}