package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.TierImage;

public interface TierImageRepository extends CrudRepository<TierImage,Long> {
    TierImage findByImgSource(String imgSource);
}
