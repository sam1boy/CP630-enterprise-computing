package ec.stats.sb;

import javax.ejb.Remote;

@Remote
public interface StatsStatefulRemote
{
    public void insertData(Double x);
    public void createModel();
    public String getStats();
} 