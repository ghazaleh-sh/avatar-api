package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.StickerDto;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * service for materials
 * <pre>
 *     contains CRUD for materials
 * </pre>
 */
public interface MaterialService {


    /**
     * service for save materials in batch form
     * <pre>
     *     this service changes during develop :
     *     there is no service for update , this service will delete everything and then recreate.
     * </pre>
     *
     * @param materialRequestDtoFlux list of materials
     * @return if everything goes well return true
     */
    Mono<Boolean> saveMaterials(MaterialRequestDto materialRequestDtoFlux, String ssn);

    /**
     * service for save file for PWA , it is new service and can be crete in Material Service
     *
     * @param file file
     * @param ssn  of admin
     * @return is uploaded ?
     */
    Mono<Boolean> savePWAMaterialFile(String ssn, String file);

    /**
     * get all material of avatars
     *
     * @return list of materials
     */
    Flux<MaterialsResponseDto> getAllMaterial();

    /**
     * get all material for panel services
     *
     * @return list of material for panel
     */
    Flux<MaterialsResponseDto> getPanelMaterial();

    /**
     * get material based on id
     *
     * @param ssn ssn of user for check authorization
     * @param id  id of material
     * @return material
     */
    Mono<MaterialsResponseDto> getMaterial(String ssn, String id);

    /**
     * get material file from MinIO server
     *
     * @param fileName
     */
    Mono<byte[]> getMaterialFile(String fileName);

    Mono<byte[]> getPanelMaterialFile(String fileName);

    /**
     * service for get formula ,
     * <pre>
     *     this service will return formula based on col , row and key
     *     used for change input to general input
     * </pre>
     *
     * @param row         row in PWA - mostly enum of key
     * @param col         col in PWA - mostly order
     * @param materialKey key
     * @return fomula
     */
    StickerDto getFormula(Integer row, Integer col, MaterialKey materialKey);


}
