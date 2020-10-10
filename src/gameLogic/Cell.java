package gameLogic;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Cell {

    double getDoubleFromRange(double left, double right){
        double res = ThreadLocalRandom.current().nextDouble(left, right);

        if (res + Double.MIN_VALUE == right)
            res += ThreadLocalRandom.current().nextInt(0,2) == 0 ? Double.MIN_VALUE : 0;

        return res;
    }

    int getIntFromRange(int left, int right){
        return ThreadLocalRandom.current().nextInt(left, right + 1);
    }
}
