package smarthomestay.betest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smarthomestay.betest.model.MstEmployee;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<MstEmployee, Long> {
    @Query(value = "select mb.* from mst_employee mb where mb.employee_id = :employeeId",nativeQuery = true)
    MstEmployee findByEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "select mu.* from mst_employee mu where lower(mu.employee_email)= lower(:employeeEmail)",nativeQuery = true)
    MstEmployee findByEmail(@Param("employeeEmail") String employeeEmail);

    @Query(value = "select mb.* from mst_employee mb order by mb.employee_id asc ",nativeQuery = true)
    List<MstEmployee> findAll();
}
