package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "attributeId")
public class GoodsAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id")
	private int attributeId;
	private String galleryAttributeNm;	//전시용 속성명
	private String manageAttributeNm;	//관리용 속성명
	private String delYn;
	private String useClassCode;

	@OneToMany(mappedBy = "att", cascade = CascadeType.ALL)
	private Set<GoodsCategoryAttribute> cateAtts;

	@OneToMany(mappedBy = "att", cascade = CascadeType.ALL)
	private Set<GoodsAttributeElement> attEls;

}
