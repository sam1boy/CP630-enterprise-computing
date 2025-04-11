package ec.stats.sb;

import javax.ejb.Remote;

import ec.stats.Statistics;

@Remote
public interface StatsSingletonRemote
{
    public void addData(Double x);
    public int getCount();
    public Statistics stats();
    public void saveModel();
}