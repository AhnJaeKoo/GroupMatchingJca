package com.enuri.gm.jca.repository.eloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enuri.gm.jca.model.eloc.TbDnCrwlQa;
import com.enuri.gm.jca.repository.eloc.custom.TbDnCrwlQaRepositoryCustom;

@Repository
public interface TbDnCrwlQaRepository extends JpaRepository<TbDnCrwlQa, Long>, TbDnCrwlQaRepositoryCustom {
}