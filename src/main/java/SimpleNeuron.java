import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleNeuron implements Neuron {
    private List<Neuron> input;
    private List<Neuron> output;
    private long value = 0;

    public SimpleNeuron() {
        this.input = new ArrayList();
        this.output = new ArrayList();
    }

    public void connectInput(Neuron other) {
        if (!this.input.contains(other)) {
            this.input.add(other);
            other.connectOutput(this);
        }
    }

    public void connectOutput(Neuron other) {
        if (!this.output.contains(other)) {
            this.output.add(other);
            other.connectInput(this);
        }
    }

    public void send(long value) {
        this.value = value;

        for (Iterator<Neuron> i = this.output.iterator(); i.hasNext();) {
            i.next().send(value());
         }
    }

    public long value() {
        long current = 0;

        if (this.input.size() == 0) {
            return applyThreshold(this.value);
        }

        for (Iterator<Neuron> i = this.input.iterator(); i.hasNext();) {
            current += i.next().value();
        }

        return applyThreshold(current);
    }

    private long applyThreshold(long current) {
        if (current < 1000) {
            return 0;
        }

        return current;
    }
}
