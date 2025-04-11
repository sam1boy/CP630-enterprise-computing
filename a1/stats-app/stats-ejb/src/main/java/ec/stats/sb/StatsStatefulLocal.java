package ec.stats.sb;

import javax.ejb.Local;

@Local
public interface StatsStatefulLocal
{
    public void insertData(Double x);
    public void createModel();
    public String getStats();
}