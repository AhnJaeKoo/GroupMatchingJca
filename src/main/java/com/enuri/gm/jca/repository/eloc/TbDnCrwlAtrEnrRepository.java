package com.enuri.gm.jca.repository.eloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enuri.gm.jca.model.eloc.TbDnCrwlAtrEnr;
import com.enuri.gm.jca.model.eloc.TbDnCrwlAtrEnrId;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlAtrEnrRepositoryCustom;

@Repository
public interface TbDnCrwlAtrEnrRepository extends JpaRepository<TbDnCrwlAtrEnr, TbDnCrwlAtrEnrId>, TbDnCrwlAtrEnrRepositoryCustom {
}
