package com.sometimes.sometimesbe.repository;

import com.sometimes.sometimesbe.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
