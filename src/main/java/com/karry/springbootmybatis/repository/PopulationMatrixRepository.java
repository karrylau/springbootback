package com.karry.springbootmybatis.repository;


import com.karry.springbootmybatis.pojo.PopulationMatrix;
import com.karry.springbootmybatis.pojo.PopulationVector;
import com.karry.springbootmybatis.pojo.PopulationMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopulationMatrixRepository extends JpaRepository<PopulationMatrix, Long> {
    List<PopulationMatrix> findByMatrixRowOrderByMatrixCol(int matrixRow);
}

