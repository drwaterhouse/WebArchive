package com.db34.WebArchive.domains.db;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;

// based on sample at https://dzone.com/articles/spring-boot-and-spring-jdbc-with-h2

//@Data
//@AllArgsConstructor
//@NoArgsConstructor(force = true)
//@RequiredArgsConstructor
//@Builder
public class ArchiveItem {
	/** unique ID of item */
    @NotNull private Long id;
    
    /** name for this item, not unique. Human readable, optional */
    private String name;
    
    /** descripion for this item. Human readable, optional */
    private String description;
    
    /** List of key-value pairs */
    private HashMap<String, String> infos;

    public ArchiveItem() {
    	super();
    }

    public ArchiveItem(Long id) {
    	super();
    	this.id = id;
    }

    public ArchiveItem(Long id, String name, String descn, HashMap<String, String> infos) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.description=descn;
    	this.infos = infos;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public void setDescription(String description) {
    	this.name = description;
    }
    
    public HashMap<String, String>  getInfos() {
    	return this.infos;
    }
    
    public void setInfos(HashMap<String, String>  infos) {
    	this.infos = infos;
    }
}


