import java.util.ArrayList;
import java.util.List;
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

    @Test public void value_outputWithWightConnected_sends07408() {
        List<Long> numbers = new ArrayList();
        numbers.add(new Long(900)); // 0,9
        RandomNumberGenerator generator = new RandomNumberGeneratorStub(numbers);
        Neuron input = this.sut(generator);
        Neuron output = this.sut(generator);

        input.connectOutput(output);

        input.send(2000); // 2

        assertEquals(1800, output.value()); // 1,8
    }
    
    @Test public void value_valueAfterOutputErrorBack_valueIsDifferentThanBefore() {
        Neuron input = this.sut();
        Neuron output = this.sut();
        input.connectOutput(output);

        // first send a signal
        input.send(1000);
        long outputOrg = output.value();
        assertEquals(1000, outputOrg);

        // propagate error back
        output.onError(500);

        // send signal again, value should now be different
        input.send(1000);
        assertNotEquals(outputOrg, output.value());
    }

    // TODO use double instaead of long
    // TODO sigmod activator

    private Neuron sut() {
        List<Long> numbers = new ArrayList();
        numbers.add(new Long(1000));
        return this.sut(new RandomNumberGeneratorStub(numbers));
    }

    private Neuron sut(RandomNumberGenerator generator) {
        return new SimpleNeuron(generator);
    }
}
