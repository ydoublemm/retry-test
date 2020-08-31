package org.example;

import org.example.pojo.Server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by caojidasabi on 2020/8/30.
 */
public class CopyWeightRoundRobinLoadBalance extends AbstractLoadBalance{

    protected AtomicInteger atomicInteger = new AtomicInteger(-1);

    @Override
    protected void init() {
        super.init();

        Map<Server, Integer> map = getServers().stream()
                .collect(
                        Collectors.toMap(Function.identity(),Server::getWeight)
                );

        List<Server> serverList = getServers();

        map.forEach((k,v)->{
            if(v>1){
                for(int i=0;i<v-1;i++){

                    serverList.add(k);

                }


            }

        });
    }

    @Override
    protected Server doSelect() {

        return getServers().get((atomicInteger.addAndGet(1)%getServers().size()));

    }
}
