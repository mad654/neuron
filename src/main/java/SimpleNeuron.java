import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleNeuron implements Neuron {
    private RandomNumberGenerator generator;
    private List<Terminal> input;
    private List<Terminal> output;
    private long value = 0;

    public SimpleNeuron(RandomNumberGenerator generator) {
        this.generator = generator;
        this.input = new ArrayList();
        this.output = new ArrayList();
    }

    public void connectInput(Neuron other) {
        if (!this.isConnectedNeuron(this.input, other)) {
            this.input.add(new SimpleTerminal(other, this.generator));
            other.connectOutput(this);
        }
    }

    public void connectOutput(Neuron other) {
        if (!this.isConnectedNeuron(this.output, other)) {
            this.output.add(new SimpleTerminal(other, this.generator));
            other.connectInput(this);
        }
    }

    private boolean isConnectedNeuron(List list, Neuron other) {
        for (Iterator<Terminal> i = list.iterator(); i.hasNext();) {
            if (i.next().equalsNeuron(other)) {
                return true;
            }
        }

        return false;
    }

    public void send(long value) {
        this.value = value;
        this.notifyValueChanged();
    }

    public void onError(long value) {
        for (Iterator<Terminal> i= this.input.iterator(); i.hasNext();) {
            i.next().onError(value);
        }
    }


    private void notifyValueChanged() {
        for (Iterator<Terminal> i= this.output.iterator(); i.hasNext();) {
            i.next().onValueChanged();
        }
    }

    public void onValueChanged() {
        this.value = 0;

        for (Iterator<Terminal> i= this.input.iterator(); i.hasNext();) {
            this.value += i.next().value();
        }

        this.value = this.applyThreshold(this.value);
        this.notifyValueChanged();
    }

    private long applyThreshold(long current) {
        if (current < 1000) {
            return 0;
        }

        return current;
    }

    public long value() {
        return this.value;
    }
}

