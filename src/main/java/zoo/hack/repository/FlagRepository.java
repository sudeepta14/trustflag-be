package zoo.hack.repository;

import zoo.hack.dao.FlagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagRepository extends CrudRepository<FlagEntity,Long> {
}