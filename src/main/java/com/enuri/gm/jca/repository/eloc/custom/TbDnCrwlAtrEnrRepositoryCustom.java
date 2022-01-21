package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;
import java.util.Map;

public interface TbDnCrwlAtrEnrRepositoryCustom {

	public long updateEnIdByDnAtrId(List<Map<String, Object>> dnAtrIds, String updEmpId);
}
