// TODO: split into INPUT/OUTPUT Neuron ??
public interface Neuron {
    public void connectOutput(Neuron other);
    public void onValueChanged();
    public void send(long value);
    public void onError(long value);
    public long value();
}
