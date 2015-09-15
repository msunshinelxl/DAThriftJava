package agilor.distributed.storage.inter.jlient;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.Serializable;

/**
 * Created by LQ on 2015/8/12.
 * 点值类型
 */
public enum  ValType implements Serializable {
    STRING('S'),
    FLOAT('R'),
    BOOLEAN('B');

    private char v;

    ValType(char c)
    {
        this.v =c;
    }


    public int toInt() {
        return (int) v;
    }

    public char value()
    {
        return v;
    }

    public static ValType value(char c)
    {
        if(c=='R') return FLOAT;
        if(c=='S') return STRING;
        if(c=='B') return BOOLEAN;
        return null;
    }


}
