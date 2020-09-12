package com.company;

class Vector {
    private int x, y, z;

    // Constructor x, y, z -->
    Vector(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double getVectorLength(){
        return Math.sqrt(x^2 + y^2 + z^2);
    }

    int scalarMul(Vector iV){
        return x * iV.x + y *
                iV.y + z * iV.z;

    }

    Vector vectorMul(Vector iV){
        return new Vector(
                y * iV.z - z * iV.y,
                z * iV.x - x * iV.z,
                x * iV.y - y * iV.x
        );
    }

    double getAngleCos(Vector iV){
        int scalarMul = scalarMul(iV);
        double length = getVectorLength();
        double iVLength = iV.getVectorLength();

        return scalarMul / Math.abs(length) * Math.abs(iVLength);
    }

    Vector getVectorsSum(Vector iV){
        return new Vector(x + iV.x, y + iV.y, z + iV.z);
    }

    Vector getVectorsDiff(Vector iV){
        return new Vector(x - iV.x, y - iV.y, z - iV.z);
    }

    void printVector(){
        System.out.println("(" + x + ", " + y + ", " + z + ")");
    }

    static Vector[] getVectorsArray(int N){
        Vector[] result = new Vector[N];

        for (int n = 0; n < N; n++){
            result[n] = new Vector(
                    (int) (Math.random() * 15),
                    (int) (Math.random() * 15),
                    (int) (Math.random() * 15)
            );
        }

        return result;
    }
}
