package com.enuri.gm.jca.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.enuri.gm.jca.core.template.engine.Velocity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DnCrawlingService {

	@PersistenceContext(unitName = "eloc")
	private EntityManager elocEm;

	@Value("${templates.velocity-path}")
	private String templatePath;

	/**
	  * @description : 프로시저 호출로 일괄 모델 생성
	  * @param : qaIds(qaId, modelNo), 사용자ID
	  * @return : 성공건수 (-1은 실패)
	  */
	public int createModelByQaId(List<Map<String, Object>> qaIds, String userId) {
		StringBuffer xmlString = new StringBuffer();
		String workId = "           <work_id>%s</work_id>\r\n".formatted(userId);

		qaIds.forEach(map -> {
			xmlString.append(Velocity.getTemplate(templatePath + "createModelByQaId.vm", map) + "\r\n");
		});

		xmlString.insert(0, "<root>\r\n");
		xmlString.append(workId);
		xmlString.append("</root>");

		StoredProcedureQuery spq = elocEm.createStoredProcedureQuery("dbo.up_dn_crwl_prcs")
			.registerStoredProcedureParameter("par_xml", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("affected_cnt", Integer.class, ParameterMode.OUT)
			.setParameter("par_xml", xmlString.toString());
		spq.execute();
		return (int) spq.getOutputParameterValue("affected_cnt");
	}
}