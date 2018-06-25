import java.util.Iterator;
import java.util.List;

class RandomNumberGeneratorStub implements RandomNumberGenerator {

    private List<Long> numbers;
    private Iterator<Long> iterator;

    public RandomNumberGeneratorStub(List<Long> numbers) {
        this.numbers = numbers;
    }

    /**
     * generates a 10 digits random number
     */
    public long generate() {
        if (this.iterator == null || !iterator.hasNext()) {
            this.iterator = numbers.iterator();
        }

        if (!iterator.hasNext()) {
            return 1;
        }

        return iterator.next();
    }

}
