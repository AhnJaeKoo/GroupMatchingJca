package com.enuri.gm.jca.repository.eloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enuri.gm.jca.model.eloc.TbDnCrwlAtr;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlAtrRepositoryCustom;

@Repository
public interface TbDnCrwlAtrRepository extends JpaRepository<TbDnCrwlAtr, Integer>, TbDnCrwlAtrRepositoryCustom {

}
