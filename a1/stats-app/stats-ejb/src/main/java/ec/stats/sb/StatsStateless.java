package ec.stats.sb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.stats.StatsSummary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class StatsStateless implements StatsStatelessRemote, StatsStatelessLocal
{
    public static final String MODEL_SAVE_PATH = "C:/enterprise/tmp/model/stats.bin";
    public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

    @EJB private StatsSingletonLocal statsgt;

    private StatsSummary sm = null;

    public StatsStateless(){}

    public StatsSummary LoadModel()
    {
        StatsSummary sm_t;
        try 
        {
            FileInputStream fileIn = new FileInputStream(MODEL_SAVE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sm_t = (StatsSummary)in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Stats summary not found!!");
            c.printStackTrace();
            return null;
        }
        LOGGER.info("Model read: \n" + sm_t.toString());
        return sm_t;
    }

    @Override
    public int getCount()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.getCount();
        else return 0;
    }

    @Override
    public Double getMin()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.getMin();
        else return 0d;
    }

    @Override
    public Double getMax()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.getMax();
        else return 0d;
    }

    @Override
    public Double getMean()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.getMean();
        else return 0d;
    }

    @Override
    public Double getSTD()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.getSTD();
        else return 0d;
    }

    @Override
    public String toString()
    {
        if(sm == null) sm = LoadModel();
        if(sm != null)
            return sm.toString();
        else
            return "\nFailed to load model!!\n";
    }
}