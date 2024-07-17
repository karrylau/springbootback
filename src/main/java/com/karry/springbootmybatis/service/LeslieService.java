package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.PopulationMatrix;
import com.karry.springbootmybatis.pojo.PopulationVector;
import com.karry.springbootmybatis.repository.PopulationMatrixRepository;
import com.karry.springbootmybatis.repository.PopulationVectorRepository;
import com.karry.springbootmybatis.pojo.PopulationMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeslieService {

    @Autowired
    private PopulationMatrixRepository matrixRepository;

    @Autowired
    private PopulationVectorRepository vectorRepository;

    public double[] applyLeslieAlgorithm() {
        List<PopulationVector> vectors = vectorRepository.findAll();
        List<PopulationMatrix> matrices = matrixRepository.findAll();

        int size = vectors.size();
        double[] populationVector = vectors.stream().mapToDouble(PopulationVector::getVectorValue).toArray();
        double[][] leslieMatrix = new double[size][size];

        for (PopulationMatrix matrix : matrices) {
            leslieMatrix[matrix.getMatrixRow()][matrix.getMatrixCol()] = matrix.getMatrixValue();
        }

        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = 0;
            for (int j = 0; j < size; j++) {
                result[i] += leslieMatrix[i][j] * populationVector[j];
            }
        }

        return result;
    }
}

