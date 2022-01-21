package com.enuri.gm.jca.repository.eloc.custom.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enuri.gm.jca.model.eloc.QTbDnCrwlAtrEnr;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlAtrEnrRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional
public class TbDnCrwlAtrEnrRepositoryCustomImpl implements TbDnCrwlAtrEnrRepositoryCustom {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;
	private QTbDnCrwlAtrEnr atrEnr = QTbDnCrwlAtrEnr.tbDnCrwlAtrEnr; // D사속성과 에누리 속성 매칭

	@Override
	@Modifying
	public long updateEnIdByDnAtrId(List<Map<String, Object>> dnAtrIds, String updEmpId) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(elocEm);
		long i = 0;

		for (Map<String, Object> map : dnAtrIds) {
			int dnAtrId = Integer.parseInt(map.get("dnAtrId").toString());
			int attId = Integer.parseInt(map.get("attId").toString());
			int attElId = Integer.parseInt(map.get("attElId").toString());
			String enrAtrDtlRngVlu = map.get("enrAtrDtlRngVlu").toString();

			jpaQueryFactory.update(atrEnr)
				.set(atrEnr.updDtm, new Timestamp(System.currentTimeMillis()))
				.set(atrEnr.updEmpId, updEmpId)
				.set(atrEnr.id.enrAtrId, attId)
				.set(atrEnr.id.enrAtrDtlId, attElId)
				.set(atrEnr.enrAtrDtlRngVlu, enrAtrDtlRngVlu)
				.where(atrEnr.id.dnAtrId.eq(dnAtrId))
				.execute();

			i++;
		}

		return i;
	}


}