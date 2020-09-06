package biz.mintchoco.complexjpabackend.repository;

import biz.mintchoco.complexjpabackend.entity.Master;
import biz.mintchoco.complexjpabackend.entity.Subtype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtypeRepository extends CrudRepository<Subtype, Long> {

    Subtype findByMaster(Master master);
}