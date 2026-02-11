package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
