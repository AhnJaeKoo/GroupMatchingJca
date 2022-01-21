package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.eloc.QTbDnCrwlQa;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlQaRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class TbDnCrwlQaRepositoryCustomImpl implements TbDnCrwlQaRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;

	private QTbDnCrwlQa qa = QTbDnCrwlQa.tbDnCrwlQa;	// D크롤링 QA 테이블

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> findByCateCdStartsWith(String cateCd, boolean isNonAtt, boolean isNonBrndMkr, String sortType, Pageable pageable) {

		String countSql = """
				/* TbDnCrwlQaRepository:findByCateCdStartsWith:39 */
				SELECT count(*)
				FROM   tb_dn_crwl_qa qa with (nolock)
				WHERE  qa.cate_cd LIKE :cateCd + '%'
				  AND  qa.use_yn = 'Y'
				""";

		if (isNonAtt) {	// 속성 미등록 리스트
			countSql += " AND dbo.udf_dn_crwl_spec(qa_id, chg_cate_cd) = '' ";
		}

		if (isNonBrndMkr) {	// 브랜드/제조사 미등록 리스트
			countSql += " AND (brnd_id = 0 or mkr_id = 0) ";
		}

		Query query = elocEm.createNativeQuery(countSql);
		query.setParameter("cateCd", cateCd);
		int totCnt = (int) query.getResultList().get(0);

		String sql = """
				/* TbDnCrwlQaRepository:findByCateCdStartsWith:51 */
				SELECT dbo.udf_dn_crwl_spec(qa_id, chg_cate_cd) enr_atr, a.*
				  FROM (
					SELECT DISTINCT qa.qa_id,
					                qa.mkr_id,
					                qa.brnd_id,
					                qa.chg_cate_cd,
					                qa.dn_rnk,
					                qa.dn_gr_catl_id,
					                qa.model_nm,
					                qa.dn_cnd_nm,
					                qa.mkr_nm,
					                qa.brnd_nm,
					                --dbo.udf_dn_crwl_spec(qa.qa_id, qa.chg_cate_cd) enr_atr, 속도문제로 밖으로 뺌
					                qa.dn_atr,
					                qa.dn_volume,
					                qa.dn_cp_vlu,
					                qa.dn_qnt,
					                qa.dn_cp_unt,
					                -- string_agg 문법 대체
									Stuff((SELECT ',' + Cast(pl.pl_no AS VARCHAR(20))
									       FROM   tb_dn_crwl_qa a with (nolock)
									              LEFT OUTER JOIN tb_dn_crwl_qa_pl pl with (nolock)
									                           ON a.qa_id = pl.qa_id
									       WHERE  a.cate_cd LIKE :cateCd + '%'
									              AND a.qa_id = qa.qa_id
									              AND a.use_yn = 'Y'
									       ORDER  BY pl.pl_no
									       FOR xml path(''), type).value('.', 'varchar(MAX)'), 1, 1, '') AS plNos,
									Stuff((SELECT TOP 4 ',' + Cast(model.model_no AS VARCHAR(20)) + '|' + '[' + goods.g_factory + ']' + '[' + goods.g_brand + ']' + model.model_nm
									       FROM   tb_dn_crwl_qa a with (nolock)
									              LEFT OUTER JOIN tb_dn_crwl_qa_model model with (nolock)
									                           ON a.qa_id = model.qa_id
									              LEFT OUTER JOIN goods with (nolock)
									               			   ON model.model_no = goods.g_modelno
									       WHERE  a.cate_cd LIKE :cateCd + '%'
									              AND a.qa_id = qa.qa_id
									              AND a.use_yn = 'Y'
									       ORDER  BY model.model_no
									       FOR xml path(''), type).value('.', 'varchar(MAX)'), 1, 1, '') AS models,
									qa.reg_date,
									pl_cnt.cnt
					FROM   tb_dn_crwl_qa qa with (nolock)
					       LEFT OUTER JOIN tb_dn_crwl_qa_pl pl with (nolock)
					                    ON qa.qa_id = pl.qa_id
					       LEFT OUTER JOIN (SELECT a.qa_id,
											       Count(*) cnt
											FROM   tb_dn_crwl_qa_pl a WITH (nolock)
												   INNER JOIN pricelist b with (nolock)
												   		   ON a.pl_no = b.pl_no
											WHERE  a.use_yn = 'Y'
											GROUP  BY a.qa_id
											) pl_cnt
										ON qa.qa_id = pl_cnt.qa_id
					WHERE  qa.cate_cd LIKE :cateCd + '%'
					       AND qa.use_yn = 'Y'
				) a
			WHERE 1=1
				""";

		if (isNonAtt) {	// 속성 미등록 리스트
			sql += " AND dbo.udf_dn_crwl_spec(qa_id, chg_cate_cd) = '' ";
		}

		if (isNonBrndMkr) {	// 브랜드/제조사 미등록 리스트
			sql += " AND (brnd_id = 0 or mkr_id = 0) ";
		}

		//정렬순서 타입에 따른 정렬
		sql += switch (sortType) {
			case "1" -> " ORDER BY dn_rnk, dn_cnd_nm ";
			case "2" -> " ORDER BY reg_date desc, model_nm, dn_cnd_nm ";
			default -> throw new IllegalArgumentException("허용되지 않는 정렬순서 타입(sortType) 입니다.");
		};

		query = elocEm.createNativeQuery(sql);
		query.setParameter("cateCd", cateCd);
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		// map 방식으로 변경
		NativeQueryImpl<Map<String, Object>> nativeQuery = (NativeQueryImpl<Map<String, Object>>) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		return new PageImpl<>(nativeQuery.getResultList(), pageable, totCnt);
	}

	@Override
	@Modifying
	public long updateUseYnByQaIds(List<Long> qaIds, String userId) {
		return new JPAQueryFactory(elocEm)
				.update(qa)
				.set(qa.useYn, "D")
				.set(qa.updDtm, new Timestamp(System.currentTimeMillis()))
				.set(qa.updEmpId, userId)
				.where(qa.qaId.in(qaIds))
				.execute();
	}

	@Override
	@Modifying
	public long updateQaByQaId(List<Map<String, Object>> qaInfos) {
		long i = 0;
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(elocEm);

		for (Map<String, Object> map : qaInfos) {
			long qaId = Long.parseLong(map.get("qaId").toString());
			String chgCateCd = String.valueOf(map.get("chgCateCd"));
			String modelNm = String.valueOf(map.get("modelNm"));
			String dnCndNm = String.valueOf(map.get("dnCndNm"));
			int mkrId = Integer.parseInt(map.get("mkrId").toString());
			String mkrNm = String.valueOf(map.get("mkrNm"));
			int brndId = Integer.parseInt(map.get("brndId").toString());
			String brndNm = String.valueOf(map.get("brndNm"));
			float dnVolume = Float.parseFloat(map.get("dnVolume").toString());
			String dnCpVlu = String.valueOf(map.get("dnCpVlu"));
			int dnQnt = Integer.parseInt(map.get("dnQnt").toString());
			String dnCpUnt = String.valueOf(map.get("dnCpUnt"));
			String updEmpId = String.valueOf(map.get("updEmpId"));

			jpaQueryFactory.update(qa)
				.set(qa.chgCateCd, chgCateCd)
				.set(qa.modelNm, modelNm)
				.set(qa.dnCndNm, dnCndNm)
				.set(qa.mkrId, mkrId)
				.set(qa.mkrNm, mkrNm)
				.set(qa.brndId, brndId)
				.set(qa.brndNm, brndNm)
				.set(qa.dnVolume, dnVolume)
				.set(qa.dnCpVlu, dnCpVlu)
				.set(qa.dnQnt, dnQnt)
				.set(qa.dnCpUnt, dnCpUnt)
				.set(qa.updEmpId, updEmpId)
				.set(qa.updDtm, new Timestamp(System.currentTimeMillis()))
				.where(qa.qaId.eq(qaId))
				.execute();

			i++;
		}

		return i;
	}
}