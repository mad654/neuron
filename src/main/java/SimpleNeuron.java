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

    public void connectOutput(Neuron other) {
        this.connectOutput((SimpleNeuron) other);
    }

    private void connectOutput(SimpleNeuron other) {
        if (this.isConnectedOutput(other)) {
            // TODO: throw exception or support multiple connections between same neurons?
            return;
        }

        Terminal terminal = new SimpleTerminal(this, other, this.generator);
        this.output.add(terminal);
        other.input.add(terminal);
    }

    private boolean isConnectedOutput(Neuron other) {
        List list = this.output;
        for (Iterator<Terminal> i = list.iterator(); i.hasNext();) {
            if (i.next().outputEquals(other)) {
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

