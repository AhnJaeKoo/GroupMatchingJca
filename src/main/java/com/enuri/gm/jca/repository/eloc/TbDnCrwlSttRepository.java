package com.enuri.gm.jca.repository.eloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enuri.gm.jca.model.eloc.TbDnCrwlStt;
import com.enuri.gm.jca.model.eloc.TbDnCrwlSttId;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlSttRepositoryCustom;

@Repository
public interface TbDnCrwlSttRepository extends JpaRepository<TbDnCrwlStt, TbDnCrwlSttId>, TbDnCrwlSttRepositoryCustom {

}