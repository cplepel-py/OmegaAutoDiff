package edu.hmc.math19;

public class Main {
    public static void main(String[] args) {
        Scalar x = new Scalar(2);
        Scalar y = new Scalar(3);
        Scalar z = new Scalar(5);
        Scalar xyz = x.scale(y).scale(z);
        Scalar f = xyz.add(x).add(y).add(z);
        Scalar g = x.power(new Scalar(2));
        Scalar w = x.add(y);
        System.out.printf("x=%.2f, y=%.2f, z=%.2f\n", x.getValue(), y.getValue(), z.getValue());
        System.out.printf("w=x+y = %.2f\n", w.getValue());
        System.out.printf("dw/dx = %.2f\n", w.diff(x).getValue());
        System.out.printf("dw/dy = %.2f\n", w.diff(y).getValue());
        System.out.printf("f=xyz+x+y+z = %.2f\n", f.getValue());
        System.out.printf("df/dx = %.2f\n", f.diff(x).getValue());
        System.out.printf("df/dy = %.2f\n", f.diff(y).getValue());
        System.out.printf("df/dz = %.2f\n", f.diff(z).getValue());
        System.out.printf("d/dx x^2 = %.2f", g.diff(x).getValue());
    }
}
