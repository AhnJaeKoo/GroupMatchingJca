package com.enuri.gm.jca.repository.eloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enuri.gm.jca.model.eloc.GoodsAttribute;
import com.enuri.gm.jca.repository.eloc.custom.GoodsAttributeRepositoryCustom;

@Repository
public interface GoodsAttributeRepository extends JpaRepository<GoodsAttribute, Integer>, GoodsAttributeRepositoryCustom {

}
