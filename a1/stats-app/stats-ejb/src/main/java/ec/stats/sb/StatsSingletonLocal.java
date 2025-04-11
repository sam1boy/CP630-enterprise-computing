package ec.stats.sb;

import javax.ejb.Local;

import ec.stats.Statistics;

@Local
public interface StatsSingletonLocal
{
    public void addData(Double x);
    public int getCount();
    public Statistics stats();
    public void saveModel();
}