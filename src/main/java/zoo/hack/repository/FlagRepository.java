package zoo.hack.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zoo.hack.dao.FlagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlagRepository extends CrudRepository<FlagEntity,Long> {

    @Query("Select a from FlagEntity a where a.name like %:keywords% or a.location like %:keywords% or a.phoneNumber like %:keywords% or a.licensePlateNumer like %:keywords%")
    List<FlagEntity> searchByKeywords(@Param("keywords") String keywords);
    
}