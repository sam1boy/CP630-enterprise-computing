import ec.stats.ECStatistics;

import static org.junit.Assert.*;
import org.junit.Test;

public class StatisticsTest {

    @Test
    public void testStatisticsOperations() {
        ECStatistics stats = new ECStatistics();
        
        // test case when nothing is in arraylist
        assertEquals(0, stats.getCount());
        assertEquals(Double.POSITIVE_INFINITY, stats.getMin(), 0.0);
        assertEquals(Double.NEGATIVE_INFINITY, stats.getMax(), 0.0);
        assertEquals(0.0, stats.getMean(), 0.0);
        assertEquals(0.0, stats.getSTD(), 0.0);
        
        for (int i = 1; i <= 10; i++) {
        	stats.addData(i * 10.0);
        }
        
        // test case when there is data
        assertEquals(10, stats.getCount());
        assertEquals(10.0, stats.getMin(), 0.0);
        assertEquals(100.0, stats.getMax(), 0.0);
        assertEquals(55.0, stats.getMean(), 0.0001);
        assertEquals(28.722813232690143, stats.getSTD(), 0.0001);

        stats.stats();

        // test case after stats() runs
        assertEquals(10, stats.getCount());
        assertEquals(10.0, stats.getMin(), 0.0);
        assertEquals(100.0, stats.getMax(), 0.0);
        assertEquals(55.0, stats.getMean(), 0.0001);
        assertEquals(28.722813232690143, stats.getSTD(), 0.0001);
        
    }
}