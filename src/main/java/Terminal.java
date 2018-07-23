public interface Terminal {
    public void onValueChanged();
    public long value();
    public void onError(long value);
    public boolean inputEquals(Neuron other);
    public boolean outputEquals(Neuron other);
}
