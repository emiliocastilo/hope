package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormCardiovascularRiskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FormulaService {

    public String cardiovascularRiskCalculator(FormCardiovascularRiskDTO formCardiovascularRiskDTO) {
        String risk;
        double cholesterolTotal = 0.5 * formCardiovascularRiskDTO.getCholesterolTotal() / 20;
        double cholesterolHDL = formCardiovascularRiskDTO.getHdl() < 35 ? formCardiovascularRiskDTO.getHdl() * 1.5 : formCardiovascularRiskDTO.getHdl();
        cholesterolHDL = formCardiovascularRiskDTO.getHdl() >= 60 ? formCardiovascularRiskDTO.getHdl() * 0.5 : cholesterolHDL;
        int riskLevel = -1;
        int row = getPositionPressure(formCardiovascularRiskDTO.getPad(), formCardiovascularRiskDTO.getPas());
        int column = getPositionCholesterol(cholesterolTotal, cholesterolHDL);

        if (row >= 0 && column >= 0) {
            if (formCardiovascularRiskDTO.getGender().equalsIgnoreCase("HOMBRE")) {
                if (formCardiovascularRiskDTO.isDiabetic()) {
                    riskLevel = getRiskLevelManDiabetic(formCardiovascularRiskDTO, riskLevel, row, column);
                } else {
                    riskLevel = getRiskLevelManNoDiabetic(formCardiovascularRiskDTO, riskLevel, row, column);
                }
            } else {
                if (formCardiovascularRiskDTO.isDiabetic()) {
                    riskLevel = getRiskLevelWomanDiabetic(formCardiovascularRiskDTO, riskLevel, row, column);
                } else {
                    riskLevel = getRiskLevelWomanNoDiabetic(formCardiovascularRiskDTO, riskLevel, row, column);
                }
            }
        }

        if (riskLevel >= 15) {
            risk = ">= 15%";
        } else if (riskLevel >= 10) {
            risk = "10-14%";
        } else if (riskLevel >= 5) {
            risk = "5-9%";
        } else if (riskLevel >= 0) {
            risk = "<5";
        } else {
            risk = "No hay resultado para los datos indicados";
        }

        return risk;
    }

    private int getRiskLevelWomanNoDiabetic(FormCardiovascularRiskDTO formCardiovascularRiskDTO, int riskLevel, int row, int column) {

        int[][] womanNoSmokerNoDiabetic65plus = {{8, 10, 12, 12, 16}, {7, 8, 10, 10, 13}, {5, 6, 7, 8, 10}, {5, 7, 8, 8, 11}, {3, 4, 5, 5, 7}};
        int[][] womanSmokerNoDiabetic65plus = {{10, 13, 16, 16, 21}, {9, 11, 13, 13, 17}, {6, 8, 10, 10, 13}, {7, 9, 10, 11, 14}, {4, 5, 6, 7, 9}};
        int[][] womanNoSmokerNoDiabetic55plus = {{9, 11, 13, 14, 18}, {7, 9, 11, 11, 15}, {5, 7, 8, 8, 11}, {6, 7, 9, 9, 12}, {4, 4, 5, 6, 7}};
        int[][] womanSmokerNoDiabetic55plus = {{11, 14, 17, 18, 23}, {9, 12, 14, 15, 19}, {7, 9, 11, 11, 14}, {7, 9, 11, 12, 15}, {5, 6, 7, 7, 9}};
        int[][] womanNoSmokerNoDiabetic45plus = {{6, 7, 9, 9, 12}, {5, 6, 7, 8, 10}, {4, 5, 6, 6, 7}, {4, 5, 6, 6, 8}, {3, 3, 4, 4, 5}};
        int[][] womanSmokerNoDiabetic45plus = {{8, 10, 12, 12, 16}, {6, 8, 10, 10, 13}, {5, 6, 7, 7, 10}, {5, 6, 8, 8, 10}, {3, 4, 5, 5, 6}};
        int[][] womanNoSmokerNoDiabetic35plus = {{3, 3, 4, 4, 5}, {2, 3, 3, 3, 4}, {2, 2, 2, 3, 3}, {2, 2, 3, 3, 3}, {1, 2, 2, 2, 2}};
        int[][] womanSmokerNoDiabetic35plus = {{3, 4, 5, 5, 7}, {3, 3, 4, 4, 5}, {2, 3, 3, 3, 4}, {2, 3, 3, 3, 4}, {2, 2, 2, 2, 3}};

        if (formCardiovascularRiskDTO.getAge() >= 65) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerNoDiabetic65plus[row][column];
            } else {
                riskLevel = womanNoSmokerNoDiabetic65plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 55) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanNoSmokerNoDiabetic55plus[row][column];
            } else {
                riskLevel = womanSmokerNoDiabetic55plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 45) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerNoDiabetic45plus[row][column];
            } else {
                riskLevel = womanNoSmokerNoDiabetic45plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 35) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerNoDiabetic35plus[row][column];
            } else {
                riskLevel = womanNoSmokerNoDiabetic35plus[row][column];
            }
        }
        return riskLevel;
    }

    private int getRiskLevelWomanDiabetic(FormCardiovascularRiskDTO formCardiovascularRiskDTO, int riskLevel, int row, int column) {

        int[][] womanNoSmokerDiabetic65plus = {{13, 17, 20, 21, 27}, {11, 14, 17, 18, 23}, {8, 11, 13, 13, 17}, {9, 11, 13, 14, 18}, {5, 7, 8, 9, 11}};
        int[][] womanSmokerDiabetic65plus = {{17, 22, 26, 27, 34}, {15, 18, 22, 23, 29}, {11, 14, 16, 17, 22}, {12, 15, 18, 18, 23}, {7, 9, 11, 11, 15}};
        int[][] womanNoSmokerDiabetic55plus = {{15, 19, 22, 23, 29}, {12, 16, 19, 19, 25}, {9, 12, 14, 14, 19}, {10, 12, 15, 15, 20}, {6, 8, 9, 9, 12}};
        int[][] womanSmokerDiabetic55plus = {{19, 24, 29, 30, 37}, {16, 20, 24, 25, 32}, {12, 15, 18, 19, 24}, {13, 16, 19, 20, 26}, {8, 10, 12, 12, 16}};
        int[][] womanNoSmokerDiabetic45plus = {{10, 13, 15, 16, 20}, {8, 11, 13, 13, 17}, {6, 8, 10, 10, 13}, {7, 8, 10, 10, 14}, {4, 5, 6, 6, 8}};
        int[][] womanSmokerDiabetic45plus = {{13, 17, 20, 21, 26}, {11, 14, 17, 17, 22}, {8, 10, 12, 13, 17}, {9, 11, 13, 14, 18}, {5, 7, 8, 8, 11}};
        int[][] womanNoSmokerDiabetic35plus = {{4, 5, 6, 7, 9}, {4, 4, 5, 6, 7}, {3, 3, 4, 4, 5}, {3, 4, 4, 4, 6}, {2, 2, 3, 3, 4}};
        int[][] womanSmokerDiabetic35plus = {{6, 7, 8, 9, 11}, {5, 6, 7, 7, 9}, {3, 4, 5, 5, 7}, {4, 5, 6, 6, 7}, {2, 3, 3, 4, 5}};

        if (formCardiovascularRiskDTO.getAge() >= 65) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerDiabetic65plus[row][column];
            } else {
                riskLevel = womanNoSmokerDiabetic65plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 55) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanNoSmokerDiabetic55plus[row][column];
            } else {
                riskLevel = womanSmokerDiabetic55plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 45) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerDiabetic45plus[row][column];
            } else {
                riskLevel = womanNoSmokerDiabetic45plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 35) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = womanSmokerDiabetic35plus[row][column];
            } else {
                riskLevel = womanNoSmokerDiabetic35plus[row][column];
            }
        }
        return riskLevel;
    }

    private int getRiskLevelManNoDiabetic(FormCardiovascularRiskDTO formCardiovascularRiskDTO, int riskLevel, int row, int column) {

        int[][] manNoSmokerNoDiabetic65plus = {{8, 14, 16, 22, 25}, {7, 13, 15, 20, 23}, {6, 10, 12, 16, 18}, {4, 8, 9, 12, 14}, {4, 8, 9, 12, 14}};
        int[][] manSmokerNoDiabetic65plus = {{12, 22, 25, 33, 38}, {11, 20, 23, 31, 35}, {9, 16, 19, 25, 29}, {7, 13, 15, 20, 23}, {7, 13, 15, 20, 23}};
        int[][] manNoSmokerNoDiabetic55plus = {{5, 9, 10, 14, 16}, {5, 8, 10, 13, 15}, {4, 7, 8, 10, 12}, {3, 5, 6, 8, 9}, {3, 5, 6, 8, 9}};
        int[][] manSmokerNoDiabetic55plus = {{8, 14, 17, 22, 25}, {7, 13, 15, 21, 23}, {6, 11, 12, 17, 19}, {5, 8, 10, 13, 15}, {5, 8, 10, 13, 15}};
        int[][] manNoSmokerNoDiabetic45plus = {{3, 6, 7, 9, 10}, {3, 5, 6, 8, 10}, {2, 4, 5, 7, 8}, {2, 3, 4, 5, 6}, {2, 3, 4, 5, 6}};
        int[][] manSmokerNoDiabetic45plus = {{5, 9, 11, 15, 17}, {5, 8, 10, 13, 15}, {4, 7, 8, 11, 12}, {3, 5, 6, 8, 10}, {3, 5, 6, 8, 10}};
        int[][] manNoSmokerNoDiabetic35plus = {{2, 4, 4, 6, 7}, {2, 3, 4, 5, 6}, {2, 3, 3, 4, 5}, {1, 2, 3, 3, 4}, {1, 2, 3, 3, 4}};
        int[][] manSmokerNoDiabetic35plus = {{3, 6, 7, 9, 11}, {3, 6, 6, 9, 10}, {3, 4, 5, 7, 8}, {2, 4, 4, 5, 6}, {2, 3, 4, 5, 6}};

        if (formCardiovascularRiskDTO.getAge() >= 65) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerNoDiabetic65plus[row][column];
            } else {
                riskLevel = manNoSmokerNoDiabetic65plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 55) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manNoSmokerNoDiabetic55plus[row][column];
            } else {
                riskLevel = manSmokerNoDiabetic55plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 45) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerNoDiabetic45plus[row][column];
            } else {
                riskLevel = manNoSmokerNoDiabetic45plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 35) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerNoDiabetic35plus[row][column];
            } else {
                riskLevel = manNoSmokerNoDiabetic35plus[row][column];
            }
        }
        return riskLevel;
    }

    private int getRiskLevelManDiabetic(FormCardiovascularRiskDTO formCardiovascularRiskDTO, int riskLevel, int row, int column) {

        int[][] manNoSmokerDiabetic65plus = {{11, 20, 23, 31, 35}, {10, 19, 22, 29, 32}, {8, 15, 18, 23, 27}, {6, 12, 14, 18, 21}, {6, 12, 14, 18, 21}};
        int[][] manSmokerDiabetic65plus = {{18, 31, 36, 46, 51}, {16, 29, 33, 43, 48}, {13, 24, 28, 36, 40}, {10, 19, 22, 29, 32}, {10, 18, 22, 29, 32}};
        int[][] manNoSmokerDiabetic55plus = {{7, 13, 15, 21, 23}, {7, 12, 14, 19, 22}, {5, 10, 11, 15, 17}, {4, 8, 9, 12, 14}, {4, 7, 9, 12, 14}};
        int[][] manSmokerDiabetic55plus = {{12, 21, 24, 32, 36}, {11, 19, 22, 30, 33}, {9, 16, 18, 24, 27}, {7, 12, 14, 19, 22}, {7, 12, 14, 19, 22}};
        int[][] manNoSmokerDiabetic45plus = {{5, 8, 10, 13, 15}, {4, 8, 9, 12, 14}, {4, 6, 7, 10, 11}, {3, 5, 6, 8, 9}, {3, 5, 6, 8, 9}};
        int[][] manSmokerDiabetic45plus = {{8, 1, 4, 16, 21, 24}, {7, 12, 15, 20, 22}, {6, 10, 12, 16, 18}, {4, 8, 9, 12, 14}, {4, 8, 9, 12, 14}};
        int[][] manNoSmokerDiabetic35plus = {{3, 6, 6, 9, 10}, {3, 5, 6, 8, 9}, {2, 4, 5, 6, 7}, {2, 3, 4, 5, 6}, {2, 3, 4, 5, 6}};
        int[][] manSmokerDiabetic35plus = {{5, 9, 10, 14, 16}, {4, 8, 9, 13, 15}, {4, 7, 8, 10, 12}, {3, 5, 6, 8, 9}, {3, 5, 6, 8, 9}};

        if (formCardiovascularRiskDTO.getAge() >= 65) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerDiabetic65plus[row][column];
            } else {
                riskLevel = manNoSmokerDiabetic65plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 55) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manNoSmokerDiabetic55plus[row][column];
            } else {
                riskLevel = manSmokerDiabetic55plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 45) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerDiabetic45plus[row][column];
            } else {
                riskLevel = manNoSmokerDiabetic45plus[row][column];
            }
        } else if (formCardiovascularRiskDTO.getAge() >= 35) {
            if (formCardiovascularRiskDTO.isSmoker()) {
                riskLevel = manSmokerDiabetic35plus[row][column];
            } else {
                riskLevel = manNoSmokerDiabetic35plus[row][column];
            }
        }
        return riskLevel;
    }

    private int getPositionCholesterol(double cholesterolTotal, double cholesterolHDL) {
        int position = -1;
        if (cholesterolTotal >= 7.2 || cholesterolHDL >= 280) {
            position = 4;
        } else if ((cholesterolTotal >= 6.2 && cholesterolTotal < 7.2) || (cholesterolHDL >= 240 && cholesterolHDL < 280)) {
            position = 3;
        } else if ((cholesterolTotal >= 5.2 && cholesterolTotal < 6.2) || (cholesterolHDL >= 200 && cholesterolHDL < 240)) {
            position = 2;
        } else if ((cholesterolTotal >= 4.1 && cholesterolTotal < 5.2) || (cholesterolHDL >= 160 && cholesterolHDL < 200)) {
            position = 1;
        } else if (cholesterolTotal < 4.1 || cholesterolHDL < 160) {
            position = 0;
        }

        return position;
    }

    private int getPositionPressure(int pas, int pad) {
        int position = -1;
        if (pas >= 160 && pad >= 100) {
            position = 0;
        } else if (pas >= 140 && pas < 160 && pad >= 90 && pad < 100) {
            position = 1;
        } else if (pas >= 130 && pas < 140 && pad >= 85 && pad < 90) {
            position = 2;
        } else if (pas >= 120 && pas < 130 && pad >= 80 && pad < 85) {
            position = 3;
        } else if (pas < 120 && pad < 80) {
            position = 4;
        }
        return position;
    }

}
