public class ComplexNumbers {
    private final double realPart;
    private final double imaginaryPart;

    public ComplexNumbers(double realPart, double imaginaryPart) {
        if (Double.isNaN(realPart) || Double.isNaN(imaginaryPart)) {
            throw new ArithmeticException();
        }
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public static void main(String[] args) {
        //send real part and imaginary part into constructor like parameters
        ComplexNumbers complexNumber1 = new ComplexNumbers(1, 2);
        System.out.println(complexNumber1.add(complexNumber1));
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public ComplexNumbers add(ComplexNumbers localComplexVariable) {
        return new ComplexNumbers(realPart + localComplexVariable.realPart,
                imaginaryPart + localComplexVariable.imaginaryPart);
    }

    @Override
    public boolean equals(Object objectVariable) {
        if (!(objectVariable instanceof ComplexNumbers)) {
            return false;
        }
        if (objectVariable == this) {
            return true;
        }
        ComplexNumbers localComplexVariable = (ComplexNumbers) objectVariable;
        return Double.compare(realPart, localComplexVariable.realPart) == 0
                && Double.compare(imaginaryPart,
                localComplexVariable.imaginaryPart) == 0;
    }

    @Override
    public int hashCode() {
        return (31 * (17 + Double.hashCode(realPart))
                + Double.hashCode(imaginaryPart));
    }

    @Override
    public String toString() {
        return ("(" + realPart + ((imaginaryPart < 0) ? "" : "+")
                + imaginaryPart + "i)");
    }

}