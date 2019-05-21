package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Equipe;

@Repository
public interface IEquipeDao extends JpaRepository<Equipe, Long> {

	@Query(value = "from Equipe where nom = :nomParam")
	public Equipe findByNom(@Param("nomParam") String nom);
	
}
