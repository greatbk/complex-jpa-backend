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

    public Master findMaster(final long masterId) {
        return masterRepository.findById(masterId).orElse(null);
    }

    public void deleteMaster(final Master master) {
        masterRepository.delete(master);
    }

    public Subtype saveSubtype(Subtype subtype) {
        masterRepository.save(subtype.getMaster());
        subtypeRepository.save(subtype);
        return subtypeRepository.findById(subtype.getId()).orElse(null);
    }

    public Master saveSubtypeReturnMaster(Subtype subtype) {
        masterRepository.save(subtype.getMaster());
        subtypeRepository.save(subtype);
        return masterRepository.findById(subtype.getMaster().getId()).orElse(null);
    }

    public void deleteSubtype(final Subtype subtype) {
        subtypeRepository.delete(subtype);
    }

    public void init() {
        subtypeRepository.deleteAll();
        masterRepository.deleteAll();
    }
}
