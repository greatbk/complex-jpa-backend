package biz.mintchoco.complexjpabackend.service;

import biz.mintchoco.complexjpabackend.entity.Master;
import biz.mintchoco.complexjpabackend.entity.Subtype;
import biz.mintchoco.complexjpabackend.repository.MasterRepository;
import biz.mintchoco.complexjpabackend.repository.SubtypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DemoService {

    private MasterRepository masterRepository;

    private SubtypeRepository subtypeRepository;

    public DemoService(MasterRepository masterRepository, SubtypeRepository subtypeRepository) {
        this.masterRepository = masterRepository;
        this.subtypeRepository = subtypeRepository;
    }

    public Master saveMaster(Master master) {
        return masterRepository.save(master);
    }

    public Master findMaster(Master master) {
        return findMaster(master.getId());
    }

    public Master findMaster(Long id) {
        return masterRepository.findById(id).orElse(null);
    }

    public Subtype findSubtype(Subtype subtype) {
        return findSubtype(subtype.getId());
    }

    public Subtype findSubtype(Long id) {
        return subtypeRepository.findById(id).orElse(null);
    }

    public Subtype saveSubtype(Subtype subtype) {
        if (subtype != null) {
            if (subtype.getMaster() != null) {
                masterRepository.save(subtype.getMaster());
            }
            subtypeRepository.save(subtype);

            return subtypeRepository.findById(subtype.getId()).orElse(null);
        }
        return subtype;
    }

    public void clear() {
        subtypeRepository.deleteAll();
        masterRepository.deleteAll();
    }
}
