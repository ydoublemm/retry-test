package org.example;

import org.example.pojo.Server;

import java.util.List;
import java.util.Random;

/**
 * Created by caojidasabi on 2020/8/30.
 *
 * 随机轮询
 *
 */
public class SimpleRandomLoadBalance extends AbstractLoadBalance {

    /*@Override
    protected List<Server> getServers() {
        return super.getServers();
    }
*/

    @Override
    protected Server doSelect() {
        return getServers().get(new Random().nextInt(getServers().size()));
    }
}
