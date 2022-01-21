package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.common.util.CutStr;
import com.enuri.gm.common.util.StringUtil;
import com.enuri.gm.jca.model.eloc.GoodsAttribute;
import com.enuri.gm.jca.model.eloc.QGoodsAttribute;
import com.enuri.gm.jca.model.eloc.QGoodsAttributeElement;
import com.enuri.gm.jca.model.eloc.QGoodsCategoryAttribute;
import com.enuri.gm.jca.repository.eloc.custom.GoodsAttributeRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional
public class GoodsAttributeRepositoryCustomImpl implements GoodsAttributeRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;

	private QGoodsAttribute att = QGoodsAttribute.goodsAttribute;
	private QGoodsCategoryAttribute cateAtt = QGoodsCategoryAttribute.goodsCategoryAttribute;
	private QGoodsAttributeElement attEl = QGoodsAttributeElement.goodsAttributeElement;

	@Override
	public List<GoodsAttribute> findByCateCdStartsWithAndIdOrName(String category, String keyword) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(elocEm);
		category = category.replace("#", "");	// 페이지 처리시 링크가 #. 쓸데없이 붙는 값 제거

		return jpaQueryFactory
				.selectFrom(att)
				.innerJoin(att.attEls, attEl).fetchJoin()	// FK 대조하여 join..select절에
				//.innerJoin(cateAtt).on(att.attributeId.eq(cateAtt.id.attributeId)).fetchJoin()
				.innerJoin(att.cateAtts, cateAtt).fetchJoin()
				.where(eqIdOrAttributeNmLike(keyword),
					(cateAtt.id.category.like(category + "%")
							.or(cateAtt.id.category.eq(CutStr.cutStr(category, 4))))	// 소카기준 like or 중카
					.and(att.delYn.eq("N"))
					.and(attEl.delYn.eq("N")))
				.distinct()
				.orderBy(att.attributeId.asc(), cateAtt.id.category.desc())
				.fetch();
	}

	@Override
	public List<GoodsAttribute> findByCateCdStartsWithAndElIdOrElName(String category, String keyword) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(elocEm);
		category = category.replace("#", "");	// 페이지 처리시 링크가 #. 쓸데없이 붙는 값 제거

		List<GoodsAttribute> result = jpaQueryFactory
				.selectFrom(att)
				.innerJoin(att.attEls, attEl).fetchJoin()
				//.innerJoin(cateAtt).on(att.attributeId.eq(cateAtt.id.attributeId)).fetchJoin()
				.innerJoin(att.cateAtts, cateAtt).fetchJoin()
				.where(eqElIdOrElAttributeNmLike(keyword),
					(cateAtt.id.category.like(category + "%")
							.or(cateAtt.id.category.eq(CutStr.cutStr(category, 4))))
					.and(att.delYn.eq("N"))
					.and(attEl.delYn.eq("N")))
				.distinct()
				.orderBy(att.attributeId.asc(), attEl.id.attributeId.asc(), attEl.id.attributeElementId.asc())
				.fetch();

		return result;
	}

	//속성 ID, 전시용명으로 조회
	private BooleanExpression eqIdOrAttributeNmLike(String keyword) {
		if (keyword == "") {
			return null;
		}

		if (StringUtil.isNumber(keyword)) { // 숫자형이면
			return att.attributeId.eq(Integer.parseInt(keyword));
		} else {
			return att.galleryAttributeNm.like("%" + keyword + "%");
		}
	}

	// 속성원 ID, 이름으로 조회
	private BooleanExpression eqElIdOrElAttributeNmLike(String keyword) {
		if (keyword == "") {
			return null;
		}

		if (StringUtil.isNumber(keyword)) { // 숫자형이면
			return attEl.id.attributeElementId.eq(Integer.parseInt(keyword));
		} else {
			return attEl.attributeElement.like("%" + keyword + "%");
		}
	}
}
