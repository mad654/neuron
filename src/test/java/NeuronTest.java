import org.junit.Test;
import static org.junit.Assert.*;

public class NeuronTest {
    @Test public void value_oneInputWith0_return0() {
        Neuron input = this.sut();
        Neuron output = this.sut();
        input.connectOutput(output);

        input.send(0);

        assertEquals(0, output.value());
    }

    @Test public void value_oneInputWith500_return0() {
        Neuron input = this.sut();
        Neuron output = this.sut();
        input.connectOutput(output);

        input.send(500);

        assertEquals(0, output.value());
    }

    @Test public void value_twoInputWith500_returns1000() {
        Neuron input1 = this.sut();
        Neuron input2 = this.sut();
        Neuron output = this.sut();
        input1.connectOutput(output);
        input2.connectOutput(output);

        input1.send(1000);
        input2.send(1000);

        assertEquals(2000, output.value());
    }

    @Test public void value_oneInputTwoOutputWith1000_bothOutputReturns1000() {
        Neuron input = this.sut();
        Neuron output1 = this.sut();
        Neuron output2 = this.sut();
        input.connectOutput(output1);
        input.connectOutput(output2);

        input.send(1000);

        assertEquals(1000, output2.value());
        assertEquals(1000, output1.value());
    }

    @Test public void value_inputChangedFrom1000To500_outputReturns0() {
        Neuron input = this.sut();
        Neuron output = this.sut();
        input.connectOutput(output);

        input.send(1000);
        input.send(500);

        assertEquals(0, output.value());
    }

    private Neuron sut() {
        return new SimpleNeuron();
    }
}
