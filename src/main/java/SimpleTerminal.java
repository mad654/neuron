public class SimpleTerminal implements Terminal {
    private Neuron neuron;
    private RandomNumberGenerator generator;
    private long weight;

    public SimpleTerminal (Neuron neuron, RandomNumberGenerator generator) {
        this.neuron = neuron;
        this.generator = generator;
        this.weight = generator.generate();
    }

    public long value() {
        return this.neuron.value() * this.weight / 1000;
    }

    public void onValueChanged() {
        this.neuron.onValueChanged();
    }

    public boolean equalsNeuron(Neuron other) {
        return this.neuron.equals(other);
    }
}
