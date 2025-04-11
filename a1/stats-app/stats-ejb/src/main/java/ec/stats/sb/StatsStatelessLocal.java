package ec.stats.sb;

import javax.ejb.Local;

import ec.stats.StatsSummary;

@Local
public interface StatsStatelessLocal
{
    public int getCount();
    public Double getMin();
    public Double getMax();
    public Double getMean();
    public Double getSTD();
    public String toString();
    public StatsSummary LoadModel();
}