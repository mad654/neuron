public class SimpleTerminal implements Terminal {
    private Neuron input;
    private Neuron output;
    private RandomNumberGenerator generator;
    private long weight;

    public SimpleTerminal (Neuron input, Neuron output, RandomNumberGenerator generator) {
        this.input = input;
        this.output = output;
        this.generator = generator;
        this.weight = generator.generate();
    }

    public long value() {
        return this.input.value() * this.weight / 1000;
    }

    public void onValueChanged() {
        this.output.onValueChanged();
    }

    // todo remove??
    public boolean inputEquals(Neuron other) {
        return this.input.equals(other);
    }

    // todo remove??
    public boolean outputEquals(Neuron other) {
        return this.output.equals(other);
    }

    public void onError(long value) {
        this.weight = value * this.weight;
        this.input.onError(this.weight);
    }
}
