package ec.stats;

import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.stats.sb.*;

public class StatsSBClientEar {
    private static final Logger LOGGER = Logger.getLogger(StatsSBClientEar.class.getName());

    public static void main(String[] args) throws NamingException
    {
        StatsSingletonRemote sbsingleton = (StatsSingletonRemote)InitialContext.doLookup("stats-ear/stats-ejb/StatsSingleton!ec.stats.sb.StatsSingletonRemote");
        for(Double i = 1d ; i <= 10 ; i++)
        {
            sbsingleton.addData(i);
        }
        sbsingleton.saveModel();

        StatsStatelessRemote sbStateless = (StatsStatelessRemote)InitialContext.doLookup("stats-ear/stats-ejb/StatsStateless!ec.stats.sb.StatsStatelessRemote");
        LOGGER.info("Count: " + sbStateless.getCount());
        LOGGER.info("Mean: " + sbStateless.getMean());
        
        StatsStatefulRemote sbStateful = (StatsStatefulRemote)InitialContext.doLookup("stats-ear/stats-ejb/StatsStateful!ec.stats.sb.StatsStatefulRemote");
        for(Double i = 11d ; i <= 100 ; i++)
            sbStateful.insertData(i);
        sbStateful.createModel();
        LOGGER.info("\n" + sbStateful.getStats());
    }
}