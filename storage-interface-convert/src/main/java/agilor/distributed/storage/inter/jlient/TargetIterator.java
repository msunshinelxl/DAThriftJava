package agilor.distributed.storage.inter.jlient;

import agilor.distributed.storage.inter.client.IClient;
import agilor.distributed.storage.inter.thrift.DEVICE;
import agilor.distributed.storage.inter.thrift.TAGNODE;
import agilor.distributed.storage.inter.thrift.TAGVAL;
import org.apache.thrift.TException;

/**
 * Created by LQ on 2015/8/12.
 * 点集合迭代器
 */
public class TargetIterator implements Iterator<Target> {
    private long hwnd=-1;

    IClient client = null;

    private Target _next=null;
    private boolean isEnd=false;
    private Device device=null;


    public TargetIterator(Device device) throws TException {
        client.open();
        this.device = device;
        hwnd = client.QueryTagsbyDevice(device.getName());
        if(hwnd<0)isEnd=true;
    }


    @Override
    public boolean hasNext() throws TException {

        if (_next != null) return true;
        if (isEnd) return false;

        TAGNODE node = client.EnumNextTag(hwnd);
        if (node.id > 0) this._next = AParse.parse(node);
        else  isEnd=true;
        return _next != null;
    }

    @Override
    public void close() {

    }

    @Override
    public Target next() throws TException {
        if(isEnd) return null;
        if(_next!=null) {
            Target t = _next;
            _next = null;
            return t;
        }

        TAGNODE node = client.EnumNextTag(hwnd);
        if (node.id < 0) {
            isEnd = true;
            return null;
        } else return AParse.parse(node);
    }
}
