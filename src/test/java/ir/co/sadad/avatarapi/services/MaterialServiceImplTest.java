package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.providers.minio.FileStorageServiceProvider;
import ir.co.sadad.avatarapi.repositories.DefaultAvatarRepository;
import ir.co.sadad.avatarapi.repositories.MaterialRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@Log4j2
@DataMongoTest
@Import(MaterialServiceImpl.class)
class MaterialServiceImplTest  {


    @Autowired
    private MaterialServiceImpl materialService;
    private  MaterialRepository materialRepository;
    private  DefaultAvatarRepository defaultAvatarRepository;

    @MockBean
    private FileStorageServiceProvider fileStorageServiceProvider;

    @Test
    public void shouldReturnMaterials() {
        Flux<Material> materials = materialService.getAllMaterial();
        StepVerifier
                .create(materials)
                .expectNextCount(0)
                .verifyComplete();
    }

//    @Test
//    public void shouldThrowExceptionWhenInputOfGetMaterialFileIsEmpty()
//    {
//        Mono<InputStream> materialFile = materialService.getMaterialFile("", "");
//        StepVerifier.create(materialFile)
//                .expectError()
//                .verifyThenAssertThat();
//    }
}