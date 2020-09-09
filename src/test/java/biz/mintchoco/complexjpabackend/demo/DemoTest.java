package biz.mintchoco.complexjpabackend.demo;

import biz.mintchoco.complexjpabackend.entity.Master;
import biz.mintchoco.complexjpabackend.entity.Subtype;
import biz.mintchoco.complexjpabackend.service.DemoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("JPA 데모 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    private DemoService demoService;

    @BeforeEach
    public void init() {
        demoService.init();
    }

    @DisplayName("Master 데이터 생성 및 조회 테스트")
    @Test
    public void demo_test_01() {
        final Master dataMaster = createMaster();

        //dataMaster 의 id 항목에 값이 생성된다.
        final Master persistMaster = demoService.saveMaster(dataMaster);

        assertThat(persistMaster, is(notNullValue()));
        assertThat(persistMaster.getId(), is(notNullValue()));
        assertThat(persistMaster.getSubtype(), is(nullValue()));

        Assertions.assertThat(persistMaster)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataMaster);
    }

    @DisplayName("Subtype 조회시 Master 정보 조회 테스트")
    @Test
    public void demo_test_02() {
        final Master dataMaster = createMaster();
        final Subtype dataSubtype = createSubtype(dataMaster, null);

        //dataSubtype 의 id 항목에 값이 생성된다.
        final Subtype persistSubtype = demoService.saveSubtype(dataSubtype);

        assertThat(persistSubtype, is(notNullValue()));
        assertThat(persistSubtype.getId(), is(notNullValue()));
        assertThat(persistSubtype.getMaster(), is(notNullValue()));

        Assertions.assertThat(persistSubtype)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataSubtype);
    }

    @DisplayName("Master 조회시 Subtype 정보 조회 테스트")
    @Test
    public void demo_test_03() {
        final Master dataMaster = createMaster();
        final Subtype dataSubtype = createSubtype(dataMaster, null);

        //dataSubtype 의 id 항목에도 값이 생성된다.
        final Master persistMaster = demoService.saveSubtypeReturnMaster(dataSubtype);

        assertThat(persistMaster, is(notNullValue()));
        assertThat(persistMaster.getId(), is(notNullValue()));
        assertThat(persistMaster.getSubtype(), is(nullValue()));

        Assertions.assertThat(persistMaster)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataMaster);
    }

    @DisplayName("Subtype 저장시 Master, Subtype, Metadata 동시 저장 테스트")
    @Test
    public void demo_test_04() {
        final Master dataMaster = createMaster();
        final Map<String, Object> dataMetadata = createMetadta();
        final Subtype dataSubtype = createSubtype(dataMaster, dataMetadata);

        //dataSubtype 의 id 항목에 값이 생성된다.
        final Subtype persistSubtype = demoService.saveSubtype(dataSubtype);

        assertThat(persistSubtype, is(notNullValue()));
        assertThat(persistSubtype.getId(), is(notNullValue()));
        assertThat(persistSubtype.getMaster(), is(notNullValue()));
        assertThat(persistSubtype.getMetadata().keySet().size(), greaterThan(0));

        Assertions.assertThat(persistSubtype)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataSubtype);
    }

    @DisplayName("Metadata 삭제 테스트")
    @Test
    public void demo_test_05() {
        final Master dataMaster = createMaster();
        final Map<String, Object> dataMetadata = createMetadta();
        final Subtype dataSubtype = createSubtype(dataMaster, dataMetadata);

        //dataSubtype 의 id 항목에 값이 생성된다.
        demoService.saveSubtype(dataSubtype);

        //subtype update
        dataSubtype.clearMetadata();
        final Subtype persistSubtype = demoService.saveSubtype(dataSubtype);

        assertThat(persistSubtype, is(notNullValue()));
        assertThat(persistSubtype.getMetadata().keySet().size(), is(0));

        /**
         * Subtype 인스턴스의 클래스 타입이 달라서 아래의 테스트 코드에서는 오류가 발생한다.
         * hibernate 캐시에서 조회한 데이터와 DB 에서 조회한 데이터의 클래스 타입에 차이가 있다.
         * ================================================================================
         * Top level actual and expected objects differ:
         * - actual value   : biz.mintchoco.complexjpabackend.entity.Subtype (Subtype$HibernateProxy$4zLTlLhj@6aa31c25)
         * - expected value : biz.mintchoco.complexjpabackend.entity.Subtype (Subtype@3442c929)
         */
        /* Assertions.assertThat(persistSubtype)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataSubtype); */
    }

    private Master createMaster() {
        return Master.builder()
                .name("샘플테스트")
                .content1("JPA CRUD 샘플 테스트")
                .content2("JPQL 샘플 테스트")
                .etc1("JUNIT 샘플 테스트")
                .etc1("OneToOne 샘플 테스트")
                .build();
    }

    private Subtype createSubtype(final Master master, final Map<String, Object> metadata) {
        final Subtype subtype = Subtype.builder()
                .master(master)
                .insertedDateTime(System.currentTimeMillis())
                .insertedUser("byung")
                .updatedDatetime(System.currentTimeMillis())
                .updatedUser("byung")
                .build();

        if (master != null) {
            subtype.setMaster(master);
        }
        if (metadata != null) {
            subtype.setMetadata(metadata);
        }

        return subtype;
    }

    private Map<String, Object> createMetadta() {
        final Map<String, Object> meta = new HashMap<>();
        meta.put("id", "gangil");
        meta.put("email", "gangil@test.zzz");
        meta.put("name", "gangil");
        meta.put("family-name", "E");
        meta.put("birth-year", 2001);
        meta.put("visit-count", 291);
        return meta;
    }
}
