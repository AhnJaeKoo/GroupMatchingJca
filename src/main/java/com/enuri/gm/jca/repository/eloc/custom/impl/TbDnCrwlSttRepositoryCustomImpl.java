package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlSttRepositoryCustom;
import com.enuri.gm.jca.util.JpaUtil;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class TbDnCrwlSttRepositoryCustomImpl implements TbDnCrwlSttRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> findByCateCdAndSttDt(String cateCd, String sDate, String eDate) {
		// 전체조회는 대카테 기준으로 추출
		int len = cateCd.length() == 0 ? 2 : cateCd.length();

		String sql = """
				SELECT a.cateCd,
				       colcQaCnt,
				       colcQaMtcCnt,
				       uprcsQaCnt,
				       uprcsQaMtcCnt,
				       addModelCnt,
				       addModelMtcCnt,
				       updModelCnt,
				       updModelMtcCnt
				FROM   (SELECT Substring(cate_cd, 1, %d) cateCd,
				               Sum(colc_qa_cnt)         colcQaCnt,
				               Sum(colc_qa_mtc_cnt)     colcQaMtcCnt,
				               Sum(uprcs_qa_cnt)        uprcsQaCnt,
				               Sum(uprcs_qa_mtc_cnt)    uprcsQaMtcCnt
				        FROM   tb_dn_crwl_stt
				        WHERE  cate_cd LIKE :cateCd
				               AND stt_dt = :eDate
				        GROUP  BY Substring(cate_cd, 1, %d)) a
				       INNER JOIN (SELECT Substring(cate_cd, 1, %d) cateCd,
				                          Sum(add_model_cnt)       addModelCnt,
				                          Sum(add_model_mtc_cnt)   addModelMtcCnt,
				                          Sum(upd_model_cnt)       updModelCnt,
				                          Sum(upd_model_mtc_cnt)   updModelMtcCnt
				                   FROM   tb_dn_crwl_stt
				                   WHERE  cate_cd LIKE :cateCd
				                          AND stt_dt BETWEEN :sDate AND :eDate
				                   GROUP  BY Substring(cate_cd, 1, %d)) b
				               ON a.cateCd = b.cateCd
				ORDER  BY catecd
				""".formatted(len, len, len, len);

		Query query = elocEm.createNativeQuery(sql);
		query.setParameter("cateCd", cateCd + "%");
		query.setParameter("sDate", sDate.replace("-", ""));
		query.setParameter("eDate", eDate.replace("-", ""));
		query = JpaUtil.setQueryToMap(query);

		return query.getResultList();
	}
}