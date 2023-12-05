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
        System.out.println();
        NVector a = new NVector(1,2,3);
        NVector b = new NVector(3,2,1);
        NVector aCb = a.cross(b);
        System.out.println();
        System.out.println("(1,2,3) cross (3,2,1)");
        System.out.printf("x=%.2f, y=%.2f, z=%.2f\n", aCb.get(0).getValue(), aCb.get(1).getValue(),aCb.get(2).getValue());
        System.out.println();
        Scalar t = new Scalar(Math.PI);
        Scalar h = t.cos();
        System.out.printf("cos(pi) = %.2f , derivative: %.2f", h.getValue(), h.diff(t).getValue());
    }
}
