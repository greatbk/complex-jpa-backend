package biz.mintchoco.complexjpabackend.repository;

import biz.mintchoco.complexjpabackend.entity.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
}