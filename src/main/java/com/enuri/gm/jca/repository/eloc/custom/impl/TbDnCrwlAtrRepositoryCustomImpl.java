package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.dto.DnAttDto;
import com.enuri.gm.jca.model.eloc.QGoodsAttribute;
import com.enuri.gm.jca.model.eloc.QGoodsAttributeElement;
import com.enuri.gm.jca.model.eloc.QTbDnCrwlAtr;
import com.enuri.gm.jca.model.eloc.QTbDnCrwlAtrEnr;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlAtrRepositoryCustom;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional
public class TbDnCrwlAtrRepositoryCustomImpl implements TbDnCrwlAtrRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;

	private QTbDnCrwlAtr tbDnCrwlAtr = QTbDnCrwlAtr.tbDnCrwlAtr;	// D사 기준 속성
	private QTbDnCrwlAtrEnr tbDnCrwlAtrEnr = QTbDnCrwlAtrEnr.tbDnCrwlAtrEnr;	// D사기준 속성 에누리 매핑
	private QGoodsAttribute att = QGoodsAttribute.goodsAttribute;	// 에누리 속성
	private QGoodsAttributeElement attEl = QGoodsAttributeElement.goodsAttributeElement;	// 에누리 속성원

	@Override
	@JsonCreator
	public List<DnAttDto> findByCateCdStartsWithAndName(String category, String dnAtrNm, int enrAtrId, int enrAtrDtlId) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(elocEm);

		return jpaQueryFactory
				.select(Projections.constructor(DnAttDto.class,
						tbDnCrwlAtr.cateCd,
						tbDnCrwlAtr.dnAtrId,
						tbDnCrwlAtr.dnAtrNm,
						tbDnCrwlAtr.dnAtr,
						att.attributeId,
						att.galleryAttributeNm,
						attEl.id.attributeElementId,
						attEl.attributeElement,
						tbDnCrwlAtrEnr.enrAtrDtlRngVlu,
						att.useClassCode
						))
				.from(tbDnCrwlAtr)
				.innerJoin(tbDnCrwlAtr.attEnrs, tbDnCrwlAtrEnr)
				.leftJoin(att).on(tbDnCrwlAtrEnr.id.enrAtrId.eq(att.attributeId))
				.leftJoin(attEl).on(tbDnCrwlAtrEnr.id.enrAtrId.eq(attEl.id.attributeId)
									.and(tbDnCrwlAtrEnr.id.enrAtrDtlId.eq(attEl.id.attributeElementId)))
				.where(likeDnAtrNm(dnAtrNm), eqEnrAtrId(enrAtrId), eqEnrAtrDtlId(enrAtrDtlId),
					tbDnCrwlAtr.cateCd.like(category + "%")
					.and(tbDnCrwlAtr.useYn.eq("Y")))
				.distinct()
				.orderBy(tbDnCrwlAtr.dnAtrId.asc())
				.fetch();
	}

	// 다나와 속성명으로 조회
	private BooleanExpression likeDnAtrNm(String dnAtrNm) {
		return "".equals(dnAtrNm) ? null : tbDnCrwlAtr.dnAtrNm.like("%" + dnAtrNm + "%");
	}

	// 에누리 속성 매칭
	private BooleanExpression eqEnrAtrId(int enrAtrId) {
		return enrAtrId == 0 ? null : tbDnCrwlAtrEnr.id.enrAtrId.eq(enrAtrId);
	}

	// 에누리 속성원 매칭
	private BooleanExpression eqEnrAtrDtlId(int enrAtrDtlId) {
		return enrAtrDtlId == 0 ? null : tbDnCrwlAtrEnr.id.enrAtrDtlId.eq(enrAtrDtlId);
	}

	@Override
	@Modifying
	public long updateUseYnBydnAtrIds(List<Integer> dnAtrIds, String userId) {
		return new JPAQueryFactory(elocEm)
				.update(tbDnCrwlAtr)
				.set(tbDnCrwlAtr.useYn, "N")
				.set(tbDnCrwlAtr.updDtm, new Timestamp(System.currentTimeMillis()))
				.set(tbDnCrwlAtr.updEmpId, userId)
				.where(tbDnCrwlAtr.dnAtrId.in(dnAtrIds))
				.execute();
	}
}