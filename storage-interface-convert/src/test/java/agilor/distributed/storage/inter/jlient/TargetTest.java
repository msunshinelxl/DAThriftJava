package agilor.distributed.storage.inter.jlient;

import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

import static org.junit.Assert.*;

/**
 * Created by LQ on 2015/8/27.
 */
public class TargetTest {

    public Target target =null;

    @Before
    public void setUp() throws Exception {
        Agilor agilor = new Agilor();
        Device device = agilor.first();
        target = device.fisrt(x -> x.getName().contains("CSD_002"));
    }

    @Test
    public void testHistory() throws Exception {
       //Val  target.history(Calendar.getInstance());




        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 7, 30, 16, 21, 16);

        Val val = target.history(calendar);
        System.out.println(val.toString());

    }

    @Test
    public void testHistories() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.set(2015,7,30,16,21,20);
        start.set(2015, 7, 30, 16, 21, 15);
        List<Val> list = target.histories(start, end);


        for(Val v:list)
        {
            System.out.println(v.toString());
        }

        //target.histories()

    }

    @Test
    public void testHistories1() throws Exception {

    }

    //此方法未实现
    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testWrite() throws Exception {

        target.write(new Val(145f));

    }


    public void write_lots_value() throws TException, InterruptedException {
        Random random = new Random();
        for(int i=0;i<1000;i++)
        {
            float v = random.nextFloat()*1000;
            System.out.println(v);
            target.write(new Val(v));

            Thread.sleep(1000);

        }
    }
}