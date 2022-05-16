public class ComplexNumber {
    private float a;
    private float b;
    public ComplexNumber(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public ComplexNumber add (float a, float b) {
        this.a += a;
        this.b += b;
        return this;
    }

    public ComplexNumber subtract(float a, float b) {
        return add(-a, -b);
    }

    public ComplexNumber neg() {
        a *= -1;
        b *= -1;
        return this;
    }

    public double dotProduct(float a, float b) {
        return this.a * a + this.b * b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }
}
