package ec.stats.sb;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import ec.stats.StatsSummary;

import javax.ejb.LocalBean;

@Stateful
@LocalBean
public class StatsStateful implements StatsStatefulRemote, StatsStatefulLocal
{
    @EJB
    private StatsStatelessLocal sbsl;
    
    @EJB
    private StatsSingletonLocal ssbs;

    //insert data into singleton session bean
    @Override
    public void insertData(Double x)
    {
        ssbs.addData(x);
    }

    //create model in singleton session bean
    @Override
    public void createModel()
    {
        ssbs.saveModel();
    }

    //get stats from singleton session bean
    @Override
    public String getStats()
    {
        StatsSummary sm = sbsl.LoadModel();
        String sts = 
            "Count: " + sm.getCount() + "<br>\n" +
            "Min: " + sm.getMin() + "<br>\n" +
            "Max: " + sm.getMax() + "<br>\n" +
            "Mean: " + sm.getMean() + "<br>\n" +
            "STD: " + sm.getSTD() + "<br>\n";
        return sts;
    }
}