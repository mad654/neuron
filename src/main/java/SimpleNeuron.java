import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @TODO Currently we pull values -> refator to push values?
 */
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
        // if we act as input we have store the original value
        this.value = value;
    }

    public long value() {
        long current = 0;

        // if we act as input we have to return the original value
        if (this.input.size() == 0) {
            return applyThreshold(this.value);
        }

        // if we act as hidden and output we have to sum our inputs
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
