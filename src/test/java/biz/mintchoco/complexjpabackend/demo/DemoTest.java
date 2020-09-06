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
        demoService.clear();
    }

    @DisplayName("Master 데이터 생성 및 조회 테스트")
    @Test
    public void demo_test_01() {
        final Master dataMaster = Master.builder()
                .name("샘플테스트")
                .content1("JPA CRUD 샘플 테스트")
                .content2("JPQL 샘플 테스트")
                .etc1("JUNIT 샘플 테스트")
                .build();

        //dataMaster 의 id 항목에도 값이 생성되어 들어간다.
        demoService.saveMaster(dataMaster);

        //DB에서 조회
        final Master persistMaster = demoService.findMaster(dataMaster);

        assertThat(persistMaster, is(notNullValue()));
        assertThat(persistMaster.getId(), is(notNullValue()));
        assertThat(persistMaster.getSubtype(), is(nullValue()));

        Assertions.assertThat(persistMaster)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataMaster);
    }

    @DisplayName("Master 및 Subtype 데이터 생성 및 조회 테스트")
    @Test
    public void demo_test_02() {
        final Master dataMaster = Master.builder()
                .name("샘플테스트")
                .content1("JPA CRUD 샘플 테스트")
                .content2("JPQL 샘플 테스트")
                .etc1("JUNIT 샘플 테스트")
                .build();

        final Subtype dataSubtype = Subtype.builder()
                .master(dataMaster)
                .insertedDatetime(System.currentTimeMillis())
                .insertedUser("byung")
                .updatedDatetime(System.currentTimeMillis())
                .updatedUser("byung")
                .build();

        //dataSubtype 의 id 항목에도 값이 생성되어 들어간다.
        final Subtype persistSubtype = demoService.saveSubtype(dataSubtype);

        assertThat(persistSubtype, is(notNullValue()));
        assertThat(persistSubtype.getId(), is(notNullValue()));
        assertThat(persistSubtype.getMaster(), is(notNullValue()));

        Assertions.assertThat(persistSubtype)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dataSubtype);
    }
}
