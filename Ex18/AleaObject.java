public class AleaObject{
    public final int weight;

    public AleaObject(int min, int max) {
		weight = (int) (Math.Random()*(max - min) + min);
    }
}
