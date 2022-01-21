package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.eloc.QTbDnCrwlQaPl;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlQaPlRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional
public class TbDnCrwlQaPlRepositoryCustomImpl implements TbDnCrwlQaPlRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;
	private QTbDnCrwlQaPl qaPl = QTbDnCrwlQaPl.tbDnCrwlQaPl;

	@Override
	@Transactional(readOnly = true)
	public int countByCateCdStartsWith(String cateCd, boolean isNonAtt, boolean isNonBrndMkr) {
		String countSql = """
				/* TbDnCrwlQaPlRepositoryCustomImpl:countByCateCdStartsWith:29 */
				SELECT Count(*)
				FROM   (SELECT qa.qa_id,
				               qa.chg_cate_cd,
				               qa.brnd_id,
				               qa.mkr_id,
				               qa.cate_cd
				        FROM   tb_dn_crwl_qa_pl pl WITH (nolock)
				               INNER JOIN tb_dn_crwl_qa qa WITH (nolock)
				                       ON pl.qa_id = qa.qa_id
				        WHERE  qa.use_yn = 'Y'
				               AND qa.cate_cd LIKE :cateCd + '%') a
				WHERE  1 = 1
				""";

		if (isNonAtt) {	// 속성 미등록 리스트
			countSql += " AND dbo.Udf_dn_crwl_spec(qa_id, chg_cate_cd) = '' ";
		}

		if (isNonBrndMkr) {	// 브랜드/제조사 미등록 리스트
			countSql += " AND (brnd_id = 0 OR mkr_id = 0) ";
		}

		Query query = elocEm.createNativeQuery(countSql);
		query.setParameter("cateCd", cateCd);

		return (int) query.getResultList().get(0);
	}

	@Override
	@Modifying
	public long updateByUseYn(List<Map<String, Object>> list, long qaId, String userId) {
		AtomicLong result = new AtomicLong();

		list.forEach(map -> {
			result.addAndGet(
					new JPAQueryFactory(elocEm)
					.update(qaPl)
					.set(qaPl.useYn, map.get("useYn").toString())
					.set(qaPl.updDtm, new Timestamp(System.currentTimeMillis()))
					.set(qaPl.updEmpId, userId)
					.where(qaPl.plNo.eq(Long.valueOf(map.get("plNo").toString()))
							.and(qaPl.qaId.eq(qaId)))
					.execute()
			);
		});

		return result.get();
	}
}