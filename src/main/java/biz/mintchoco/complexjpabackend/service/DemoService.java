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
        masterRepository.save(master);
        return masterRepository.findById(master.getId()).orElse(null);
    }

    public Subtype saveSubtype(Subtype subtype) {
        if (subtype.getMaster() != null) {
            masterRepository.save(subtype.getMaster());
        }
        subtypeRepository.save(subtype);
        return subtypeRepository.findById(subtype.getId()).orElse(null);
    }

    public Master saveSubtypeReturnMaster(Subtype subtype) {
        if (subtype.getMaster() != null) {
            masterRepository.save(subtype.getMaster());
        }
        subtypeRepository.save(subtype);
        return masterRepository.findById(subtype.getMaster().getId()).orElse(null);
    }

    public void clear() {
        subtypeRepository.deleteAll();
        masterRepository.deleteAll();
    }
}
