package smarthomestay.betest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smarthomestay.betest.model.MstAddFacility;

import java.util.List;

@Repository
public interface AddFacilityRepository extends PagingAndSortingRepository<MstAddFacility, Long> {
    @Query(value = "select ms.* from mst_add_facility ms where ms.facility_id= :facilityId order by ms.facility_id asc limit 1",nativeQuery = true)
    MstAddFacility findByFacilityId(@Param("facilityId") Long facilityId);

    @Query(value = "select ms.* from mst_add_facility ms order by ms.facility_id asc ",nativeQuery = true)
    List<MstAddFacility> findAll();
}
