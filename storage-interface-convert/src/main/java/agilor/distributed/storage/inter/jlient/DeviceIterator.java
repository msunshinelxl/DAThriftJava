package agilor.distributed.storage.inter.jlient;

import agilor.distributed.storage.inter.client.IClient;
import agilor.distributed.storage.inter.thrift.DEVICE;
import org.apache.thrift.TException;

/**
 * Created by LQ on 2015/8/12.
 * 设备集合迭代器
 */
public class DeviceIterator extends AWidget implements Iterator<Device> {
    private long hwnd=-1;

    IClient client = null;

    private Device _next=null;
    private boolean isEnd=false;



    public DeviceIterator(Agilor agilor) throws TException {
        this.client = agilor.getClient();
        hwnd = client.QueryDeviceInfo();
    }

    @Override
    public boolean hasNext() throws TException {
        if(hwnd<0) return false;
        if (_next != null) return true;
        if (isEnd) return false;

        DEVICE device = client.EnumDeviceInfo(hwnd);
        if (device.id > 0) this._next = AParse.parse(device);
        else  isEnd=true;
        return _next != null;
    }

    @Override
    public void close() { }

    @Override
    public Device next() throws TException {
        if(hwnd<0) return null;
        if(isEnd) return null;
        if(_next!=null) {
            Device t = _next;
            _next = null;
            return t;
        }

        DEVICE device = client.EnumDeviceInfo(hwnd);
        if (device.id < 0) {
            isEnd = true;
            return null;
        } else return AParse.parse(device);


    }

}
