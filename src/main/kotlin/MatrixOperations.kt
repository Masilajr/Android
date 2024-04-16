package org.example

object MatrixOperations {

    fun add(matrix1: Matrix, matrix2: Matrix): Matrix {
        require(matrix1.getRows() == matrix2.getRows() && matrix1.getCols() == matrix2.getCols()) { "Matrices must have the same dimensions for addition." }
        val result = Matrix(matrix1.getRows(), matrix1.getCols())
        for (i in 0 until matrix1.getRows()) {
            for (j in 0 until matrix1.getCols()) {
                result.setElement(i, j, matrix1.getElement(i, j) + matrix2.getElement(i, j))
            }
        }
        return result
    }

    fun subtract(matrix1: Matrix, matrix2: Matrix): Matrix {
        require(matrix1.getRows() == matrix2.getRows() && matrix1.getCols() == matrix2.getCols()) {
            "Matrices must be of the same dimension for subtraction"
        }
        val result = Matrix(matrix1.getRows(), matrix1.getCols())
        for (i in 0 until matrix1.getRows()) {
            for (j in 0 until matrix1.getCols()) {
                result.setElement(i, j, matrix1.getElement(i, j) - matrix2.getElement(i, j))
            }
        }
        return result
    }

    fun multiply(matrix1: Matrix, matrix2: Matrix): Matrix {
        if (matrix1.getCols() != matrix2.getRows()) {
            throw IllegalArgumentException("Number of columns in matrix1 must be equal to number of rows in matrix2 for multiplication.")
        }
        val result = Matrix(matrix1.getRows(), matrix2.getCols())

        for (i in 0 until matrix1.getRows()) {
            for (j in 0 until matrix2.getCols()) {
                var sum = 0.0
                for (k in 0 until matrix1.getCols()) {
                    sum += matrix1.getElement(i, k) * matrix2.getElement(k, j)
                }
                result.setElement(i, j, sum)
            }
        }
        return result

    }

    fun determinant(matrix: Matrix): Double {
        require(matrix.isSquare()) {
            "Determinant can only be calculated for a square matrix."
        }
        val rows = matrix.getRows()
        val cols = matrix.getCols()
        if (rows == 1) {
            return matrix.getElement(0, 0)
        }
        if (rows == 2) {
            return matrix.getElement(0, 0) * matrix.getElement(1, 1) -
                    matrix.getElement(0, 1) * matrix.getElement(1, 0)
        }
        var det = 0.0
        for (j in 0 until cols) {
            det += matrix.getElement(0, j) * cofactor(matrix, 0, j)
        }
        return det
    }

    private fun cofactor(matrix: Matrix, row: Int, col: Int): Double {
        val sign = if ((row + col) % 2 == 0) 1.0 else -1.0
        return sign.toDouble() * minorDeterminant(matrix, row, col)
    }

    private fun minorDeterminant(matrix: Matrix, row: Int, col: Int): Double {
        val minorData = mutableListOf<Double>()
        val rows = matrix.getRows()
        val cols = matrix.getCols()
        for (i in 0 until rows) {
            if (i != row) {
                for (j in 0 until cols) {
                    if (j != col) {
                        minorData.add(matrix.getElement(i, j))
                    }
                }
            }
        }
        // Calculate determinant of the minor matrix
        return if (rows == 2) {
            minorData[0] * minorData[3] - minorData[1] * minorData[2]
        } else {
            // For now, return 0 as a placeholder
            0.0
        }
    }


    private fun minor(matrix: Matrix, row: Int, col: Int): Double {
        val minorData = mutableListOf<Double>()
        val rows = matrix.getRows()
        val cols = matrix.getCols()
        for (i in 0 until rows) {
            if (i != row) {
                for (j in 0 until cols) {
                    if (j != col) {
                        minorData.add(matrix.getElement(i, j))
                    }
                }
            }
        }
        // Calculate determinant of the minor matrix
        return if (rows == 2) {
            minorData[0] * minorData[3] - minorData[1] * minorData[2]
        } else {
            // For now, return 0 as a placeholder
            0.0
        }
    }


    fun inverse(matrix: Matrix): Matrix {
        require(matrix.isSquare()) {
            "Inverse can only be calculated for a square matrix."
        }
        val det = determinant(matrix)
        require(det != 0.0) {
            "Inverse does not exist for a singular matrix."
        }
        val adjoint = adjoint(matrix)
        return adjoint.scalarMultiply(1.0 / det)
    }

    private fun adjoint(matrix: Matrix): Matrix {
        val adjointData = mutableListOf<MutableList<Double>>()
        val rows = matrix.getRows()
        val cols = matrix.getCols()
        for (i in 0 until rows) {
            val adjointRow = mutableListOf<Double>()
            for (j in 0 until cols) {
                val cofactor = if ((i + j) % 2 == 0) cofactor(matrix, i, j) else -cofactor(matrix, i, j)
                adjointRow.add(cofactor)
            }
            adjointData.add(adjointRow)
        }
        return Matrix(adjointData.size, adjointData[0].size).apply {
            initialize(adjointData)
        }.transpose()
    }
}