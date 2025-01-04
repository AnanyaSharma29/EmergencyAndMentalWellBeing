package com.EmergencyAndMentalWellBeing.Backend.Repository;
import com.EmergencyAndMentalWellBeing.Backend.Model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
}
