public interface Neuron {
    public void connectInput(Neuron other);
    public void connectOutput(Neuron other);
    public void send(long value);
    public long value();
}
