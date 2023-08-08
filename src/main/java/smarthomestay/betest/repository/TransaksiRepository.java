package smarthomestay.betest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smarthomestay.betest.model.MstTransaksi;

import java.util.List;

@Repository
public interface TransaksiRepository extends PagingAndSortingRepository<MstTransaksi, Long> {
    @Query(value = "select ms.* from mst_transaksi ms where ms.transaksi_id= :transaksiId order by ms.transaksi_id asc limit 1",nativeQuery = true)
    MstTransaksi findByTransaksiId(@Param("transaksiId") Long transaksiId);

    @Query(value = "select ms.* from mst_transaksi ms order by ms.transaksi_id asc ",nativeQuery = true)
    List<MstTransaksi> findAll();
}
