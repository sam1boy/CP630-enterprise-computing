package ec.stats.sb;

import javax.ejb.Remote;

@Remote
public interface StatsStatelessRemote
{
    public int getCount();
    public Double getMin();
    public Double getMax();
    public Double getMean();
    public Double getSTD();
    public String toString();
}