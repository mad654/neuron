public interface Neuron {
    public void connectInput(Neuron other);
//    public void connectInput(Terminal other);
    public void connectOutput(Neuron other);
    public void onValueChanged();
    public void send(long value);
    public void onError(long value);
    public long value();
}
